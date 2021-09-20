package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Duke {
    private static final String TASKS_FILENAME = "./data/duke.txt";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final String TASK_DEADLINE_SPLITTER = "/by";
    private static final String TASK_EVENT_SPLITTER = "/at";

    private static final String MESSAGE_LIST_EMPTY = "Task list is empty.";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number.";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    private static final String MESSAGE_INVALID_TASK_TYPE = "Invalid task type.";
    private static final String MESSAGE_ADD_TASK_FAILED = "Fail to add task.";
    private static final String MESSAGE_DELETE_TASK_FAILED = "Fail to delete task.";

    private static final String MESSAGE_FORMAT_DONE_USAGE = "Usage: %s <task number>";
    private static final String MESSAGE_FORMAT_TODO_USAGE = "Usage: %s <description>";
    private static final String MESSAGE_FORMAT_DEADLINE_USAGE = "Usage: %s <description> %s <date/time>";
    private static final String MESSAGE_FORMAT_EVENT_USAGE = "Usage: %s <description> %s <date/time>";
    private static final String MESSAGE_FORMAT_DELETE_USAGE = "Usage: %s <task number>";

    private static final String MESSAGE_FORMAT_TASK_ALREADY_MARKED = "Task #%d is already marked as done.";
    private static final String MESSAGE_FORMAT_TASK_MARKED = "Task marked as done:\n  %s";
    private static final String MESSAGE_FORMAT_TASK_ADDED = "Got it. Task added:\n  %s\nThere are %d tasks in the list.";
    private static final String MESSAGE_FORMAT_TASK_DELETED = "Task deleted:\n  %s\nThere are %d tasks left in the list.";

    private final TaskList TASKS;
    private final Storage STORAGE;
    private final Ui UI;

    public Duke(String filename) {
        this.TASKS = new TaskList();
        this.STORAGE = new Storage(filename);
        this.UI = new Ui();
    }

    public void run() {
        loadTasksFromStorage();
        UI.printWelcomeMessage();

        boolean isRunning = true;
        do {
            String input = UI.getUserInput();
            Parser parser = new Parser(input);
            String command = parser.getCommand();
            String argument = parser.getArgument();

            if (command.equals(COMMAND_BYE)) {
                isRunning = false;
            } else {
                executeCommand(command, argument);
            }
        } while (isRunning);

        UI.printExitMessage();
    }

    /**
     * Prints tasks in list format.
     */
    private void printTasks() {
        UI.printMessage(TASKS.toString());
    }

    /**
     * Executes the list command. Print tasks in the list.
     */
    private void executeListCommand() {
        if (TASKS.isEmpty()) {
            UI.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            printTasks();
        }
    }

    /**
     * Executes the done command. Marks the given task as done.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @throws DukeException If argument is invalid/missing or task is already marked as done.
     */
    private void executeDoneCommand(String argument) throws DukeException {
        if (!Util.isStringInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DONE_USAGE, COMMAND_DONE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!TASKS.isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        // Get task object associated to the task index from list
        Task task = TASKS.getTaskAt(taskNumber - 1);

        if (task.isDone()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_TASK_ALREADY_MARKED, taskNumber));
        }

        task.setAsDone();
        UI.printMessage(String.format(MESSAGE_FORMAT_TASK_MARKED, task));
    }

    /**
     * Executes the add task command. Adds a task depending on type.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @param taskType The type of task.
     * @throws DukeException If command format is incorrect or taskType is invalid.
     */
    private void executeAddTask(String argument, char taskType) throws DukeException {
        Task task = null;
        String[] descriptionAndArg;
        switch (taskType) {
        case Task.TYPE_TODO:
            if (argument.isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_TODO_USAGE, COMMAND_TODO));
            }
            task = TASKS.addTodoTask(argument);
            break;

        case Task.TYPE_DEADLINE:
            descriptionAndArg = Parser.getTaskDescriptionAndArg(argument, TASK_DEADLINE_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_DEADLINE_USAGE, COMMAND_DEADLINE, TASK_DEADLINE_SPLITTER));
            }
            task = TASKS.addDeadlineTask(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        case Task.TYPE_EVENT:
            descriptionAndArg = Parser.getTaskDescriptionAndArg(argument, TASK_EVENT_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_EVENT_USAGE, COMMAND_EVENT, TASK_EVENT_SPLITTER));
            }
            task = TASKS.addEventTask(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        default:
            throw new DukeException(MESSAGE_INVALID_TASK_TYPE);
        }

        if (task == null) {
            throw new DukeException(MESSAGE_ADD_TASK_FAILED);
        }

        UI.printMessage(String.format(MESSAGE_FORMAT_TASK_ADDED, task, TASKS.getSize()));
    }

    /**
     * Executes the delete command. Removes the task from list.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @throws DukeException If argument is invalid/missing.
     */
    private void executeDeleteCommand(String argument) throws DukeException {
        if (!Util.isStringInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DELETE_USAGE, COMMAND_DELETE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!TASKS.isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        Task removedTask = TASKS.removeTaskAt(taskNumber - 1);
        if (removedTask == null) {
            throw new DukeException(MESSAGE_DELETE_TASK_FAILED);
        }

        UI.printMessage(String.format(MESSAGE_FORMAT_TASK_DELETED, removedTask, TASKS.getSize()));
    }

    /**
     * Executes a command.
     *
     * @param command  The command from getCommandAndArgument(<string>).
     * @param argument The argument from getCommandAndArgument(<string>).
     */
    private void executeCommand(String command, String argument) {
        try {
            boolean hasListChanged = false;

            switch (command) {
            case COMMAND_LIST:
                executeListCommand();
                break;

            case COMMAND_DONE:
                executeDoneCommand(argument);
                hasListChanged = true;
                break;

            case COMMAND_TODO:
                executeAddTask(argument, Task.TYPE_TODO);
                hasListChanged = true;
                break;

            case COMMAND_DEADLINE:
                executeAddTask(argument, Task.TYPE_DEADLINE);
                hasListChanged = true;
                break;

            case COMMAND_EVENT:
                executeAddTask(argument, Task.TYPE_EVENT);
                hasListChanged = true;
                break;

            case COMMAND_DELETE:
                executeDeleteCommand(argument);
                hasListChanged = true;
                break;

            default:
                throw new DukeException(MESSAGE_UNKNOWN_COMMAND);
            }

            if (hasListChanged) {
                writeTasksToFile();
            }
        } catch (DukeException e) {
            UI.printException(e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     */
    private void loadTasksFromStorage() {
        try {
            ArrayList<Task> tasks = STORAGE.load();
            if (tasks == null) {
                return;
            }

            for (Task task : tasks) {
                TASKS.addTask(task);
            }
        } catch (DukeException e) {
            UI.printException(e.getMessage());
        }
    }

    /**
     * Writes the tasks in the TaskList to the storage file
     */
    private void writeTasksToFile() {
        try {
            STORAGE.write(TASKS);
        } catch (DukeException e) {
            UI.printException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(TASKS_FILENAME).run();
    }
}
