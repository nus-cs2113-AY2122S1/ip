package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.DukeException;
import duke.validation.Validation;

import java.util.ArrayList;

public class TaskManager {
    public static final int TODO_DESCRIPTION_BEGIN_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_BEGIN_INDEX = 9;
    public static final int EVENT_DESCRIPTION_BEGIN_INDEX = 6;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

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

    public static void addTask(String input, String[] inputWords, String command) {
        String description;
        switch (command) {
        case "todo":
            if (!Validation.isValidTodo(inputWords)) {
                DukeException.invalidTodoException();
                return;
            }
            description = input.substring(TODO_DESCRIPTION_BEGIN_INDEX);
            Task newToDo = new ToDo(description);
            tasks.add(newToDo);
            break;
        case "deadline":
            if (!Validation.isValidDeadline(input, inputWords)) {
                DukeException.invalidDeadlineException();
                return;
            } else if (!Validation.isValidEndDate(input)) {
                DukeException.invalidEndDateException();
                return;
            }
            String endDate;
            description = input.substring(DEADLINE_DESCRIPTION_BEGIN_INDEX, input.indexOf("/by") - 1);
            int endDateBeginIndex = input.indexOf("/by") + 4;
            endDate = input.substring(endDateBeginIndex);
            Task newDeadline = new Deadline(description, endDate);
            tasks.add(newDeadline);
            break;
        case "event":
            if (!Validation.isValidEvent(input, inputWords)) {
                DukeException.invalidEventException();
                return;
            } else if (!Validation.isValidDuration(input)) {
                DukeException.invalidDurationException();
                return;
            }
            String duration;
            description = input.substring(EVENT_DESCRIPTION_BEGIN_INDEX, input.indexOf("/at") - 1);
            int durationBeginIndex = input.indexOf("/at") + 4;
            duration = input.substring(durationBeginIndex);
            Task newEvent = new Event(description, duration);
            tasks.add(newEvent);
            break;
        default:
            break;
        }
        taskCount = taskCount + 1;
        Task current = tasks.get(taskCount - 1);
        UI.printAdditionMessage(current, taskCount);
    }

}
