import java.util.Scanner;

public class Duke {

    public static final boolean IS_STARTING = true;
    public static final boolean IS_ENDING = false;

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final String HELP_MESSAGE = "Valid Commands: " + System.lineSeparator()
            + "todo (description of task)" + System.lineSeparator()
            + "event (description of event) /at (time of event)" + System.lineSeparator()
            + "deadline (description of task) /by (deadline of task)" + System.lineSeparator()
            + "list" + System.lineSeparator()
            + "bye";

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String STARTING_MESSAGE = "Hello from" + System.lineSeparator()
            + LOGO + System.lineSeparator()
            + "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    public static final String ENDING_MESSAGE = "Bye. Hope to see you again soon!";

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final TaskManager TASK_MANAGER = new TaskManager();

    public static void printMessage(String message) {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE + System.lineSeparator() + message + System.lineSeparator()
                + HORIZONTAL_LINE);
    }

    //Made this as a separate function so that main function doesn't become too big
    public static void printStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            printMessage(STARTING_MESSAGE);
        } else {
            printMessage(ENDING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        printStartingOrEndingMessage(IS_STARTING);
        runDuke();
        printStartingOrEndingMessage(IS_ENDING);
    }

    private static void runDuke() {
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            String inputCommand = SCANNER.nextLine().trim();
            String command = getFirstWord(inputCommand);
            switch (command) {
            case "list":
                TASK_MANAGER.printTasks();
                break;
            case "done":
                int indexOfTaskDone = getIntegerFromCommand(inputCommand);
                TASK_MANAGER.setTaskAsDone(indexOfTaskDone);
                break;
            case "bye":
                conversationIsOver = true;
                break;
            case "deadline":
                String deadlineInput = removeFirstWordInSentence(inputCommand, 8);
                TASK_MANAGER.addDeadline(deadlineInput);
                break;
            case "todo":
                String todoInput = removeFirstWordInSentence(inputCommand, 4);
                TASK_MANAGER.addTodo(todoInput);
                break;
            case "event":
                String eventInput = removeFirstWordInSentence(inputCommand, 5);
                TASK_MANAGER.addEvent(eventInput);
                break;
            default:
                printMessage(HELP_MESSAGE);
                break;
            }
        }
    }

    private static String removeFirstWordInSentence(String inputCommand, int i) {
        //to remove the words "deadline", "even" or "todo"
        return inputCommand.substring(i).trim();
    }

    private static int getIntegerFromCommand(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    private static String getFirstWord(String inputCommand) {
        //switch to lowercase so that Duke won't be case-sensitive
        return inputCommand.toLowerCase().split(" ")[0];
    }
}
