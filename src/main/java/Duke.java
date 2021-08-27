import java.util.Scanner;

public class Duke {

    private static String logo = "████████ ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██ ██      ██      \n" +
            "   ██    ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██      ██      ██ \n" +
            "   ██    ██   ██ ██ ███████ ███████ \n";

    // Initialise boolean to track if user has said "bye"
    private static boolean hasUserSaidBye = false;

    // Initialise array to keep track of user's tasks
    private static Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    // Initialise variable to track user input
    private static String userInput = null;


    public static void main(String[] args) {
        // Print logo and welcome text
        printWelcomeMessage();

        // Initialise user input reader
        Scanner in = new Scanner(System.in);

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            String userInput = in.nextLine();
            System.out.println("____________________________________________________________");

            // Check if user has said "bye"
            if (userInput.equals("bye")) {
                printShutdownMessage();
            } else if (userInput.equals("list")) {
                printAllTasks();
            } else if (userInput.contains("done")) {
                handleUserMarkingTaskAsDone(userInput);
            } else {
                handleUserCreatingTask(userInput);
            }

            System.out.println("____________________________________________________________");

        }
    }

    private static void handleUserCreatingTask(String userInput) {
        // If user has not said any other command, store user input as task
        tasks[noOfTasks] = new Task(userInput);
        noOfTasks++;
        // Then, echo the task
        System.out.println("I've added: " + userInput);
    }

    private static void handleUserMarkingTaskAsDone(String userInput) {
        // Get number of task after the term "done"
        int indexOfCompletedTask = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // If task does not exist, do not delete any task
        if (indexOfCompletedTask > noOfTasks - 1 || indexOfCompletedTask < 0) {
            System.out.println("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasks[indexOfCompletedTask];

        // If task was already done, let user know
        if (chosenTask.isDone()) {
            System.out.println("Oh! This task was already marked as done:");
            return;
        }

        // If task exists, and is not done, mark it as done
        chosenTask.setDone(true);
        System.out.println("Wunderbar! This task has been marked as done:");

        // Print out the task in the following format: "    [X] Task"
        System.out.println("    " + chosenTask.getDoneStatusAsSymbol() + " " + chosenTask.getName());
    }

    private static void printAllTasks() {
        // If user said "list", print a list of all saved tasks
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(Integer.toString(i + 1) + "." + tasks[i].getDoneStatusAsSymbol()
                    + " " + tasks[i].getName());
        }
    }

    private static void printShutdownMessage() {
        // If user said "bye", update hasUserSaidBye and print closing phrase
        hasUserSaidBye = true;
        System.out.println("Thanks for coming. Auf wiedersehen!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Triss :)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
