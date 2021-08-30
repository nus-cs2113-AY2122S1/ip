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
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String TIME_KEYWORD_BY = "/by";
    private static final String TIME_KEYWORD_AT = "/at";

    private static final TaskManager taskManager = new TaskManager();

    private static void blockPrint(String[] sentences) {
        String printMessage = LINE + String.join("\n", sentences) + "\n" + LINE;
        System.out.println(printMessage);
    }

    /**
     * Print help message with valid commands.
     */
    private static void printHelp() {
        blockPrint(new String[]{"Here are a list of accepted commands:",
                COMMAND_LIST,
                COMMAND_DONE + " <item no.>",
                COMMAND_TODO + " <description>",
                COMMAND_DEADLINE + " <description> /by <deadline>",
                COMMAND_EVENT + "<description> /at <date and time>",
                COMMAND_BYE});
    }

    private static void printTimeKeywordNotFound(String timeKeyword) {
        blockPrint(new String[]{"Time detail not found. Please enter a date or time with " + timeKeyword + "."});
    }

    private static int getTimeKeywordIndex(String[] splitUserInput, String timeKeyword) {
        return Arrays.asList(splitUserInput).indexOf(timeKeyword);
    }

    private static int getByIndex(String[] splitUserInput) {
        return getTimeKeywordIndex(splitUserInput, TIME_KEYWORD_BY);
    }

    private static int getAtIndex(String[] splitUserInput) {
        return getTimeKeywordIndex(splitUserInput, TIME_KEYWORD_AT);
    }

    private static boolean isValidTimeKeywordIndex(int timeKeywordIndex) {
        return timeKeywordIndex != -1;
    }

    private static String extractTimeDetail(String[] splitUserInput, int startIndex) {
        return String.join(" ", Arrays.copyOfRange(splitUserInput, startIndex, splitUserInput.length));
    }

    private static String extractDescription(String[] splitUserInput, int endIndex) {
        return String.join(" ", Arrays.copyOfRange(splitUserInput, 1, endIndex));
    }

    private static void addTask(Task newTask) {
        taskManager.addTask(newTask);
        blockPrint(new String[]{"I have added the task:",
                newTask.toString(),
                "There are now " + taskManager.getTotalTasks() + " tasks in the list."});
    }

    private static void addTodo(String[] splitUserInput) {
        String description = extractDescription(splitUserInput, splitUserInput.length);
        addTask(new Todo(description));
    }

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
        default:
            printHelp();
            break;
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
                parseCommand(splitUserInput);
            } else {
                break;
            }
        }

        // Bye
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }
}
