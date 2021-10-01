package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.InvalidFormatException;
import duke.validation.InvalidIndexException;
import duke.validation.Validation;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The TaskManager class handles all aspects regarding tasks.
 * It can create, delete and edit statuses of tasks.
 */
public class TaskManager {
    private static final int TODO_DESCRIPTION_BEGIN_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_BEGIN_INDEX = 9;
    private static final int EVENT_DESCRIPTION_BEGIN_INDEX = 6;
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String EVENT_KEYWORD = "/at";

    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int taskCount = 0;

    /**
     * Prints out the list of existing tasks or error message if there is no tasks.
     * @param inputWords The inputs received from user
     */
    public static void printList(String[] inputWords) {
        if (inputWords.length >= 2) {
            UI.printWrongButOkayMessage();
        }
        if (taskCount == 0) {
            UI.printEmptyListMessage();
        } else {
            UI.printList(tasks);
        }
    }

    /**
     * Decodes the input into a format that is read by other methods.
     * @param input The input received from user
     * @return Returns decoded input as another type.
     */
    public static String[] decodeInput(String input) {
        String[] inputWords = input.split(" ");
        return inputWords;
    }

    /**
     * Sets the status of tasks as done.
     * @param inputWords The inputs received from user.
     * @throws InvalidFormatException The input received is not of proper format.
     * @throws InvalidIndexException The task with indicated index does not exist.
     */
    public static void crossOff(String[] inputWords) throws InvalidFormatException, InvalidIndexException {
        if (!Validation.isValidCrossOff(inputWords)) {
            throw new InvalidFormatException();
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (!Validation.isValidTaskIndex(taskIndex, taskCount)) {
            throw new InvalidIndexException();
        }
        Task current = tasks.get(taskIndex);
        current.setDone();
        UI.printDoneMessage(current);
    }

    /**
     * Deletes the specified task by index.
     * @param inputWords The inputs received from user.
     * @throws InvalidFormatException The input received is not of proper format.
     * @throws InvalidIndexException The task with indicated index does not exist.
     */
    public static void deleteTask(String[] inputWords) throws InvalidFormatException, InvalidIndexException {
        if (!Validation.isValidDeleteTask(inputWords)) {
            throw new InvalidFormatException();
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (!Validation.isValidTaskIndex(taskIndex, taskCount)) {
            throw new InvalidIndexException();
        }
        Task current = tasks.get(taskIndex);
        taskCount = taskCount - 1;
        UI.printDeleteMessage(current, taskCount);
        tasks.remove(taskIndex);
    }

    /**
     * Creates a ToDo task.
     * @param input The input received from user.
     * @param inputWords The inputs received from user.
     * @throws InvalidFormatException The input received is not of proper format.
     */
    public static void addToDo(String input, String[] inputWords) throws InvalidFormatException {
        if (!Validation.isValidTodo(inputWords)) {
            throw new InvalidFormatException();
        }
        String description = input.substring(TODO_DESCRIPTION_BEGIN_INDEX);
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        taskCount = taskCount + 1;
        UI.printAdditionMessage(newToDo, taskCount);
    }

    /**
     * Creates a Deadline task.
     * @param input The input received from user.
     * @param inputWords The inputs received from user.
     * @throws InvalidFormatException The input received is not of proper format.
     */
    public static void addDeadline(String input, String[] inputWords) throws InvalidFormatException {
        if (!(Validation.isValidDeadline(input, inputWords) && Validation.isValidEndDate(input))) {
            throw new InvalidFormatException();
        }
        String description = input.substring(DEADLINE_DESCRIPTION_BEGIN_INDEX, input.indexOf("/by") - 1);
        int endDateBeginIndex = input.indexOf(DEADLINE_KEYWORD) + 4;
        String endDate = input.substring(endDateBeginIndex);
        Task newDeadline = new Deadline(description, endDate);
        tasks.add(newDeadline);
        taskCount = taskCount + 1;
        UI.printAdditionMessage(newDeadline, taskCount);
    }

    /**
     * Creates an Event task.
     * @param input The input received from user.
     * @param inputWords The inputs received from user.
     * @throws InvalidFormatException The input received is not of proper format.
     */
    public static void addEvent(String input, String[] inputWords) throws InvalidFormatException {
        if (!(Validation.isValidEvent(input, inputWords) && Validation.isValidDuration(input))) {
            throw new InvalidFormatException();
        }
        String description = input.substring(EVENT_DESCRIPTION_BEGIN_INDEX, input.indexOf("/at") - 1);
        int durationBeginIndex = input.indexOf(EVENT_KEYWORD) + 4;
        String duration = input.substring(durationBeginIndex);
        Task newEvent = new Event(description, duration);
        tasks.add(newEvent);
        taskCount = taskCount + 1;
        UI.printAdditionMessage(newEvent, taskCount);
    }
  
    public static void findTask(String input) {
        String filterString = input.replaceFirst("find ", ""); //removes 'find' to find keyword
        ArrayList<Task> specificTasks = (ArrayList<Task>) tasks.stream()
                .filter((task) -> task.getDescription()
                .contains(filterString)).collect(Collectors.toList());
        if (specificTasks.isEmpty()) {
            UI.printEmptyListMessage(filterString);
        } else {
            UI.printList(specificTasks, filterString);
        }
}
