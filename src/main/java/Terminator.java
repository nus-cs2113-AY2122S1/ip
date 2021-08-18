import java.util.ArrayList;
import java.util.Scanner;

public class Terminator {

    public static final int TERMINATOR_FORMATTING = 0;
    public static final int USER_FORMATTING = 1;
    public static final int KEYWORD_INDEX = 0;
    public static final int TASK_NUMBER_INDEX = 1;
    public static Boolean toContinue = true;
    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    /**
     * Prints response back to user of task that is modified.
     * @param task_number The index of the task to be updated.
     */
    public static void printUpdateMessage(int task_number){
        Task current_task = tasksList.get(task_number);
        System.out.println("Great! The following item has been marked as completed:");
        System.out.println("[" + current_task.getStatusIcon() + "] " + current_task.getName());
        System.out.println(formatWithHeading("Is there anything else you would like me to do?",
                TERMINATOR_FORMATTING));
    }

    /**
     * Update the completion status of the Task to true.
     * @param task_number The index of the task to be updated.
     */
    public static void updateCompletion(int task_number){
        tasksList.get(task_number).setCompleted(true);
    }

    /**
     * Prints the tasks in the Task list with formatting.
     */
    public static void printTasks(){
        System.out.println("Here is a list of taskings:");
        for (int i = 0; i < tasksList.size(); ++i){
            Task currentTask = tasksList.get(i);
            // If the current task is completed, check the completion_status
            String completion_status = "[" + currentTask.getStatusIcon() + "]";
            System.out.printf("%d. %-15s %-15s" + System.lineSeparator(), i+1, currentTask.getName(),
                    completion_status);
        }
        System.out.println(formatWithHeading("Anything else?", TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is added.
     * @param task_name The name of the task to be added.
     */
    public static void printAddTaskMessage(String task_name){
        System.out.println(formatWithHeading("Added \"" + task_name + "\" successfully!", TERMINATOR_FORMATTING));
    }

    /**
     * Creates a new Task with name provided and adds it to ArrayList.
     * @param task_name The name of the task to be added.
     */
    public static void addTask(String task_name){
        // Instantiate new Task object
        Task new_task = new Task(task_name);
        // Add to tasksList
        tasksList.add(new_task);
    }

    /**
     * Prints Goodbye message to user.
     */
    public static void printGoodByeMessage(){
        System.out.println(formatWithHeading("Hasta la vista.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("I will be back.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Program Terminating in...", TERMINATOR_FORMATTING));
        // Stops at 2 intentionally
        for (int i = 5; i > 1; --i){
            System.out.println(formatWithHeading(Integer.toString(i), TERMINATOR_FORMATTING));
        }
    }

    /**
     * Format printed messages with the appropriate headings.
     * If option TERMINATOR_FORMATTING is selected, [The Terminator] will prepend the msg.
     * If option USER_FORMATTING is selected, [User] will prepend the msg.
     * @param msg Message to be printed.
     * @param option TERMINATOR_FORMATTING or USER_FORMATTING.
     * @return String with prepended heading.
     */
    public static String formatWithHeading(String msg, Integer option){
        String prepend = "";
        switch (option){
        case TERMINATOR_FORMATTING:
            prepend = "[The Terminator]";
            break;
        case USER_FORMATTING:
            prepend = "[User]";
            break;
        default:
            break;
        }
        return String.format("%s: %s", prepend, msg);
    }

    /**
     * Prints the welcome message to the user.
     */
    public static void printHelloMessage(){
        // @@author ObASCII
        // Reused from https://www.asciiart.eu/computers/computers
        // with minor modifications
        String logo = "              ,---------------------------,\n"
                + "              |  /---------------------\\  |\n"
                + "              | |                       | |\n"
                + "              | |     404               | |\n"
                + "              | |      Send             | |\n"
                + "              | |       Help            | |\n"
                + "              | |        Pls            | |\n"
                + "              |  \\_____________________/  |\n"
                + "              |___________________________|\n"
                + "            ,---\\_____     []     _______/------,\n"
                + "          /         /______________\\           /|\n"
                + "        /___________________________________ /  | ___\n"
                + "        |                                   |   |    )\n"
                + "        |  _ _ _                 [-------]  |   |   (\n"
                + "        |  o o o                 [-------]  |  /    _)_\n"
                + "        |__________________________________ |/     /  /\n"
                + "    /-------------------------------------/|      ( )/\n"
                + "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
                + "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(logo);
        System.out.println(formatWithHeading("Hola Amigos, I am the Terminator.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("What would you like me to do?", TERMINATOR_FORMATTING));
        System.out.println("[*] Type \"bye\" if you want to leave!");
    }

    /**
     * Main Function that is called upon program execution.
     * @param args System Arguments added to the program.
     */
    public static void main(String[] args) {
        // Prints opening message
        printHelloMessage();
        Scanner scanObject = new Scanner(System.in);

        // Continue Running Loop until bye is called
        while(toContinue){
            // Gets user input
            System.out.print(formatWithHeading("", USER_FORMATTING));
            String userInput = scanObject.nextLine();
            // This is assuming user will put input
            // TODO: Error checking that user has put something in
            // Parse out keyword from user input
            String keyword = userInput.split(" ")[KEYWORD_INDEX];

            // Checks for the input for keywords BYE and LIST
            switch (keyword.toUpperCase()){
                case "DONE":
                    // Assumption that there are at least 2 tokens in split input
                    // No check to see if task number is valid yet
                    // TODO: Might want to include error checks (like try/catch) at later levels

                    // Parse out task number from user input
                    int taskNumber = Integer.parseInt(userInput.split(" ")[TASK_NUMBER_INDEX]) - 1;
                    // Update the list and print respective message
                    updateCompletion(taskNumber);
                    printUpdateMessage(taskNumber);
                    break;
                case "LIST":
                    // Print Tasks with in-built tasksList
                    printTasks();
                    break;
                case "BYE":
                    // Stop loop and print Goodbye message
                    toContinue = false;
                    printGoodByeMessage();
                    break;
                default:
                    // Create Task and add to tasksList
                    addTask(userInput);
                    printAddTaskMessage(userInput);
                    break;
            }
        }
    }
}
