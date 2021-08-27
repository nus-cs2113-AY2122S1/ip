import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // ArrayList to store all the tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        getUserInput();
    }

    /**
     * Prompts input from user and processes it
     */
    private static void getUserInput() {
        // String to store user's input
        String userInput = "";
        Scanner in = new Scanner(System.in);

        while (true) {
            // Reads user input
            userInput = in.nextLine();
            // Splits user input by spaces
            String[] userInputSplited = userInput.split("\\s+", 2);
            String command = userInputSplited[0];
            String description = "";

            // Ensure description exists
            if (userInputSplited.length > 1) {
                description = userInputSplited[1];
            }
            processCommands(command, description);
        }
    }

    /**
     * Processes the commands entered by the user.
     *
     * @param command The command entered by the user
     * @param description Task description entered by user.
     */
    private static void processCommands(String command, String description){
        switch (command) {
        case "list":
            listTasks();
            break;
        case "todo":
            addToDo(description);
            break;
        case "deadline":
            addDeadLine(description);
            break;
        case "event":
            addEvent(description);
            break;
        case "done":
            markDone(description);
            break;
        case "bye":
            exit();
        default:
            customPrint("Sorry! I did not understand your command.");
        }
    }

    /**
     * Adds a Todo to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addToDo(String description) {
        tasks.add(new Todo(description));
        acknowledgeCommand();
    }

    /**
     * Adds a Deadline to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addDeadLine(String description) {
        int byIndex = description.indexOf("/by");
        String taskDescription = description.substring(0, byIndex).trim(); // Remove trailing spaces
        String date = description.substring(byIndex + 4).trim(); // Remove leading spaces
        tasks.add(new Deadline(taskDescription, date));
        acknowledgeCommand();
    }

    /**
     * Adds an Event to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addEvent(String description) {
        int byIndex = description.indexOf("/at");
        String taskDescription = description.substring(0, byIndex).trim(); // Remove trailing spaces
        String at = description.substring(byIndex + 4).trim(); // Remove leading spaces
        tasks.add(new Event(taskDescription, at));
        acknowledgeCommand();
    }

    /**
     * Prints the exit message and ends the program.
     */
    private static void exit() {
        customPrint("Bye. Hope to see you again soon!");
        // Ends the program
        System.exit(0);
    }

    /**
     * Prints the acknowledgement that the command is processed.
     */
    private static void acknowledgeCommand() {
        Task addedTask = tasks.get(tasks.size() - 1);
        customPrint("Got it. I've added this task:\n" + addedTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the welcome message.
     */
    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Prints welcome message
        String welcomeMessage = logo;
        welcomeMessage += "Hello! I'm Duke\n";
        welcomeMessage += "What can I do for you?";
        customPrint(welcomeMessage);
    }

    /**
     * Prints out a statement with borders.
     *
     * @param statement Statement to be printed.
     */
    public static void customPrint(String statement) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(statement.trim()); // Remove any trailing spaces
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Prints out all the Tasks with borders.
     */
    public static void listTasks() {
        String taskString = "";
        taskString += "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            taskString += (i + 1) + "." + currentTask.toString() + "\n";
        }
        customPrint(taskString);
    }

    /**
     * Marks a task as done and calls customPrint to print the completed message.
     *
     * @param description String ID of task to be marked as completed.
     */
    public static void markDone(String description) {
        int taskId = Integer.parseInt(description) - 1; // -1 as array index starts from 0

        // Checks for valid taskID
        if (taskId < 0 || taskId > tasks.size() || tasks.size() == 0) {
            customPrint("You have entered an invalid task ID!");
            return;
        }

        // Checks if task has been completed
        Task currentTask = tasks.get(taskId);
        if (currentTask.isDone()) {
            customPrint("You have already completed the task [" + currentTask.getDescription() + "]!");
        } else {
            currentTask.markAsDone(); // Mark current task as done
            String textToPrint = "Nice! I've marked this task as done:\n";
            textToPrint += currentTask.toString();
            customPrint(textToPrint);
        }
    }
}
