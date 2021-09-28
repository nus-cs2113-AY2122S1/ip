package duke;
import duke.exception.*;

import java.util.Scanner;

/**
 * A class containing the methods necessary to parse the user input to specific commands
 * and executes these commands according to their purposes.
 */
public class Parser {
    public static final int TODO_LENGTH = 4;
    public static final int EVENT_LENGTH = 5;
    public static final int DEADLINE_LENGTH = 8;

    public static Scanner in = new Scanner(System.in);
    public static String input;
    public static TaskList taskList = new TaskList();

    public static String getInput() {
        String input = in.nextLine();
        return input;
    }

    public static boolean isList() {
        return input.equals("list");
    }

    public static boolean isBye() {
        return input.equals("bye");
    }

    public static boolean isDone() {
        return input.startsWith("done");
    }

    public static boolean isEvent() {
        return input.startsWith("event");
    }

    public static boolean isDeadLine() {
        return input.startsWith("deadline");
    }

    public static boolean isToDo() {
        return input.startsWith("todo");
    }

    public static boolean isDelete() { return input.startsWith("delete"); }

    /**
     * Verifies if the todo command has valid input.
     * @return Nothing
     */
    public static boolean isInvalidToDo() {
        return input.substring(TODO_LENGTH).equals("");
    }

    /**
     * Verifies if the Deadline command has valid input time
     * Ensures the Deadline has the necessary part "/by"
     * @return Nothing
     */
    public static boolean isInvalidDeadLine() {
        String inputTemp = input.substring(DEADLINE_LENGTH).trim();
        boolean isEmpty = inputTemp.equals("");
        String[] task = inputTemp.split("/by");
        return (isEmpty || task.length == 0 || task.length == 1);

    }

    /**
     * Verifies if the Event command has valid input time
     * Ensures the Event has the necessary part "/at"
     * @return Nothing
     */
    public static boolean isInvalidEvent() {
        String inputTemp = input.substring(EVENT_LENGTH).trim();
        boolean isEmpty = inputTemp.equals("");
        String[] task = inputTemp.split("/at");
        return (isEmpty || task.length == 0 || task.length == 1);
    }

    /**
     * Convert the user input string to a specific task object
     * Categorize them into todos, deadlines, and events.
     * @return a specific task object
     * @throws InvalidDeadlineTimeException
     * @throws InvalidEventTimeException
     * @throws EmptyToDoException
     * @throws InvalidCommandException
     */
    public static Task convertInputToTask()
            throws InvalidDeadlineTimeException, InvalidEventTimeException, EmptyToDoException, InvalidCommandException {
        if(isDeadLine()){
            if(isInvalidDeadLine()) {
                throw new InvalidDeadlineTimeException();
            }
            return new DeadLine(input);
        }
        if(isEvent()){
            if(isInvalidEvent()) {
                throw new InvalidEventTimeException();
            }
            return new Event(input);
        }
        if(isToDo()){
            if(isInvalidToDo()) {
                throw new EmptyToDoException();
            }
            return new ToDo(input);
        }
        else
            throw new InvalidCommandException();
    }

    /**
     * Method that executes specific functions in TaskList according to the command received from user
     * @param input the user input string
     * @param c the command that parsed from user input to be executed
     * @throws TaskIndexOutOfBound
     * @throws InvalidDeadlineTimeException
     * @throws InvalidEventTimeException
     * @throws EmptyToDoException
     * @throws InvalidCommandException
     * @throws EmptyDoneIndexException
     * @throws InvalidInputException
     */
    public static void runCommand(String input, Command c)
            throws TaskIndexOutOfBound, InvalidDeadlineTimeException, InvalidEventTimeException, EmptyToDoException, InvalidCommandException, EmptyDoneIndexException, InvalidInputException{
        switch (c) {
        case LIST:
            taskList.listTasks();
            break;
        case DONE:
            taskList.tasksDone(input);
            break;
        case DELETE:
            taskList.deleteTask(input);
            break;
        case INVALID:
            throw new InvalidInputException();
        case TODO:
        case EVENT:
        case DEADLINE:
            Task task = convertInputToTask();
            taskList.addTask(task);
            break;
        }
    }
}
