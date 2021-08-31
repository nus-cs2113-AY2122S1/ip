import java.util.Scanner;

public class Duke {

    /** Logo shown during startup */
    private static final String LOGO = "████████ ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██ ██      ██      \n" +
            "   ██    ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██      ██      ██ \n" +
            "   ██    ██   ██ ██ ███████ ███████ \n";

    /** String of underscores to separate user input and Triss output */
    public static final String SEPARATOR_LINE = "____________________________________________________________";

    /** Boolean to track if user has said "bye" */
    private static boolean hasUserSaidBye = false;

    /** Array to keep track of user's tasks */
    private static Task[] tasks = new Task[100];

    /** Int to keep track of number of tasks stored in tasks */
    private static int noOfTasks = 0;

    /** Length of the word "todo" */
    public static final int END_INDEX_OF_WORD_TODO = 4;
    /** Length of the word "deadline" */
    public static final int END_INDEX_OF_WORD_DEADLINE = 8;
    /** Length of the word "event" */
    public static final int END_INDEX_OF_WORD_EVENT = 5;

    /**
     * Loops Triss into receiving user input and giving output messages.
     * It will only end once the user types "bye".
     * @param args No params currently used.
     */
    public static void main(String[] args) {
        // Print LOGO and welcome text
        printWelcomeMessage();

        // Initialise user input reader
        Scanner in = createNewInputReader();

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            // Get the next line of input, and parse it to find the user's command (first word in input)
            String userInput = getUserInput(in);
            String userCommand = parseUserInput(userInput, 0);
            printLine(SEPARATOR_LINE);

            // Perform actions based on user's command
            switch (userCommand) {
            case "bye":
                printShutdownMessage();
                break;
            case "list":
                printAllTasks();
                break;
            case "done":
                handleUserMarkingTaskAsDone(userInput);
                break;
            default:
                handleUserCreatingTask(userInput);
                break;
            }

            printLine(SEPARATOR_LINE);

        }
    }

    /**
     * Create a new Scanner to read user input.
     * @return A new Scanner.
     */
    private static Scanner createNewInputReader() {
        return new Scanner(System.in);
    }

    /**
     * Print a string, then terminates the line.
     * @param s The string to be printed.
     */
    private static void printLine(String s) {
        System.out.println(s);
    }

    /**
     * Parse the user input and return the word in the index the user wants.
     * @param userInput The user input to be parsed.
     * @param i The index of the word in the user input to be returned.
     * @return Parsed string from user input.
     */
    private static String parseUserInput(String userInput, int i) {
        return userInput.split(" ")[i];
    }

    /**
     * Read the next line of user input.
     * If the user input is blank, asks user for input again.
     * @param in The InputReader (Scanner) that will be used to read the next line.
     * @return Valid user input.
     */
    private static String getUserInput(Scanner in) {
        /* String variable to store user input */
        String userInput = in.nextLine();

        while (userInput.isBlank()) {
            printLine("Stop with the silent treatment! Say something?");
            printLine(SEPARATOR_LINE);
            userInput = in.nextLine();
        }
        return userInput;
    }

    /**
     * Creates new task depending on first word in user input.
     * @param userInput The user's input.
     */
    private static void handleUserCreatingTask(String userInput) {
        String taskType = parseUserInput(userInput, 0);

        switch (taskType) {
        case "deadline":
            createNewDeadline(userInput);
            break;
        case "event":
            createNewEvent(userInput);
            break;
        case "todo":
            createNewTodo(userInput);
            break;
        default:
            createNewTodo("todo " + userInput.trim());
            break;
        }
    }

    /**
     * Creates a new todo based on user's input.
     * If user did not type in this format: "todo Eat with Friends", it asks the user to try again.
     * @param userInput Any user input starting with the words "todo"
     */
    private static void createNewTodo(String userInput) {
        String taskName;
        taskName = userInput.substring(END_INDEX_OF_WORD_TODO).trim();

        if (taskName.isBlank()) {
            printLine("You didn't specify a name for your todo! Let's try that again.");
            printLine(" ");
            printLine("Type a todo in this format:");
            printLine("    todo Eat with Friends");
            return;
        }

        tasks[noOfTasks] = new Todo(taskName);

        // Increase current number of tasks by 1
        noOfTasks++;

        // Then, echo the task
        printLine("I've added: " + tasks[noOfTasks - 1].printTask());
    }

    /**
     * Creates a new event based on the user's input.
     * User has to type the input in this format: "event Stay in a log cabin /Friday the 13th".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "event".
     */
    private static void createNewEvent(String userInput) {
        String taskName;
        String eventTiming;

        try {
            taskName = userInput.substring(END_INDEX_OF_WORD_EVENT, userInput.indexOf("/")).trim();
            eventTiming = userInput.substring(userInput.indexOf("/") + 1).trim();
        } catch (Exception e) {
            printLine("You didn't write your event properly!");
            printLine(" ");
            printLine("Try inserting an event in this format:");
            printLine("    event Stay in a log cabin /Friday the 13th");
            return;
        }

        tasks[noOfTasks] = new Event(taskName, eventTiming);

        // Increase current number of tasks by 1
        noOfTasks++;

        // Then, echo the task
        printLine("I've added: " + tasks[noOfTasks - 1].printTask());
    }

    /**
     * Creates a new deadline based on the user's input.
     * User has to type the input in this format: "deadline Meet with Friends /12th July".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "deadline".
     */
    private static void createNewDeadline(String userInput) {
        String deadlineDate;
        String taskName;

        try {
            deadlineDate = userInput.substring(userInput.indexOf("/") + 1).trim();
            taskName = userInput.substring(END_INDEX_OF_WORD_DEADLINE, userInput.indexOf("/")).trim();
        } catch (Exception e) {
            printLine("You didn't write your deadline properly!");
            printLine(" ");
            printLine("Try inserting a deadline in this format:");
            printLine("    deadline Meet with Friends /12th July");
            return;
        }

        tasks[noOfTasks] = new Deadline(taskName, deadlineDate);

        // Increase current number of tasks by 1
        noOfTasks++;

        // Then, echo the task
        printLine("I've added: " + tasks[noOfTasks - 1].printTask());
    }

    /**
     * Mark user task as done, if request is valid.
     * Stops if user did not specify a task.
     * Stops if user's chosent task does not exist.
     * Informs user if task was already done.
     * @param userInput Any user input starting with "done"
     */
    private static void handleUserMarkingTaskAsDone(String userInput) {
        // Get number of task after the term "done"
        int indexOfCompletedTask;
        // Throw exception if user did not type a number after "done"
        try {
            indexOfCompletedTask = Integer.parseInt(parseUserInput(userInput, 1)) - 1;
        } catch (Exception e) {
            printLine("Ach, nee! That task does not exist.");
            return;
        }


        // If task does not exist, do not delete any task
        if (indexOfCompletedTask > noOfTasks - 1 || indexOfCompletedTask < 0) {
            printLine("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasks[indexOfCompletedTask];

        // If task was already done, let user know
        if (chosenTask.isDone()) {
            printLine("Oh! This task was already marked as done:");
            // Print out the task in the following format: "    [X] Task"
            printLine("    " + chosenTask.printTask());
            return;
        }

        // If task exists, and is not done, mark it as done
        chosenTask.setDone(true);
        printLine("Wunderbar! This task has been marked as done:");

        // Print out the task in the following format: "    [X] Task"
        printLine("    " + chosenTask.printTask());
    }

    /**
     * Prints all tasks stored in Task Array tasks.
     */
    private static void printAllTasks() {
        // If user said "list", print a list of all saved tasks
        for (int i = 0; i < noOfTasks; i++) {
            printLine(i + 1 + "." + tasks[i].printTask());
        }
    }

    /**
     * Prints shutdown message.
     */
    private static void printShutdownMessage() {
        // If user said "bye", update hasUserSaidBye and print closing phrase
        hasUserSaidBye = true;
        printLine("Thanks for coming. Auf wiedersehen!");
    }

    /**
     * Prints welcome message.
     */
    private static void printWelcomeMessage() {
        printLine("Hello from\n" + LOGO);
        printLine(SEPARATOR_LINE);
        printLine("Hello! I'm Triss :)");
        printLine("What can I do for you?");
        printLine(SEPARATOR_LINE);
    }
}
