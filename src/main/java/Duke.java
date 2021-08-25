import java.util.Locale;
import java.util.Scanner;

public class Duke {

    /* A nicely formatted line */
    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

    /**
     * Prints given string in the middle of 2 horizontal lines.
     *
     * @param message String to be printed
     */
    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        // Set welcome and exit message for user
        String welcomeMessage = "Hello! I'm Gaben.\n"
                + "Steam sales is coming! Prepare your wallet.\n"
                + "Anyways, what can I do for you today?";
        String exitMessage = "Thank you for using Gaben.\n"
                + "Remember to keep your wallet stacked before using me again!";

        // Print welcome message to user
        printMessage(welcomeMessage);
        // Create task manager to manage task given by user
        TaskManager taskManager = new TaskManager();
        // Create a scanner to read user input
        Scanner in = new Scanner(System.in);
        // Boolean to allow continuous listening of user input
        boolean exit = false;
        // Listen for user input and do commands given by user till user wants to exit program
        while (!exit) {
            String userInput = in.nextLine();
            switch (userInput.split(" ")[0].toLowerCase(Locale.ROOT)) {
            case "list":
                taskManager.listTask();
                break;
            case "bye":
                exit = true;
                break;
            case "done":
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    taskManager.completeTask(taskNumber);
                } catch (NumberFormatException e) {
                    printMessage("Error in detecting task number. Please enter a valid number after done, i.e: done 1");
                }
                break;
            default:
                taskManager.addTask(userInput);
                break;
            }
        }
        printMessage(exitMessage);
    }
}
