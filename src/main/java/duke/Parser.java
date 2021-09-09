package duke;
import duke.exception.*;

import java.util.Scanner;

public class Parser {
    public static final int TODO_LENGTH = 4;
    public static final int EVENT_LENGTH = 5;
    public static final int DEADLINE_LENGTH = 8;

    public static Scanner in = new Scanner(System.in);
    public static String input;
    public static TaskManager taskList = new TaskManager();

    public static String getInput() {
        String input = in.nextLine();
        return input;
    }

    //Classify the input to a specific command
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

    //check if there is input exception
    public static boolean isInvalidToDo() {
        return input.substring(TODO_LENGTH).equals("");
    }

    public static boolean isInvalidDeadLine() {
        String inputTemp = input.substring(DEADLINE_LENGTH).trim();
        boolean isEmpty = inputTemp.equals("");
        String[] task = inputTemp.split("/by");
        return (isEmpty || task.length == 0 || task.length == 1);

    }

    public static boolean isInvalidEvent() {
        String inputTemp = input.substring(EVENT_LENGTH).trim();
        boolean isEmpty = inputTemp.equals("");
        String[] task = inputTemp.split("/at");
        return (isEmpty || task.length == 0 || task.length == 1);
    }


    /**
     * Convert the string that user inputs to a specific task object
     * Categorize them into todos, deadlines, and events
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


    public static void runCommand(String input, Command c)
            throws TaskIndexOutOfBound, InvalidDeadlineTimeException, InvalidEventTimeException, EmptyToDoException, InvalidCommandException {
        switch (c) {
        case LIST:
            taskList.listTasks();
            break;
        case DONE:
            taskList.tasksDone(input);
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            Task task = convertInputToTask();
            taskList.addTask(task);
        }
    }







}
