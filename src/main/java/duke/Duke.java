package duke;

import java.util.Scanner;

import duke.storage.Storage;
import duke.storage.Storage.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    /** Platform independent line separator */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String DATA_FILE_SEPARATOR = " ` ";

    private static final String MESSAGE_GREETING = "Hello! I'm Duke" + LINE_SEPARATOR
            + "%1$s" + LINE_SEPARATOR
            + "What can I do for you?";
    private static final String MESSAGE_DATA_FILE_NEW = "No data file found. Will store data in new file: '%1$s'";
    private static final String MESSAGE_DATA_FILE_EXISTING = "Data file found. Using data from: '%1$s'";

    private static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_ERROR = "☹ OOPS!!! %1$s";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:" + LINE_SEPARATOR
            + "  %1$s" + LINE_SEPARATOR
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:" + LINE_SEPARATOR
            + "  %1$s" + LINE_SEPARATOR
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:" + LINE_SEPARATOR + "%1$s";
    private static final String MESSAGE_TASK_MARKED_AS_DONE = "Nice! I've marked this task as done:" + LINE_SEPARATOR
            + "  %1$s";

    private static final String MESSAGE_TODO_DESCRIPTION_EMPTY = "The description of a todo cannot be empty.";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";
    private static final String MESSAGE_UNRECOGNISED_TASK_TYPE_ICON = "Unrecognised task type icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_TASK_STATUS_ICON = "Unrecognised task status icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_EVENT_FORMAT = "Unrecognised event format. " + LINE_SEPARATOR
            + "Please ensure you provide the date/time of the event.";
    private static final String MESSAGE_UNRECOGNISED_DEADLINE_FORMAT = "Unrecognised deadline format. " + LINE_SEPARATOR
            + "Please ensure you provide the date/time of the deadline.";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "Please use a valid integer for the task number.";
    private static final String MESSAGE_NONEXISTENT_TASK_NUMBER = "That task number does not exist!";

    private static final String DEADLINE_PREFIX_BY = "/by";
    private static final String EVENT_PREFIX_AT = "/at";

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";

    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_ARGS = 1;

    /** Array of Task objects */
    private static TaskList tasks;

    private static Storage storage;

    /**
     * Main entry point of Duke.
     */
    public static void main(String[] args) {
        try {
            storage = new Storage("data/duke.txt");
            tasks = storage.loadData();
        } catch (StorageException e) {
            printResponseBlock(e.getMessage());
            throw new RuntimeException(e);
        }
        printGreeting(storage.getPath(), storage.isUsingNewFile());
        while (true) {
            final String userInput = getUserInput();
            final String feedback = executeCommand(userInput);
            try {
                storage.saveData(tasks);
                printResponseBlock(feedback);
            } catch (StorageException e) {
                printResponseBlock(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private static void printGreeting(String path, boolean isUsingNewFile) {
        final String dataFileMessage = String.format(
                (isUsingNewFile ? MESSAGE_DATA_FILE_NEW : MESSAGE_DATA_FILE_EXISTING), path);
        printResponseBlock(String.format(MESSAGE_GREETING, dataFileMessage));
    }

    /**
     * Prints out the specified text formatted as a response block.
     * Horizontal lines will be printed before and after the specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    public static void printResponseBlock(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    private static String indent(String text) {
        String[] lines = text.split(LINE_SEPARATOR);
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(LINE_SEPARATOR, lines);
    }

    /**
     * Reads input commands from the user.
     * Ignores blank lines and trims input command.
     *
     * @return Trimmed input command.
     */
    private static String getUserInput() {
        String line = SCANNER.nextLine();
        // Ignore blank lines
        while (line.trim().isEmpty()) {
            line = SCANNER.nextLine();
        }
        return line.trim();
    }

    /**
     * Executes the command specified by the input.
     *
     * @param userInput Input command together with any arguments.
     * @return Feedback about what was executed.
     */
    private static String executeCommand(String userInput) {
        final String[] commandAndArgs = userInput.split(" ", 2);
        final String command = commandAndArgs[INDEX_COMMAND];
        final String args = commandAndArgs.length > INDEX_ARGS ? commandAndArgs[INDEX_ARGS] : "";

        try {
            switch (command) {
            case COMMAND_EXIT:
                printFarewell();
                System.exit(0);
                // fallthrough
            case COMMAND_ADD_TODO:
                return addTodo(args);
            case COMMAND_ADD_DEADLINE:
                return addDeadline(args);
            case COMMAND_ADD_EVENT:
                return addEvent(args);
            case COMMAND_DELETE_TASK:
                return deleteTask(args);
            case COMMAND_LIST_TASKS:
                return listTasks();
            case COMMAND_MARK_TASK_AS_DONE:
                return markTaskAsDone(args);
            default:
                return handleUnrecognisedCommand();
            }
        } catch (DukeException e) {
            return String.format(MESSAGE_ERROR, e.getMessage());
        }
    }

    private static void printFarewell() {
        printResponseBlock(MESSAGE_FAREWELL);
    }

    private static String addTodo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_EMPTY);
        }
        Task task = new ToDo(description);
        return addTask(task);
    }

    private static String addDeadline(String args) throws DukeException {
        final String[] splitArgs = args.split(" " + DEADLINE_PREFIX_BY + " ");
        if (splitArgs.length < 2) {
            throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
        }
        final String description = splitArgs[0];
        final String by = splitArgs[1];
        Task task = new Deadline(description, by);
        return addTask(task);
    }

    private static String addEvent(String args) throws DukeException {
        final String[] splitArgs = args.split(" " + EVENT_PREFIX_AT + " ");
        if (splitArgs.length < 2) {
            throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
        }
        final String description = splitArgs[0];
        final String at = splitArgs[1];
        Task task = new Event(description, at);
        return addTask(task);
    }

    private static String addTask(Task task) {
        tasks.addTask(task);
        return String.format(MESSAGE_TASK_ADDED, task, tasks.getSize());
    }

    private static String deleteTask(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        tasks.removeTask(task);
        return String.format(MESSAGE_TASK_DELETED, task, tasks.getSize());
    }

    /** Returns the list of tasks (numbered) together with their status icons */
    private static String listTasks() {
        String[] formattedTasks = new String[tasks.getSize()];
        for (int i = 0; i < tasks.getSize(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.getTask(i));
        }
        String taskListOutput = String.join(LINE_SEPARATOR, formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }

    private static String markTaskAsDone(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        task.setAsDone();
        return String.format(MESSAGE_TASK_MARKED_AS_DONE, task);
    }

    private static Task getTaskFromStringId(String args) throws DukeException {
        try {
            int taskId = Integer.parseInt(args) - 1;
            return tasks.getTask(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_NONEXISTENT_TASK_NUMBER);
        }
    }

    private static String handleUnrecognisedCommand() throws DukeException {
        throw new DukeException(MESSAGE_UNRECOGNISED_COMMAND);
    }
}
