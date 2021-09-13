package duke;
import duke.exception.*;

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
            } else if (Parser.isBye()){
                Init.bye();
                break;
            } else {
                c = Command.INVALID;
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
            } catch (InvalidInputException e) {
                System.out.println(e);
            }

            Init.lineSeparator();
        }
    }
}