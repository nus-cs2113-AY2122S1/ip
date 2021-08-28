import java.util.Scanner;

public class Duke {

    /**
     * A string to append before and after a print message.
     */
    final static String LINE = "____________________________________________________________";

    /**
     * Main function to run the bot.
     *
     * @param args Arguments from console input
     */
    public static void main(String[] args) {
        System.out.println(LINE);
        printLogoMessage();
        printWelcomeMessage();
        getMenu();
    }

    /**
     * Prints the exit message when user quits the program.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message when user first runs the program.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the logo of the bot.
     */
    public static void printLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Get the menu for user to input commands for the bot to perform.
     */
    public static void getMenu() {
        Scanner in = new Scanner(System.in);
        String userInputs = in.nextLine();
        TaskManager taskManager = new TaskManager();
        String[] taskInfo;
        menuLoop:
        while (true) {
            System.out.println(LINE);
            switch (getUserCommand(userInputs)) {
            case "todo":
            case "event":
            case "deadline":
                processTaskInfo(taskManager, userInputs);
                break;
            case "done":
                int taskIndex = getTaskIndexFromUserInputs(userInputs);
                taskManager.setTaskToDone(taskIndex);
                break;
            case "list":
                taskManager.printAllTasks();
                break;
            case "bye":
                printExitMessage();
                break menuLoop;
            default:
                System.out.println("Error: Invalid command.");
                break;
            }
            System.out.println(LINE);
            userInputs = in.nextLine();
        }
    }

    public static String getUserCommand(String userInputs) {
        return userInputs.split(" ")[0];
    }

    public static String getUserPayload(String userInputs) {
        String[] payload = userInputs.split(" ");
        payload[0] = "";
        return String.join(" ", payload).trim();
    }

    public static int getTaskIndexFromUserInputs(String userInputs) {
        int result = -1;
        try {
            result = Integer.parseInt(userInputs.split(" ")[1]);
        } catch (Exception exception) {
            System.out.println("Error: Incorrect format detected.");
        }
        return result;
    }


    public static void processTaskInfo(TaskManager taskManager, String userInputs) {
        String payload = getUserPayload(userInputs);
        String userCommand = getUserCommand(userInputs);
        String[] newPayload = new String[0];
        if (payload.equals("")) {
            System.out.println("Error: Missing Task Info.");
            return;
        }
        switch (userCommand) {
        case "todo":
            taskManager.createToDoTask(payload);
            break;
        case "deadline":
            newPayload = payload.split("/by");
            if (newPayload.length < 2) {
                System.out.println("Error: Missing /by tag.");
            } else {
                taskManager.createDeadlineTask(newPayload[0], newPayload[1]);
            }
            break;
        case "event":
            newPayload = payload.split("/at");
            if (newPayload.length < 2) {
                System.out.println("Error: Missing /at tag.");
            } else {
                taskManager.createEventTask(newPayload[0], newPayload[1]);
            }
            break;
        }
    }

}
