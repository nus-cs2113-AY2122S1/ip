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


    /**
     * Class constructor with specified Ui Object.
     *
     * @param uiObject Ui Object
     */
    public Parser(Ui uiObject) {
        ui = uiObject;
    }

    /**
     * Returns the type of command from user input.
     *
     * @param userInput The read-in user input.
     * @return The type of command
     */
    public String getCommandType(String userInput) {
        String[] userParams = userInput.split(Ui.SPACE_PREFIX);
        return userParams[0];
    }

    /**
     * Returns the extracted index from user input.
     *
     * @param userInput The read-in user input
     * @return The index specified by user input
     */
    public int getIndex(String userInput) {
        String[] userParams = userInput.split(Ui.SPACE_PREFIX);
        return Integer.parseInt(userParams[1]);
    }

    /**
     * Parses the user input and runs the respective user command functionalities.
     *
     * @param userInput The read-in user input
     * @param tasks     The list of tasks
     */
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

    /**
     * Parses the user input and run Task-related commands.
     *
     * @param userInput The read-in user input
     * @param tasks     The list of tasks
     */
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
     * Parses and validates the task name to create a Todo
     * and adds it to the list of tasks.
     *
     * @param taskName The name of the TodoObject to be created
     * @param tasks    The list of tasks
     * @throws DukeException If the task name is empty.
     */
    private void parseNewToDo(String taskName, TaskList tasks) throws DukeException {
        if (taskName.equals(EMPTY_VALUE)) {
            throw new DukeException(DukeException.TODO_EMPTY_MESSAGE);
        }
        tasks.addNewTodoObject(taskName);
    }

    /**
     * Returns an array of details containing task name and "by when".
     * Index 0 contains the task name.
     * Index 1 contains the "by when" date and time.
     *
     * @param unprocessedTaskName The unprocessed task name containing the prefixes and "by when" value.
     * @return An array containing task name and "by when" value.
     */
    private String[] getTaskNameAndByWhen(String unprocessedTaskName) {
        String[] taskNameAndByWhen = new String[2];
        String byWhen = unprocessedTaskName.split(BY_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(BY_WHEN_PREFIX, EMPTY_VALUE).replace(byWhen, EMPTY_VALUE);
        taskNameAndByWhen[0] = actualTaskName.trim();
        taskNameAndByWhen[1] = byWhen;
        return taskNameAndByWhen;
    }

    /**
     * Parses and validates the unprocessed task name to create a Deadline
     * and adds it to the list of tasks.
     *
     * @param unprocessedTaskName The unprocessed task name before extracting out non-taskName relevant info
     * @throws DukeException If the given input does not contain any "/by" prefix or deadline name is missing
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

    /**
     * Returns an array of details containing task name and "at when".
     * Index 0 contains the task name.
     * Index 1 contains the "at when" date and time.
     *
     * @param unprocessedTaskName The unprocessed task name containing the prefixes and "by when" value.
     * @return An array containing task name and "at when" value.
     */
    private String[] getTaskNameAndAtWhen(String unprocessedTaskName) {
        String[] taskNameAndAtWhen = new String[2];
        String atWhen = unprocessedTaskName.split(AT_WHEN_PREFIX)[1].trim();
        String actualTaskName = unprocessedTaskName.replace(AT_WHEN_PREFIX, EMPTY_VALUE).replace(atWhen, EMPTY_VALUE);
        taskNameAndAtWhen[0] = actualTaskName.trim();
        taskNameAndAtWhen[1] = atWhen;
        return taskNameAndAtWhen;
    }

    /**
     * Parses and validates the unprocessed task name to create an Event
     * and adds it to the list of tasks.
     *
     * @param unprocessedTaskName The unprocessed task name before extracting out non-taskName relevant info
     * @throws DukeException If the given input does not contain the "/at" prefix or deadline name is missing
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
