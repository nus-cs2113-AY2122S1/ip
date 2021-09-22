package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.DukeException;
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

    public static void printList() {
        if (taskCount == 0) {
            UI.printEmptyListMessage();
        } else {
            UI.printList(tasks);
        }
    }

    public static String[] decodeInput(String input) {
        return input.split(" ");
    }

    public static void crossOff(String[] inputWords) {
        if (!Validation.isValidCrossOff(inputWords)) {
            DukeException.invalidCrossOffException();
            return;
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (!Validation.isValidTaskIndex(taskIndex, taskCount)) {
            DukeException.invalidTaskIndexException();
            return;
        }
        Task current = tasks.get(taskIndex);
        current.setDone();
        UI.printDoneMessage(current);
    }

    public static void deleteTask(String[] inputWords) {
        if (!Validation.isValidDeleteTask(inputWords)) {
            DukeException.invalidDeleteTaskException();
            return;
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (!Validation.isValidTaskIndex(taskIndex, taskCount)) {
            DukeException.invalidTaskIndexException();
            return;
        }
        Task current = tasks.get(taskIndex);
        taskCount = taskCount - 1;
        UI.printDeleteMessage(current, taskCount);
        tasks.remove(taskIndex);
    }
    public static void addToDo(String input, String[] inputWords) {
        if (!Validation.isValidTodo(inputWords)) {
            DukeException.invalidTodoException();
            return;
        }
        String description = input.substring(TODO_DESCRIPTION_BEGIN_INDEX);
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        taskCount = taskCount + 1;
        UI.printAdditionMessage(newToDo, taskCount);
    }

    public static void addDeadline(String input, String[] inputWords) {
        if (!Validation.isValidDeadline(input, inputWords)) {
            DukeException.invalidDeadlineException();
            return;
        } else if (!Validation.isValidEndDate(input)) {
            DukeException.invalidEndDateException();
            return;
        }
        String description = input.substring(DEADLINE_DESCRIPTION_BEGIN_INDEX, input.indexOf("/by") - 1);
        int endDateBeginIndex = input.indexOf(DEADLINE_KEYWORD) + 4;
        String endDate = input.substring(endDateBeginIndex);
        Task newDeadline = new Deadline(description, endDate);
        tasks.add(newDeadline);
        taskCount = taskCount + 1;
        UI.printAdditionMessage(newDeadline, taskCount);
    }

    public static void addEvent(String input, String[] inputWords) {
        if (!Validation.isValidEvent(input, inputWords)) {
            DukeException.invalidEventException();
            return;
        } else if (!Validation.isValidDuration(input)) {
            DukeException.invalidDurationException();
            return;
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
