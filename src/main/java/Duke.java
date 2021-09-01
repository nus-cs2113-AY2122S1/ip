import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final char TASK_TYPE_TODO = 'T';
    public static final char TASK_TYPE_DEADLINE = 'D';
    public static final char TASK_TYPE_EVENT = 'E';

    private static final String INPUT_PROMPT = "$ ";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String TASK_DEADLINE_SPLITTER = "/by";
    private static final String TASK_EVENT_SPLITTER = "/at";

    private static final String MESSAGE_WALL = "------------------------------------------------------------";
    private static final String MESSAGE_LIST_EMPTY = "List is empty";
    private static final String MESSAGE_LIST_HEADER = "Task List:";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    private static final String MESSAGE_FORMAT_DONE_USAGE = "Usage: %s <task number>";
    private static final String MESSAGE_FORMAT_TASK_ALREADY_MARKED = "Task #%d is already marked as done";
    private static final String MESSAGE_FORMAT_TASK_MARKED = "Task marked as done:\n  %s";
    private static final String MESSAGE_FORMAT_DEADLINE_USAGE = "Usage: %s <name> %s <date/time>";
    private static final String MESSAGE_FORMAT_EVENT_USAGE = "Usage: %s <name> %s <date/time>";
    private static final String MESSAGE_FORMAT_TASK_ADDED = "Got it. Task added:\n%s\nThere are %d tasks in the list";

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
     *
     * @param header List header.
     */
    private static void printTasks(String header) {
        StringBuilder messageBuilder = new StringBuilder(header);
        for (int i = 0; i < TASKS.size(); i += 1) {
            messageBuilder.append("\n");
            messageBuilder.append(i + 1);
            messageBuilder.append(": ");
            messageBuilder.append(TASKS.get(i).getListEntryString());
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
        String[] result = input.trim().split("\\s+",2);
        return (result.length == 2) ? result : new String[] { result[0] , "" };
    }

    /**
     * Executes the list command. Print tasks in the list.
     */
    private static void executeListCommand() {
        if (TASKS.isEmpty()) {
            printMessage(MESSAGE_LIST_EMPTY);
        } else {
            printTasks(MESSAGE_LIST_HEADER);
        }
    }

    /**
     * Executes the done command. Marks the given task as done.
     */
    private static void executeDoneCommand(String argument) {
        if (!isInteger(argument)) {
            printMessage(String.format(MESSAGE_FORMAT_DONE_USAGE,COMMAND_DONE));
            return;
        }

        int taskIndex = Integer.parseInt(argument);
        if (taskIndex < 0 || taskIndex > TASKS.size()) {
            printMessage(MESSAGE_INVALID_TASK_NUMBER);
            return;
        }

        Task task = TASKS.get(taskIndex - 1);
        if (task.isDone()) {
            printMessage(String.format(MESSAGE_FORMAT_TASK_ALREADY_MARKED,taskIndex));
        } else {
            task.setAsDone();
            printMessage(String.format(MESSAGE_FORMAT_TASK_MARKED,task.getListEntryString()));
        }
    }

    /**
     * Gets the task description and argument.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @return String array: [0] - Description, [1] - Argument Value.
     */
    private static String[] getTaskDescriptionAndArg(String argument, String splitString) {
        String[] argSplit = argument.split(splitString,2);
        argSplit[0] = argSplit[0].trim();
        if (argSplit.length == 2) {
            argSplit[1] = argSplit[1].trim();
            return argSplit;
        }

        return new String[] { argSplit[0], "" };
    }

    /**
     * Executes the add task command. Adds a task depending on type.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @param taskType The type of task.
     */
    private static void executeAddTask(String argument, char taskType) {
        Task task = null;

        String[] descriptionAndArg;
        switch (taskType) {
        case TASK_TYPE_TODO:
            task = new Todo(argument);
            break;

        case TASK_TYPE_DEADLINE:
            descriptionAndArg = getTaskDescriptionAndArg(argument,TASK_DEADLINE_SPLITTER);
            if (descriptionAndArg[1].isEmpty()) {
                printMessage(String.format(MESSAGE_FORMAT_DEADLINE_USAGE, COMMAND_DEADLINE, TASK_DEADLINE_SPLITTER));
            } else {
                task = new Deadline(descriptionAndArg[0], descriptionAndArg[1]);
            }
            break;

        case TASK_TYPE_EVENT:
            descriptionAndArg = getTaskDescriptionAndArg(argument,TASK_EVENT_SPLITTER);
            if (descriptionAndArg[1].isEmpty()) {
                printMessage(String.format(MESSAGE_FORMAT_EVENT_USAGE, COMMAND_EVENT, TASK_EVENT_SPLITTER));
            } else {
                task = new Event(descriptionAndArg[0], descriptionAndArg[1]);
            }
            break;
        }

        if (task != null) {
            TASKS.add(task);
            printMessage(String.format(MESSAGE_FORMAT_TASK_ADDED, task.getListEntryString(), TASKS.size()));
        }
    }

    /**
     * Executes a command.
     *
     * @param command The command from getCommandAndArgument(<string>).
     * @param argument The argument from getCommandAndArgument(<string>).
     */
    private static void executeCommand(String command, String argument) {
        switch (command) {
        case COMMAND_LIST:
            executeListCommand();
            break;

        case COMMAND_DONE:
            executeDoneCommand(argument);
            break;

        case COMMAND_TODO:
            executeAddTask(argument, TASK_TYPE_TODO);
            break;

        case COMMAND_DEADLINE:
            executeAddTask(argument, TASK_TYPE_DEADLINE);
            break;

        case COMMAND_EVENT:
            executeAddTask(argument, TASK_TYPE_EVENT);
            break;

        default:
            printMessage(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public static void main(String[] args) {
        printMessage(MESSAGE_WELCOME);

        boolean run = true;
        do {
            String input = getUserInput();
            String[] commandAndArgument = getCommandAndArgument(input);
            String command = commandAndArgument[0];
            String argument = commandAndArgument[1];

            if (command.equals(COMMAND_BYE)) {
                run = false;
            } else {
                executeCommand(command, argument);
            }
        } while (run);

        printMessage(MESSAGE_BYE);
    }
}
