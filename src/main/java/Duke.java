import java.util.Scanner;

public class Duke {

    /** Logo shown during startup */
    private static final String LOGO = "████████ ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██ ██      ██      \n" +
            "   ██    ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██      ██      ██ \n" +
            "   ██    ██   ██ ██ ███████ ███████ \n";

    /** Boolean to track if user has said "bye" */
    private static boolean hasUserSaidBye = false;

    /** Array to keep track of user's tasks */
    private static Task[] tasks = new Task[100];

    /** Int to keep track of number of tasks stored in tasks */
    private static int noOfTasks = 0;

    /** String variable to store user input */
    private static String userInput = null;

    /** Length of the word "todo" */
    public static final int END_INDEX_OF_WORD_TODO = 4;
    /** Length of the word "deadline" */
    public static final int END_INDEX_OF_WORD_DEADLINE = 8;
    /** Length of the word "event" */
    public static final int END_INDEX_OF_WORD_EVENT = 5;

    public static void main(String[] args) {
        // Print LOGO  and welcome text
        printWelcomeMessage();

        // Initialise user input reader
        Scanner in = new Scanner(System.in);

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            String userInput = in.nextLine();
            String userCommand = userInput.split(" ")[0];
            System.out.println("____________________________________________________________");

            // Check if user has said "bye"
            if (userCommand.equals("bye")) {
                printShutdownMessage();
            } else if (userCommand.equals("list")) {
                printAllTasks();
            } else if (userCommand.equals("done")) {
                handleUserMarkingTaskAsDone(userInput);
            } else {
                handleUserCreatingTask(userInput);
            }

            System.out.println("____________________________________________________________");

        }
    }

    private static void handleUserCreatingTask(String userInput) {
        String taskType = userInput.split(" ")[0];
        String taskName;
        String deadlineDate;

        if (taskType.equals("deadline")) {
            deadlineDate = userInput.substring(userInput.indexOf("/") + 1).trim();
            taskName = userInput.substring(END_INDEX_OF_WORD_DEADLINE, userInput.indexOf("/")).trim();
            tasks[noOfTasks] = new Deadline(taskName, deadlineDate);
        } else if (taskType.equals("event")) {
            String eventTiming = userInput.substring(userInput.indexOf("/") + 1).trim();
            taskName = userInput.substring(END_INDEX_OF_WORD_EVENT, userInput.indexOf("/")).trim();
            tasks[noOfTasks] = new Event(taskName, eventTiming);
        } else if (taskType.equals("todo")) {
            taskName = userInput.substring(END_INDEX_OF_WORD_TODO).trim();
            tasks[noOfTasks] = new Todo(taskName);
        } else {
            taskName = userInput.trim();
            tasks[noOfTasks] = new Todo(userInput);
        }

        // Increase current number of tasks by 1
        noOfTasks++;
        // Then, echo the task
        System.out.println("I've added: " + tasks[noOfTasks - 1].printTask());
    }

    private static void handleUserMarkingTaskAsDone(String userInput) {
        // Get number of task after the term "done"
        int indexOfCompletedTask;
        // Throw exception if user did not type a number after "done"
        try {
            indexOfCompletedTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (Exception e) {
            System.out.println("Ach, nee! That task does not exist.");
            return;
        }


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
            // Print out the task in the following format: "    [X] Task"
            System.out.println("    " + chosenTask.printTask());
            return;
        }

        // If task exists, and is not done, mark it as done
        chosenTask.setDone(true);
        System.out.println("Wunderbar! This task has been marked as done:");

        // Print out the task in the following format: "    [X] Task"
        System.out.println("    " + chosenTask.printTask());
    }

    private static void printAllTasks() {
        // If user said "list", print a list of all saved tasks
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(Integer.toString(i + 1) + "." + tasks[i].printTask());
        }
    }

    private static void printShutdownMessage() {
        // If user said "bye", update hasUserSaidBye and print closing phrase
        hasUserSaidBye = true;
        System.out.println("Thanks for coming. Auf wiedersehen!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Triss :)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
