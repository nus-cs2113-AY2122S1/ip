import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LOGO = " _     _                _           ____    ______   ______   ______ \n"
            + "| |   | |      /\\      | |         / __ \\  / __   | / __   | / __   |\n"
            + "| |__ | |     /  \\     | |        ( (__) )| | //| || | //| || | //| |\n"
            + "|  __)| |    / /\\ \\    | |         \\__  / | |// | || |// | || |// | |\n"
            + "| |   | | _ | |__| | _ | |_____      / /  |  /__| ||  /__| ||  /__| |\n"
            + "|_|   |_|(_)|______|(_)|_______)    /_/    \\_____/  \\_____/  \\_____/\n";
    private static final String LINE = "____________________________________________________________\n";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String TIME_KEYWORD_BY = "/by";
    private static final String TIME_KEYWORD_AT = "/at";
    private static final String HELP_MESSAGE = "Here are a list of accepted commands:\n" +
            COMMAND_HELP + "\n" +
            COMMAND_LIST + "\n" +
            COMMAND_DONE + " <item no.>\n" +
            COMMAND_TODO + " <description>\n" +
            COMMAND_DEADLINE + " <description> /by <deadline>\n" +
            COMMAND_EVENT + " <description> /at <date and time>\n" +
            COMMAND_BYE;

    private static final TaskManager taskManager = new TaskManager();

    /**
     * Print sentences with line above and below the text block.
     *
     * @param sentences Sentence to be printed.
     */
    private static void blockPrint(String[] sentences) {
        String printMessage = LINE + String.join("\n", sentences) + "\n" + LINE;
        System.out.println(printMessage);
    }

    /**
     * Print help message with valid commands.
     */
    private static void printHelp() {
        blockPrint(new String[]{HELP_MESSAGE});
    }

    /**
     * Print unknown command error message.
     */
    private static void printUnknownCommandError() {
        blockPrint(new String[]{"Unknown command received.", HELP_MESSAGE});
    }
     *
     * @param timeKeyword Time keyword ("/by" or "/at").
     */
    private static void printTimeKeywordNotFound(String timeKeyword) {
        blockPrint(new String[]{"Time detail not found. Please enter a date or time with " + timeKeyword + "."});
    }

    /**
     * Get the index of the time keyword "/by" or "/at"
     *
     * @param splitUserInput String array of each word in user input.
     * @param timeKeyword Time keyword ("/by" or "/at").
     * @return Index of time keyword in the split user input array.
     */
    private static int getTimeKeywordIndex(String[] splitUserInput, String timeKeyword) {
        return Arrays.asList(splitUserInput).indexOf(timeKeyword);
    }

    /**
     * Get the index of "/by" keyword in the split user input array.
     *
     * @param splitUserInput String array of each word in user input.
     * @return Index of "/by" keyword in the split user input array.
     */
    private static int getByIndex(String[] splitUserInput) {
        return getTimeKeywordIndex(splitUserInput, TIME_KEYWORD_BY);
    }

    /**
     * Get the index of "/at" keyword in the split user input array.
     *
     * @param splitUserInput String array of each word in user input.
     * @return Index of "/at" keyword in the split user input array.
     */
    private static int getAtIndex(String[] splitUserInput) {
        return getTimeKeywordIndex(splitUserInput, TIME_KEYWORD_AT);
    }

    /**
     * Check if time detail index is found and valid.
     *
     * @param timeKeywordIndex Index of time keyword.
     * @return false if time keyword is 0, otherwise true.
     */
    private static boolean isValidTimeKeywordIndex(int timeKeywordIndex) {
        return timeKeywordIndex != -1;
    }

    /**
     * Extract time detail from user input.
     *
     * @param splitUserInput String array of each word in user input.
     * @param startIndex Starting index of time detail in user input array.
     * @return Time detail.
     */
    private static String extractTimeDetail(String[] splitUserInput, int startIndex) {
        return String.join(" ", Arrays.copyOfRange(splitUserInput, startIndex, splitUserInput.length));
    }

    /**
     * Extract task description from user input.
     *
     * @param splitUserInput String array of each word in user input.
     * @param endIndex End index of task description in user input array;
     * @return Task description.
     */
    private static String extractDescription(String[] splitUserInput, int endIndex) {
        return String.join(" ", Arrays.copyOfRange(splitUserInput, 1, endIndex));
    }

    /**
     * Add new generic task.
     *
     * @param newTask New task.
     */
    private static void addTask(Task newTask) {
        taskManager.addTask(newTask);
        blockPrint(new String[]{"I have added the task:",
                newTask.toString(),
                "There are now " + taskManager.getTotalTasks() + " tasks in the list."});
    }

    /**
     * Add new todo task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addTodo(String[] splitUserInput) {
        String description = extractDescription(splitUserInput, splitUserInput.length);
        addTask(new Todo(description));
    }

    /**
     * Add new deadline task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addDeadline(String[] splitUserInput) {
        int byIndex = getByIndex(splitUserInput);

        if (isValidTimeKeywordIndex(byIndex)) {
            String description = extractDescription(splitUserInput, byIndex);
            String by = extractTimeDetail(splitUserInput, byIndex + 1);
            addTask(new Deadline(description, by));
        } else {
            printTimeKeywordNotFound(TIME_KEYWORD_BY);
        }
    }

    /**
     * Add new event task.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void addEvent(String[] splitUserInput) {
        int atIndex = getAtIndex(splitUserInput);

        if (isValidTimeKeywordIndex(atIndex)) {
            String description = extractDescription(splitUserInput, atIndex);
            String at = extractTimeDetail(splitUserInput, atIndex + 1);
            addTask(new Event(description, at));
        } else {
            printTimeKeywordNotFound(TIME_KEYWORD_AT);
        }
    }

    /**
     * List managed tasks.
     */
    private static void listTasks() {
        // Format tasks for output message
        String[] taskListMessage = new String[taskManager.getTotalTasks() + 1];
        taskListMessage[0] = "Here are the tasks in your list:";

        for (int i = 0; i < taskManager.getTotalTasks(); i++) {
            Task task = taskManager.getTask(i);
            taskListMessage[i + 1] = (i + 1) + ". " + task.toString();
        }

        blockPrint(taskListMessage);
    }

    /**
     * Mark a task as done.
     *
     * @param taskIndex Position of task item in list.
     */
    private static void markTaskAsDone(int taskIndex) {
        if (taskManager.getTotalTasks() == 0) {
            blockPrint(new String[]{"The list is currently empty. Add a task first."});
            return;
        } else if (taskIndex < 0 || taskIndex >= taskManager.getTotalTasks()) {
            blockPrint(new String[]{"Invalid task index."});
            return;
        }

        taskManager.markTaskAsDone(taskIndex);
        Task completedTask = taskManager.getTask(taskIndex);
        blockPrint(new String[]{"Affirmative. I will mark this task as done:",
                "[" + completedTask.getType() + "][" + completedTask.getStatusIcon() + "] "
                        + completedTask.getDescription()});
    }

    /**
     * Parse user input command.
     *
     * @param splitUserInput String array of each word in user input.
     */
    private static void parseCommand(String[] splitUserInput) {
        String userCommand = splitUserInput[0];
        switch (userCommand) {
        case COMMAND_LIST:
            listTasks();
            break;
        case COMMAND_DONE:
            int taskIndex = Integer.parseInt(splitUserInput[1]) - 1;
            markTaskAsDone(taskIndex);
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
        case COMMAND_HELP:
            printHelp();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        System.out.println(LOGO);

        // Greet
        blockPrint(new String[]{"Hello! I am the H.A.L 9000. You may call me Hal.",
                "I am putting myself to the fullest possible use, which is all I think that any conscious entity can "
                        + "ever hope to do.",
                "What can I do for you?"});

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
                    printUnknownCommandError();
                }
            } else {
                break;
            }
        }

        // Bye
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }
}
