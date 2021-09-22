package parser;

import exception.DukeException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class Parser {
    private final Ui ui;

    // Command Prefixes for checking type of command
    private static final String COMMAND_BYE = "Bye";
    private static final String COMMAND_DONE = "Done";
    private static final String COMMAND_LIST = "List";
    private static final String COMMAND_DELETE = "Delete";
    private static final String COMMAND_FIND = "Find";

    // Prefixes meant for processing data parameters for Task
    private static final String BY_WHEN_PREFIX = "/by";
    private static final String AT_WHEN_PREFIX = "/at";
    private static final String TASK_TODO_PREFIX = "Todo";
    private static final String TASK_DEADLINE_PREFIX = "Deadline";
    private static final String TASK_EVENT_PREFIX = "Event";
    private static final String EMPTY_VALUE = "";

    public Parser(Ui uiObject) {
        ui = uiObject;
    }

    public String getCommandType(String userInput) {
        String[] userParams = userInput.split(Ui.SPACE_PREFIX);
        return userParams[0];
    }

    public int getIndex(String userInput) {
        String[] userParams = userInput.split(Ui.SPACE_PREFIX);
        return Integer.parseInt(userParams[1]);
    }

    public void parseUserCommand(String userInput, TaskList tasks) {
        String commandType = getCommandType(userInput);
        if (commandType.equalsIgnoreCase(COMMAND_BYE)) {
            ui.printFarewell();
            System.exit(0);
        }
        if (commandType.equalsIgnoreCase(COMMAND_LIST) || userInput.equals(EMPTY_VALUE)) {
            ui.printTasks(tasks);
        } else if (commandType.equalsIgnoreCase(COMMAND_DONE)) {
            try {
                int index = getIndex(userInput);
                tasks.markTaskAsDone(index - 1);
            } catch (DukeException err) {
                ui.printErrorMessage(err.getMessage());
            }
        } else if (commandType.equalsIgnoreCase(COMMAND_DELETE)) {
            int index = getIndex(userInput);
            tasks.removeTaskAtIndex(index - 1);
        } else if (commandType.equalsIgnoreCase(COMMAND_FIND)) {
            parseFindCommand(userInput);
        } else {
            parseTaskCommands(userInput, tasks);
        }

    }

    public String getKeyword(String userInput) {
        return userInput.split(ui.SPACE_PREFIX)[1];
    }

    public void parseFindCommand(String userInput) {
        String keyword = getKeyword(userInput);
        ArrayList<Task> filteredTasks = TaskList.getAllTasksByName(keyword);
        ui.printFilteredTasks(filteredTasks, keyword);
    }

    public void parseTaskCommands(String userInput, TaskList tasks) {
        String taskType = userInput.split(Ui.SPACE_PREFIX)[0];
        // Remove the Type of Task from the user input
        String taskName = userInput.replace(taskType, EMPTY_VALUE).trim();
        try {
            if (taskType.equalsIgnoreCase(TASK_TODO_PREFIX)) {
                parseNewToDo(taskName, tasks);
            } else if (taskType.equalsIgnoreCase(TASK_DEADLINE_PREFIX)) {
                parseNewDeadline(taskName, tasks);
            } else if (taskType.equalsIgnoreCase(TASK_EVENT_PREFIX)) {
                parseNewEvent(taskName, tasks);
            } else {
                System.out.println(DukeException.UNKNOWN_COMMAND_MESSAGE);
            }
        } catch (DukeException err) {
            ui.printErrorMessage(err.getMessage());
        }
    }

    /**
     * Creates a TodoObject from task name given by the user
     * and returns it to be added to Tasks
     *
     * @param taskName name of the Todo_Task to be created
     * @return TodoObject
     */
    private void parseNewToDo(String taskName, TaskList tasks) throws DukeException {
        if (taskName.equals(EMPTY_VALUE)) {
            throw new DukeException(DukeException.TODO_EMPTY_MESSAGE);
        }
        tasks.addNewTodoObject(taskName);
    }

    private String[] getTaskNameAndByWhen(String unprocessedTaskName) {
        String[] taskNameAndByWhen = new String[2];
        String byWhen = unprocessedTaskName.split(BY_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(BY_WHEN_PREFIX, EMPTY_VALUE).replace(byWhen, EMPTY_VALUE);
        taskNameAndByWhen[0] = actualTaskName.trim();
        taskNameAndByWhen[1] = byWhen;
        return taskNameAndByWhen;
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date to be completed ("byWhen").
     * Lastly, creates a Deadline Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return DeadlineObject
     */
    private void parseNewDeadline(String unprocessedTaskName, TaskList tasks) throws DukeException {
        if (!unprocessedTaskName.contains(BY_WHEN_PREFIX)) {
            throw new DukeException(DukeException.INVALID_TASK_MESSAGE);
        }
        try {
            String[] taskNameAndByWhen = getTaskNameAndByWhen(unprocessedTaskName);
            tasks.addNewDeadlineObject(taskNameAndByWhen[0], taskNameAndByWhen[1]);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH DEADLINE_EMPTY_MESSAGE");
        }
    }

    private String[] getTaskNameAndAtWhen(String unprocessedTaskName) {
        String[] taskNameAndAtWhen = new String[2];
        String atWhen = unprocessedTaskName.split(AT_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(AT_WHEN_PREFIX, EMPTY_VALUE).replace(atWhen, EMPTY_VALUE);
        taskNameAndAtWhen[0] = actualTaskName.trim();
        taskNameAndAtWhen[1] = atWhen;
        return taskNameAndAtWhen;
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date of the event ("atWhen").
     * Lastly, creates an Event Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return EventObject
     */
    private void parseNewEvent(String unprocessedTaskName, TaskList tasks) throws DukeException {
        if (!unprocessedTaskName.contains(AT_WHEN_PREFIX)) {
            throw new DukeException(DukeException.INVALID_TASK_MESSAGE);
        }
        try {
            String [] taskNameAndAtWhen = getTaskNameAndAtWhen(unprocessedTaskName);
            tasks.addNewEventObject(taskNameAndAtWhen[0], taskNameAndAtWhen[1]);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH EVENT_EMPTY_MESSAGE");
        }
    }

}
