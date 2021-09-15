package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    /** Platform independent line separator */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");
    private static final String DATA_FILE_NAME = "duke.txt";
    private static final Path DATA_FILE_PATH = DATA_DIRECTORY_PATH.resolve(DATA_FILE_NAME);
    public static final String DATA_FILE_SEPARATOR = " ` ";

    private static final String MESSAGE_DATA_DIRECTORY_CREATED = "Created new directory: '" + DATA_DIRECTORY_PATH + "'";
    private static final String MESSAGE_DATA_FILE_CREATED = "No data file found. Created new file: '"
            + DATA_FILE_PATH + "'";
    private static final String MESSAGE_DATA_FILE_FOUND = "Data file found. Using data from '" + DATA_FILE_PATH + "'";
    private static final String MESSAGE_DATA_FILE_ACCESS_ERROR = "There was an error accessing the data file: '"
            + DATA_FILE_PATH + "'";
    private static final String MESSAGE_DATA_FILE_PARSE_ERROR = "There was an error parsing the data file:";
    private static final String MESSAGE_TASK_FORMAT_ERROR = "Unrecognised task format.";

    private static final String MESSAGE_GREETING = "Hello! I'm Duke" + LINE_SEPARATOR + "What can I do for you?";
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
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Main entry point of Duke.
     */
    public static void main(String[] args) {
        loadData();
        printGreeting();
        while (true) {
            final String userInput = getUserInput();
            final String feedback = executeCommand(userInput);
            printResponseBlock(feedback);
        }
    }

    private static void printGreeting() {
        printResponseBlock(MESSAGE_GREETING);
    }

    /**
     * Prints out the specified text formatted as a response block.
     * Horizontal lines will be printed before and after the specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    private static void printResponseBlock(String text) {
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

    private static void loadData() {
        boolean createdDataDirectory = DATA_DIRECTORY_PATH.toFile().mkdir();

        File dataFile = DATA_FILE_PATH.toFile();
        try {
            if (dataFile.createNewFile()) {
                printResponseBlock((createdDataDirectory ? MESSAGE_DATA_DIRECTORY_CREATED + LINE_SEPARATOR : "")
                        + MESSAGE_DATA_FILE_CREATED);
            } else {
                printResponseBlock(MESSAGE_DATA_FILE_FOUND);
                parseDataFromFile(dataFile);
            }
        } catch (IOException e) {
            printResponseBlock(MESSAGE_DATA_FILE_ACCESS_ERROR + LINE_SEPARATOR + e.getMessage());
            System.exit(0);
        }
    }

    private static void parseDataFromFile(File dataFile) throws FileNotFoundException {
        final Scanner scanner = new Scanner(dataFile);
        try {
            while (scanner.hasNext()) {
                final String line = scanner.nextLine();
                final String[] args = line.split(DATA_FILE_SEPARATOR);
                Task task = decodeTaskFromString(args);
                tasks.add(task);
            }
        } catch (DukeException e) {
            printResponseBlock(MESSAGE_DATA_FILE_PARSE_ERROR + LINE_SEPARATOR + e.getMessage());
            System.exit(0);
        }
    }

    private static Task decodeTaskFromString(String[] args) throws DukeException {
        if (args.length < 3) {
            throw new DukeException(MESSAGE_TASK_FORMAT_ERROR);
        }
        final String taskTypeIcon = args[0];
        final String statusString = args[1];
        final String description = args[2];
        Task task;
        switch (taskTypeIcon) {
        case Todo.TASK_TYPE_ICON:
            task = new Todo(description);
            break;
        case Event.TASK_TYPE_ICON:
            if (args.length < 4) {
                throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
            }
            final String at = args[3];
            task = new Event(description, at);
            break;
        case Deadline.TASK_TYPE_ICON:
            if (args.length < 4) {
                throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
            }
            final String by = args[3];
            task = new Deadline(description, by);
            break;
        default:
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_TYPE_ICON, taskTypeIcon));
        }
        if (statusString.equals("1")) {
            task.setAsDone();
        } else if (!statusString.equals("0")){
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_STATUS_ICON, statusString));
        }
        return task;
    }

    private static void saveData() {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH.toFile());
            fw.write(formatTasksAsDataOutput());
            fw.close();
        } catch (IOException e) {
            printResponseBlock(MESSAGE_DATA_FILE_ACCESS_ERROR + LINE_SEPARATOR + e.getMessage());
            System.exit(0);
        }
    }

    private static String formatTasksAsDataOutput() {
        ArrayList<String> taskDataStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskDataStrings.add(task.toDataString());
        }
        return String.join(LINE_SEPARATOR, taskDataStrings);
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
        Task task = new Todo(description);
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
        tasks.add(task);
        saveData();
        return String.format(MESSAGE_TASK_ADDED, task, tasks.size());
    }

    private static String deleteTask(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        tasks.remove(task);
        saveData();
        return String.format(MESSAGE_TASK_DELETED, task, tasks.size());
    }

    /** Returns the list of tasks (numbered) together with their status icons */
    private static String listTasks() {
        String[] formattedTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.get(i));
        }
        String taskListOutput = String.join(LINE_SEPARATOR, formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }

    private static String markTaskAsDone(String args) throws DukeException {
        Task task = getTaskFromStringId(args);
        task.setAsDone();
        saveData();
        return String.format(MESSAGE_TASK_MARKED_AS_DONE, task);
    }

    private static Task getTaskFromStringId(String args) throws DukeException {
        try {
            int taskId = Integer.parseInt(args) - 1;
            return tasks.get(taskId);
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
