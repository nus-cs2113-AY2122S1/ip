package duke;

import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.exception.EmptyDescriptionException;
import duke.task.exception.EmptyTimeDetailException;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import duke.task.exception.TimeSpecifierNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    protected static final String COMMAND_HELP = "help";
    protected static final String COMMAND_LIST = "list";
    protected static final String COMMAND_DONE = "done";
    protected static final String COMMAND_BYE = "bye";
    protected static final String COMMAND_TODO = "todo";
    protected static final String COMMAND_DEADLINE = "deadline";
    protected static final String COMMAND_EVENT = "event";
    protected static final String COMMAND_DELETE = "delete";
    private static final String TIME_SPECIFIER_BY = "/by";
    private static final String TIME_SPECIFIER_AT = "/at";

    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();

    /**
     * Load task list from file.
     */
    private static void loadTaskList() {
        try {
            taskList = storage.loadTaskList();
            ui.printSaveFileFound();
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        }
    }

    /**
     * Save task list to file.
     */
    private static void saveTaskList() {
        try {
            storage.saveTaskList(taskList);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        } catch (IOException e) {
            ui.printTaskFileSavingError();
        }
    }

    /**
     * Check if time detail index is found and valid.
     *
     * @param timeSpecifierIndex Index of time specifier.
     * @return false if index of time specifier is 0, otherwise true.
     */
    private static boolean isTimeSpecifierFound(int timeSpecifierIndex) {
        return timeSpecifierIndex != -1;
    }

    /**
     * Get the index of the time specifier "/by" or "/at".
     *
     * @param splitUserInput String array of each word in user input.
     * @param timeSpecifier  Time specifier TIME_SPECIFIER_BY or TIME_SPECIFIER_AT.
     * @return Index of time specifier in the split user input array.
     * @throws TimeSpecifierNotFoundException Time specifier "/by" or "/at" is not found in user input.
     */
    private static int getTimeSpecifierIndex(String[] splitUserInput, String timeSpecifier)
            throws TimeSpecifierNotFoundException {
        int timeSpecifierIndex = Arrays.asList(splitUserInput).indexOf(timeSpecifier);

        if (!isTimeSpecifierFound(timeSpecifierIndex)) {
            throw new TimeSpecifierNotFoundException();
        }

        return timeSpecifierIndex;
    }

    /**
     * Get the index of "/by" specifier in the split user input array.
     *
     * @param splitUserInput String array of each word in user input.
     * @return Index of "/by" specifier in the split user input array.
     * @throws TimeSpecifierNotFoundException "/by" specifier is not found in user input.
     */
    private static int getByIndex(String[] splitUserInput) throws TimeSpecifierNotFoundException {
        return getTimeSpecifierIndex(splitUserInput, TIME_SPECIFIER_BY);
    }

    /**
     * Get the index of "/at" specifier in the split user input array.
     *
     * @param splitUserInput String array of each word in user input.
     * @return Index of "/at" specifier in the split user input array.
     * @throws TimeSpecifierNotFoundException "/at" specifier is not found in user input.
     */
    private static int getAtIndex(String[] splitUserInput) throws TimeSpecifierNotFoundException {
        return getTimeSpecifierIndex(splitUserInput, TIME_SPECIFIER_AT);
    }

    /**
     * Extract time detail from user input.
     *
     * @param splitUserInput String array of each word in user input.
     * @param startIndex     Starting index of time detail in user input array.
     * @return Time detail.
     * @throws EmptyTimeDetailException Time detail not found in user input.
     */
    private static String extractTimeDetail(String[] splitUserInput, int startIndex) throws EmptyTimeDetailException {
        String timeDetail = String.join(" ", Arrays.copyOfRange(splitUserInput, startIndex, splitUserInput.length));

        if (timeDetail.isBlank()) {
            throw new EmptyTimeDetailException();
        }

        return timeDetail;
    }

    /**
     * Extract task description from user input.
     *
     * @param splitUserInput String array of each word in user input.
     * @param endIndex       End index of task description in user input array.
     * @return Task description.
     * @throws EmptyDescriptionException Task description is not provided.
     */
    private static String extractDescription(String[] splitUserInput, int endIndex) throws EmptyDescriptionException {
        String description = String.join(" ", Arrays.copyOfRange(splitUserInput, 1, endIndex));

        if (description.isBlank()) {
            throw new EmptyDescriptionException();
        }

        return description;
    }

    /**
     * Add new generic task.
     *
     * @param newTask New task.
     */
    private static void addTask(Task newTask) {
        taskList.addTask(newTask);
        ui.printAddNewTask(newTask, taskList.getTotalTasks());
        saveTaskList();
    }

    /**
     * Delete a task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void deleteTask(String[] splitUserInput) {
        int taskIndex;
        Task deletedTask;
        try {
            taskIndex = Integer.parseInt(splitUserInput[1]) - 1;
            deletedTask = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
        } catch (NumberFormatException | InvalidTaskIndexException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndexError();
            return;
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        }

        ui.printDeletedTask(deletedTask, taskList.getTotalTasks());
        saveTaskList();
    }

    /**
     * Add new todo task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addTodo(String[] splitUserInput) {
        String description;

        try {
            description = extractDescription(splitUserInput, splitUserInput.length);
        } catch (EmptyDescriptionException e) {
            ui.printDescriptionNotFoundError();
            return;
        }

        addTask(new Todo(description));
    }

    /**
     * Add new deadline task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addDeadline(String[] splitUserInput) {
        int byIndex;
        try {
            byIndex = getByIndex(splitUserInput);
        } catch (TimeSpecifierNotFoundException e) {
            ui.printTimeSpecifierNotFoundError(TIME_SPECIFIER_BY);
            return;
        }

        String description;
        try {
            description = extractDescription(splitUserInput, byIndex);
        } catch (EmptyDescriptionException e) {
            ui.printDescriptionNotFoundError();
            return;
        }

        String by;
        try {
            by = extractTimeDetail(splitUserInput, byIndex + 1);
        } catch (EmptyTimeDetailException e) {
            ui.printTimeDetailNotFoundError(TIME_SPECIFIER_BY);
            return;
        }

        addTask(new Deadline(description, by));
    }

    /**
     * Add new event task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addEvent(String[] splitUserInput) {
        int atIndex;
        try {
            atIndex = getAtIndex(splitUserInput);
        } catch (TimeSpecifierNotFoundException e) {
            ui.printTimeSpecifierNotFoundError(TIME_SPECIFIER_AT);
            return;
        }

        String description;
        try {
            description = extractDescription(splitUserInput, atIndex);
        } catch (EmptyDescriptionException e) {
            ui.printDescriptionNotFoundError();
            return;
        }

        String at;
        try {
            at = extractTimeDetail(splitUserInput, atIndex + 1);
        } catch (EmptyTimeDetailException e) {
            ui.printTimeDetailNotFoundError(TIME_SPECIFIER_AT);
            return;
        }

        addTask(new Event(description, at));
    }

    /**
     * List managed tasks.
     */
    private static void listTasks() {
        try {
            ui.listTasks(taskList);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        }
    }

    /**
     * Mark a task as done.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void markTaskAsDone(String[] splitUserInput) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(splitUserInput[1]) - 1;
            taskList.markTaskAsDone(taskIndex);
        } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndexError();
            return;
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        }

        Task completedTask;
        try {
            completedTask = taskList.getTask(taskIndex);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
            return;
        }

        ui.printMarkAsDone(completedTask);
        saveTaskList();
    }

    /**
     * Parse user input command.
     *
     * @param splitUserInput String array of each word in user input.
     * @throws UnknownCommandException Unknown command received.
     */
    private static void parseCommand(String[] splitUserInput) throws UnknownCommandException {
        String userCommand = splitUserInput[0];
        switch (userCommand) {
        case COMMAND_LIST:
            listTasks();
            break;
        case COMMAND_DONE:
            markTaskAsDone(splitUserInput);
            break;
        case COMMAND_TODO:
            addTodo(splitUserInput);
            break;
        case COMMAND_DEADLINE:
            addDeadline(splitUserInput);
            break;
        case COMMAND_EVENT:
            addEvent(splitUserInput);
            break;
        case COMMAND_DELETE:
            deleteTask(splitUserInput);
            break;
        case COMMAND_HELP:
            ui.printHelp();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        ui.printGreet();
        loadTaskList();

        // User input loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Read input
            String in = scanner.nextLine().trim();
            String[] splitUserInput = in.split(" ");
            String userCommand = splitUserInput[0];

            // Commands
            if (!userCommand.equals(COMMAND_BYE)) {
                try {
                    parseCommand(splitUserInput);
                } catch (UnknownCommandException e) {
                    ui.printUnknownCommandError();
                }
            } else {
                break;
            }
        }

        ui.printBye();
    }
}
