package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.InvalidFormatException;
import duke.validation.InvalidIndexException;
import duke.validation.Validation;

import java.util.ArrayList;

public class TaskManager {
    private static final int TODO_DESCRIPTION_BEGIN_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_BEGIN_INDEX = 9;
    private static final int EVENT_DESCRIPTION_BEGIN_INDEX = 6;
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String EVENT_KEYWORD = "/at";

    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int taskCount = 0;

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

    public static String[] decodeInput(String input) {
        String[] inputWords = input.split(" ");
        return inputWords;
    }

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
}
