package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.DukeException;
import duke.validation.Validation;

public class TaskManager {
    public static final int TODO_DESCRIPTION_BEGIN_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_BEGIN_INDEX = 9;
    public static final int EVENT_DESCRIPTION_BEGIN_INDEX = 6;
    public static final int MAX_TASK = 100;

    private final Task[] tasks = new Task[MAX_TASK];
    private int taskCount = 0;

    public void printList() {
        if (taskCount == 0) {
            UI.printEmptyListMessage();
        } else {
            UI.printList(tasks);
        }
    }

    public String[] decodeInput(String input) {
        return input.split(" ");
    }

    public void crossOff(String[] inputWords) {
        if (!Validation.isValidCrossOff(inputWords)) {
            DukeException.invalidCrossOffException();
            return;
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (!Validation.isValidTaskIndex(taskIndex, taskCount)) {
            DukeException.invalidTaskIndexException();
            return;
        }
        tasks[taskIndex].setDone();
        UI.printDoneMessage(tasks[taskIndex]);
    }

    public void addTask(String input, String[] inputWords, String command) {
        String description;
        switch (command) {
        case "todo":
            if (!Validation.isValidTodo(inputWords)) {
                DukeException.invalidTodoException();
                return;
            }
            description = input.substring(TODO_DESCRIPTION_BEGIN_INDEX);
            tasks[taskCount] = new ToDo(description);
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
            tasks[taskCount] = new Deadline(description, endDate);
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
            tasks[taskCount] = new Event(description, duration);
            break;
        default:
            break;
        }
        taskCount = taskCount + 1;
        UI.printAdditionMessage(tasks[taskCount - 1], taskCount);
    }
}
