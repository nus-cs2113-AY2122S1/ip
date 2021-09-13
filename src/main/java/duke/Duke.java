package duke;
import duke.exception.EmptyToDoException;
import duke.exception.TaskIndexOutOfBound;
import duke.exception.EmptyInputException;
import duke.exception.InvalidDeadlineTimeException;
import duke.exception.InvalidEventTimeException;
import duke.exception.InvalidCommandException;

public class Duke {
    public static void main(String[] args) {
        Init.showWelcomeMessage();
        while(true) {
            Parser.input = Parser.getInput();
            Command c;
            if (Parser.isList()) {
                c = Command.LIST;
            } else if (Parser.isDone()) {
                c = Command.DONE;
            } else if(Parser.isDelete()) {
                c = Command.DELETE;    // for level-6 just simply add one command here, no need for other changes
            } else if (Parser.isDeadLine()) {
                c = Command.DEADLINE;
            } else if (Parser.isEvent()) {
                c = Command.EVENT;
            } else if (Parser.isToDo()) {
                c = Command.TODO;
            } else {
                Init.bye();
                break;
            }

            try {
                Parser.runCommand(Parser.input, c);
            } catch(TaskIndexOutOfBound e) {
                System.out.println(e.getMessage());
            } catch (EmptyToDoException e) {
                System.out.println(e);
            } catch (InvalidDeadlineTimeException e) {
                System.out.println(e);
            } catch (InvalidEventTimeException e) {
                System.out.println(e);
            } catch (InvalidCommandException e) {
                System.out.println(e);
            }

            Init.lineSeparator();
        }
    }
}