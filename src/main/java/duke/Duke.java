package duke;

import java.util.Scanner;

import duke.storage.Storage;
import duke.storage.Storage.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String DATA_FILE_SEPARATOR = " ` ";

    private static final String MESSAGE_ERROR = "☹ OOPS!!! %1$s";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n" + "  %1$s\n"
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:\n" + "  %1$s\n"
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:\n" + "%1$s";
    private static final String MESSAGE_TASK_MARKED_AS_DONE = "Nice! I've marked this task as done:\n" + "  %1$s";

    private static final String MESSAGE_TODO_DESCRIPTION_EMPTY = "The description of a todo cannot be empty.";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";
    private static final String MESSAGE_UNRECOGNISED_EVENT_FORMAT = "Unrecognised event format.\n"
            + "Please ensure you provide the date/time of the event.";
    private static final String MESSAGE_UNRECOGNISED_DEADLINE_FORMAT = "Unrecognised deadline format.\n"
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

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadData();
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
    public void run() {
        ui.showGreeting(storage.getPath(), storage.isUsingNewFile());
        while (true) {
            final String userInput = getUserInput();
            final String feedback = executeCommand(userInput);
            try {
                storage.saveData(tasks);
                ui.showToUser(feedback);
            } catch (StorageException e) {
                ui.showToUser(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Main entry point of Duke.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Reads input commands from the user.
     * Ignores blank lines and trims input command.
     *
     * @return Trimmed input command.
     */
    private String getUserInput() {
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
    private String executeCommand(String userInput) {
        final String[] commandAndArgs = userInput.split(" ", 2);
        final String command = commandAndArgs[INDEX_COMMAND];
        final String args = commandAndArgs.length > INDEX_ARGS ? commandAndArgs[INDEX_ARGS] : "";

        try {
            switch (command) {
            case COMMAND_EXIT:
                ui.showFarewell();
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

    private String addTodo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_EMPTY);
        }
        Task task = new ToDo(description);
        return addTask(task);
    }

    private String addDeadline(String args) throws DukeException {
        final String[] splitArgs = args.split(" " + DEADLINE_PREFIX_BY + " ");
        if (splitArgs.length < 2) {
            throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
        }
        final String description = splitArgs[0];
        final String by = splitArgs[1];
        Task task = new Deadline(description, by);
        return addTask(task);
    }

    private String addEvent(String args) throws DukeException {
        final String[] splitArgs = args.split(" " + EVENT_PREFIX_AT + " ");
        if (splitArgs.length < 2) {
            throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
        }
        final String description = splitArgs[0];
        final String at = splitArgs[1];
        Task task = new Event(description, at);
        return addTask(task);
    }

    private String addTask(Task task) {
        tasks.addTask(task);
        return String.format(MESSAGE_TASK_ADDED, task, tasks.getSize());
    }

    private String deleteTask(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        tasks.removeTask(task);
        return String.format(MESSAGE_TASK_DELETED, task, tasks.getSize());
    }

    /** Returns the list of tasks (numbered) together with their status icons */
    private String listTasks() {
        String[] formattedTasks = new String[tasks.getSize()];
        for (int i = 0; i < tasks.getSize(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.getTask(i));
        }
        String taskListOutput = String.join("\n", formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }

    private String markTaskAsDone(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        task.setAsDone();
        return String.format(MESSAGE_TASK_MARKED_AS_DONE, task);
    }

    private Task getTaskFromStringId(String args) throws DukeException {
        try {
            int taskId = Integer.parseInt(args) - 1;
            return tasks.getTask(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_NONEXISTENT_TASK_NUMBER);
        }
    }

    private String handleUnrecognisedCommand() throws DukeException {
        throw new DukeException(MESSAGE_UNRECOGNISED_COMMAND);
    }
}
