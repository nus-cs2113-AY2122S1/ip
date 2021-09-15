package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String INPUT_PROMPT = "$ ";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final String TASK_DEADLINE_SPLITTER = "/by";
    private static final String TASK_EVENT_SPLITTER = "/at";

    private static final String MESSAGE_WALL = "------------------------------------------------------------";
    private static final String MESSAGE_LIST_EMPTY = "Task list is empty";
    private static final String MESSAGE_LIST_HEADER = "Task List:";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_INVALID_TASK_TYPE = "Invalid task type";

    private static final String MESSAGE_FORMAT_DONE_USAGE = "Usage: %s <task number>";
    private static final String MESSAGE_FORMAT_TODO_USAGE = "Usage: %s <description>";
    private static final String MESSAGE_FORMAT_DEADLINE_USAGE = "Usage: %s <description> %s <date/time>";
    private static final String MESSAGE_FORMAT_EVENT_USAGE = "Usage: %s <description> %s <date/time>";
    private static final String MESSAGE_FORMAT_DELETE_USAGE = "Usage: %s <task number>";

    private static final String MESSAGE_FORMAT_TASK_ALREADY_MARKED = "Task #%d is already marked as done.";
    private static final String MESSAGE_FORMAT_TASK_MARKED = "Task marked as done:\n  %s";
    private static final String MESSAGE_FORMAT_TASK_ADDED = "Got it. Task added:\n  %s\nThere are %d tasks in the list.";
    private static final String MESSAGE_FORMAT_EXCEPTION = "An exception has occurred.\n%s";
    private static final String MESSAGE_FORMAT_TASK_DELETED = "Task deleted:\n  %s\nThere are %d tasks left in the list.";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Prints message within horizontal lines.
     *
     * @param message The message to print.
     */
    private static void printMessage(String message) {
        System.out.println(MESSAGE_WALL);
        System.out.println(message);
        System.out.println(MESSAGE_WALL);
    }

    /**
     * Prints tasks in list format.
     */
    private static void printTasks() {
        StringBuilder messageBuilder = new StringBuilder(MESSAGE_LIST_HEADER);
        for (int i = 0; i < TASKS.size(); i += 1) {
            messageBuilder.append("\n");
            messageBuilder.append(i + 1);
            messageBuilder.append(": ");
            messageBuilder.append(TASKS.get(i));
        }

        printMessage(messageBuilder.toString());
    }

    /**
     * Checks if string value is an integer.
     *
     * @param string The string to check.
     * @return true if string can be converted to integer, else false.
     */
    private static boolean isInteger(String string) {
        boolean isInt;
        try {
            int value = Integer.parseInt(string);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        }

        return isInt;
    }

    /**
     * Gets a non-empty user input.
     *
     * @return Non-empty user input.
     */
    private static String getUserInput() {
        String input;
        do {
            System.out.print(INPUT_PROMPT);
            input = SCANNER.nextLine();
        } while (input.trim().isEmpty());

        return input;
    }

    /**
     * Splits user input string into command and argument.
     *
     * @param input The user input string.
     * @return String array: [0] - Command, [1] - argument.
     */
    private static String[] getCommandAndArgument(String input) {
        String[] result = input.trim().split("\\s+", 2);
        return (result.length == 2) ? result : new String[]{result[0], ""};
    }

    /**
     * Executes the list command. Print tasks in the list.
     */
    private static void executeListCommand() {
        if (TASKS.isEmpty()) {
            printMessage(MESSAGE_LIST_EMPTY);
        } else {
            printTasks();
        }
    }

    /**
     * Checks if task index is valid.
     * Note: Task index starts from 1 (not 0).
     *
     * @param taskNumber The task index.
     * @return true if task index is valid, else false.
     */
    private static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber > 0 && taskNumber <= TASKS.size());
    }

    /**
     * Executes the done command. Marks the given task as done.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @throws DukeException If argument is invalid/missing or task is already marked as done.
     */
    private static void executeDoneCommand(String argument) throws DukeException {
        if (!isInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DONE_USAGE, COMMAND_DONE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        // Get task object associated to the task index from list
        Task task = TASKS.get(taskNumber - 1);

        if (task.isDone()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_TASK_ALREADY_MARKED, taskNumber));
        }

        task.setAsDone();
        printMessage(String.format(MESSAGE_FORMAT_TASK_MARKED, task));
    }

    /**
     * Gets the task description and argument.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @param splitString The string to split at.
     * @return String array: [0] - Description, [1] - Argument Value.
     */
    private static String[] getTaskDescriptionAndArg(String argument, String splitString) {
        String[] argSplit = argument.split(splitString, 2);
        argSplit[0] = argSplit[0].trim();
        if (argSplit.length == 2) {
            argSplit[1] = argSplit[1].trim();
            return argSplit;
        }

        return new String[]{argSplit[0], ""};
    }

    /**
     * Executes the add task command. Adds a task depending on type.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @param taskType The type of task.
     * @throws DukeException If command format is incorrect or taskType is invalid.
     */
    private static void executeAddTask(String argument, char taskType) throws DukeException {
        Task task = null;

        String[] descriptionAndArg;
        switch (taskType) {
        case Task.TYPE_TODO:
            if (argument.isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_TODO_USAGE, COMMAND_TODO));
            }
            task = new Todo(argument);
            break;

        case Task.TYPE_DEADLINE:
            descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_DEADLINE_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_DEADLINE_USAGE, COMMAND_DEADLINE, TASK_DEADLINE_SPLITTER));
            }
            task = new Deadline(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        case Task.TYPE_EVENT:
            descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_EVENT_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_EVENT_USAGE, COMMAND_EVENT, TASK_EVENT_SPLITTER));
            }
            task = new Event(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        default:
            throw new DukeException(MESSAGE_INVALID_TASK_TYPE);
        }

        TASKS.add(task);
        printMessage(String.format(MESSAGE_FORMAT_TASK_ADDED, task, TASKS.size()));
    }

    /**
     * TODO
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @throws DukeException If argument is invalid/missing.
     */
    private static void executeDeleteCommand(String argument) throws DukeException {
        if (!isInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DELETE_USAGE, COMMAND_DELETE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        Task removedTask = TASKS.remove(taskNumber - 1);
        printMessage(String.format(MESSAGE_FORMAT_TASK_DELETED, removedTask, TASKS.size()));
    }

    /**
     * Executes a command.
     *
     * @param command  The command from getCommandAndArgument(<string>).
     * @param argument The argument from getCommandAndArgument(<string>).
     */
    private static void executeCommand(String command, String argument) {
        try {
            switch (command) {
            case COMMAND_LIST:
                executeListCommand();
                break;

            case COMMAND_DONE:
                executeDoneCommand(argument);
                break;

            case COMMAND_TODO:
                executeAddTask(argument, Task.TYPE_TODO);
                break;

            case COMMAND_DEADLINE:
                executeAddTask(argument, Task.TYPE_DEADLINE);
                break;

            case COMMAND_EVENT:
                executeAddTask(argument, Task.TYPE_EVENT);
                break;

            case COMMAND_DELETE:
                executeDeleteCommand(argument);
                break;

            default:
                throw new DukeException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (DukeException e) {
            printMessage(String.format(MESSAGE_FORMAT_EXCEPTION, e.getMessage()));
        }
    }

    public static void main(String[] args) {
        printMessage(MESSAGE_WELCOME);

        boolean isRunning = true;
        do {
            String input = getUserInput();
            String[] commandAndArgument = getCommandAndArgument(input);
            String command = commandAndArgument[0];
            String argument = commandAndArgument[1];

            if (command.equals(COMMAND_BYE)) {
                isRunning = false;
            } else {
                executeCommand(command, argument);
            }
        } while (isRunning);

        printMessage(MESSAGE_BYE);
    }
}
