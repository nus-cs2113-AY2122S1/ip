package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String SEPARATOR_SPACE = " ";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    public static void main(String[] args) {
        UI.printWelcome();
        Assistant duke = new Assistant();
        duke.loadFile();
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] inputs = userInput.split(SEPARATOR_SPACE, 2);
        while (!inputs[0].equals(COMMAND_EXIT)) { //check command
            try {
                switch (inputs[0]) {
                case COMMAND_LIST:
                    duke.listTasks();
                    break;
                case COMMAND_DONE:
                    duke.completeTask(inputs[1]);
                    break;
                case COMMAND_DEADLINE:
                    duke.addDeadline(inputs[1]);
                    break;
                case COMMAND_EVENT:
                    duke.addEvent(inputs[1]);
                    break;
                case COMMAND_TODO:
                    duke.addToDo(inputs[1]);
                    break;
                case COMMAND_DELETE:
                    duke.deleteTask(inputs[1]);
                    break;
                case COMMAND_FIND:
                    duke.findTask(inputs[1]);
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (MissingInputException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                UI.printInvalidDescription();
            } catch (InvalidCommandException e) {
                UI.printInvalidCommand();
            } catch (InvalidIndexException e) {
                UI.printInvalidIndex();
            } catch (DateTimeParseException e) {
                UI.printInvalidDate();
            }
            duke.saveFile();
            userInput = in.nextLine();
            inputs = userInput.split(SEPARATOR_SPACE, 2);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
