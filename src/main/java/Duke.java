import java.util.Scanner;

public class Duke {

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final String HELP_MESSAGE = "Valid Commands: " + System.lineSeparator()
            + "todo (description of task)" + System.lineSeparator()
            + "event (description of event) /at (time of event)" + System.lineSeparator()
            + "deadline (description of task) /by (deadline of task)" +System.lineSeparator()
            + "list" + System.lineSeparator()
            + "bye";

    public static void printMessage(String message) {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE + System.lineSeparator() + message + System.lineSeparator()
                + HORIZONTAL_LINE);
    }

    //Made this as a separate function so that main function doesn't become too big
    public static void printStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            //Starting message
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            printMessage("Hello! I'm Duke" + System.lineSeparator()
                    + "What can I do for you?");
        } else {
            //Ending message
            printMessage("Bye. Hope to see you again soon!");
        }
    }

    public static void main(String[] args) {
        printStartingOrEndingMessage(true);
        //initialise Scanner and TaskManager
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            String inputCommand = in.nextLine().trim();
            //switch to lowercase so that Duke won't be case-sensitive
            String command = inputCommand.toLowerCase().split(" ")[0];
            switch (command) {
            case "list":
                taskManager.printTasks();
                break;
            case "done":
                taskManager.setTaskAsDone(Integer.parseInt(inputCommand.split(" ")[1]));
                break;
            case "bye":
                conversationIsOver = true;
                break;
            case "deadline":
                //to remove the word "deadline"
                String deadlineInput = inputCommand.substring(8).trim();
                taskManager.addDeadline(deadlineInput);
                break;
            case "todo":
                //to remove the word "todo"
                String todoInput = inputCommand.substring(4).trim();
                taskManager.addTodo(todoInput);
                break;
            case "event":
                //to remove the word "event"
                String eventInput = inputCommand.substring(5).trim();
                taskManager.addEvent(eventInput);
                break;
            default:
                printMessage(HELP_MESSAGE);
                break;
            }
        }
        printStartingOrEndingMessage(false);
    }
}
