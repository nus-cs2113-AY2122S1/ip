package duke;
import duke.exception.*;

import java.io.IOException;


/**
 * The main programme for the Duke programme
 * Integrates all classes together。
 */
public class Duke {
    public static void main(String[] args) {
        Ui.showWelcomeMessage();
        try {
            Storage.loadData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            Parser.input = Parser.getInput();
            Parser.taskList = Storage.taskList;
            Command c;
            if (Parser.isList()) {
                c = Command.LIST;
            } else if (Parser.isDone()) {
                c = Command.DONE;
            } else if(Parser.isDelete()) {
                c = Command.DELETE;
            } else if (Parser.isDeadLine()) {
                c = Command.DEADLINE;
            } else if (Parser.isEvent()) {
                c = Command.EVENT;
            } else if (Parser.isToDo()) {
                c = Command.TODO;
            } else if (Parser.isFind()) {
                c = Command.FIND;
            } else if (Parser.isBye()){
                Ui.bye();
                break;
            } else {
                c = Command.INVALID;
            }
            try {
                Parser.runCommand(Parser.input, c);
            } catch (TaskIndexOutOfBound e) {
                System.out.println(e.getMessage());
            } catch (EmptyToDoException e) {
                System.out.println(e);
            } catch (InvalidDeadlineTimeException e) {
                System.out.println(e);
            } catch (InvalidEventTimeException e) {
                System.out.println(e);
            } catch (InvalidCommandException e) {
                System.out.println(e);
            } catch (EmptyDoneIndexException e) {
                System.out.println(e);
            } catch (InvalidInputException e) {
                System.out.println(e);
            }
            Storage.saveData(Parser.taskList.getAllTasksListFormatted(), Storage.filePath);
            Storage.saveData(Parser.taskList.getAllTasksListOriginal(), Storage.originalInputPath);
            Ui.lineSeparator();
        }
    }
}