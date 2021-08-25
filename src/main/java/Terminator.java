import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

public class Terminator {

    /**
     * List of constants used in formatWithHeading.
     */
    public static final int TERMINATOR_FORMATTING = 0;
    public static final int USER_FORMATTING = 1;

    /**
     * List of constants used in tokenizing user input.
     */
    public static final int KEYWORD_INDEX = 0;
    public static final int TASK_NUMBER_INDEX = 1;

    /**
     * List of constants used to identify Event Type for createEvent
     */
    public static final String TODO_TYPE = "T";
    public static final String EVENT_TYPE = "E";
    public static final String NORMAL_TYPE = "N";
    public static final String DEADLINE_TYPE = "D";

    /**
     * List of constants used to show index of taskName and dateTime from retrieval list
     */
    public static final int TASK_NAME_INDEX = 0;
    public static final int DATE_TIME_INDEX = 1;

    /**
     * Global variable used to show if loop to get user input should continue running.
     */
    public static Boolean toContinue = true;

    /**
     * Global Array List of all tasks created by the user.
     */
    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    /**
     * Given user input, extract Task Name and UserInput.
     * @param userinput The string given by the user.
     * @param eventType The type of task to extract based on.
     * @return String array containing userinput and eventType.
     */
    public static String[] extractNameDateTime(String userinput, String eventType){
        String[] returnArray = new String[2];
        if (Objects.equals(eventType, DEADLINE_TYPE)){
            // Get indexes to substring
            int startOfByIndex = userinput.indexOf("/by");
            int endOfByIndex = startOfByIndex + 4;
            int endOfDeadlineStringIndex = userinput.indexOf("deadline") + 9;

            // Get specific task_name and date_time
            String task_name = userinput.substring(endOfDeadlineStringIndex, startOfByIndex).strip();
            String date_time = userinput.substring(endOfByIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = task_name;
            returnArray[DATE_TIME_INDEX] = date_time;
        } else if (Objects.equals(eventType, EVENT_TYPE)){
            // Get indexes to substring
            int startOfAtIndex = userinput.indexOf("/at");
            int endOfAtIndex = startOfAtIndex + 4;
            int endOfEventStringIndex = userinput.indexOf("event") + 6;

            // Get specific task_name and date_time
            String task_name = userinput.substring(endOfEventStringIndex, startOfAtIndex).strip();
            String date_time = userinput.substring(endOfAtIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = task_name;
            returnArray[DATE_TIME_INDEX] = date_time;
        } else {
            // Extract values for ToDo
            int endOfToDoStringIndex = userinput.indexOf("todo") + 5;
            String task_name = userinput.substring(endOfToDoStringIndex).strip();
            returnArray[TASK_NAME_INDEX] = task_name;

        }
        return returnArray;
    }

    /**
     * Prints response back to user of task that is modified.
     * @param task_number The index of the task to be updated.
     */
    public static void printUpdateMessage(int task_number) {
        Task current_task = tasksList.get(task_number);
        System.out.println("Great! The following item has been marked as completed" + System.lineSeparator() +
                current_task.toString());
        System.out.println(formatWithHeading("Is there anything else you would like me to do?",
                TERMINATOR_FORMATTING));
    }

    /**
     * Update the completion status of the Task to true.
     * @param task_number The index of the task to be updated.
     */
    public static void updateCompletion(int task_number) {
        tasksList.get(task_number).setCompleted(true);
    }

    /**
     * Prints the tasks in the Task list with formatting.
     */
    public static void printTasks() {
        System.out.println("Here is a list of taskings:");
        System.out.println("===================================================");
        for (int i = 0; i < tasksList.size(); ++i) {
            Task currentTask = tasksList.get(i);
            // If the current task is completed, check the completion_status
            System.out.printf("%d.%s" + System.lineSeparator(), i+1, currentTask.toString());
        }
        System.out.println(formatWithHeading("Anything else?", TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is added.
     * @param new_task The task that is added by the user.
     */
    public static void printAddTaskMessage(Task new_task) {
        System.out.println(formatWithHeading("Added the following Task" + System.lineSeparator() +
                new_task.toString(), TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading(String.format("Now you have %d task(s) in the list.",
                        tasksList.size()), TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Hmmm... what should I do now?", TERMINATOR_FORMATTING));
    }

    /**
     * Creates a new default/ToDo Task with name provided and adds it to ArrayList.
     * @param task_name The name of the task to be added.
     * @return The task object created by the user.
     */
    public static Task addTask(String task_name, String task_type) {
        Task new_task = null;
        if (Objects.equals(task_type, TODO_TYPE)) {
            new_task = new ToDo(task_name);
        } else {
            new_task = new Task(task_name);
        }
        // Add to tasksList
        tasksList.add(new_task);
        return new_task;
    }

    /**
     * Overloaded class that creates a Deadline/Event Task with name and appropriate date time.
     * If task_type = DEADLINE_TYPE, create a Deadline object.
     * Else If task_type = EVENT_TYPE, create a Event object.
     * @param task_name The name assigned to the given task.
     * @param date_time The date-time associated with the task.
     * @param task_type The type of task to determine the subclass to create.
     * @return The task object created by the user.
     */
    public static Task addTask(String task_name, String date_time, String task_type){
        Task new_task = null;
        if (Objects.equals(task_type, DEADLINE_TYPE)){
            new_task = new Deadline(task_name, date_time);
        } else if (Objects.equals(task_type, EVENT_TYPE)){
            new_task = new Event(task_name, date_time);
        }
        tasksList.add(new_task);
        return new_task;
    }

    /**
     * Prints Goodbye message to user.
     */
    public static void printGoodByeMessage() {
        System.out.println(formatWithHeading("Hasta la vista.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("I will be back.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Program Terminating in...", TERMINATOR_FORMATTING));
        // Stops at 2 intentionally
        for (int i = 5; i > 1; --i) {
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
    public static String formatWithHeading(String msg, Integer option) {
        String prepend = "";
        switch (option) {
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
    public static void printHelloMessage() {
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
        // Local Variables
        String[] extractedValues;
        String task_name, date_time;
        Task created_task;

        // Prints opening message
        printHelloMessage();
        Scanner scanObject = new Scanner(System.in);

        // Continue Running Loop until bye is called
        while (toContinue) {
            // Gets user input
            System.out.print(formatWithHeading("", USER_FORMATTING));
            String userInput = scanObject.nextLine();
            // This is assuming user will put input
            // TODO: Error checking that user has put something in
            // Parse out keyword from user input
            String keyword = userInput.split(" ")[KEYWORD_INDEX];

            // Checks for the input for keywords BYE and LIST
            switch (keyword.toUpperCase()) {
            case "DONE":
                // Assumption that there are at least 2 tokens in split input
                // No check to see if task number is valid yet
                // No check for format of user input, like is there an /at? or /by?
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
            case "TODO":
                // Extract values and create ToDo Task
                extractedValues = extractNameDateTime(userInput, TODO_TYPE);
                task_name = extractedValues[TASK_NAME_INDEX];
                created_task = addTask(task_name, TODO_TYPE);
                printAddTaskMessage(created_task);
                break;
            case "DEADLINE":
                // Extract values and create Deadline Task
                extractedValues = extractNameDateTime(userInput, DEADLINE_TYPE);
                task_name = extractedValues[TASK_NAME_INDEX];
                date_time = extractedValues[DATE_TIME_INDEX];
                created_task = addTask(task_name, date_time, DEADLINE_TYPE);
                printAddTaskMessage(created_task);
                break;
            case "EVENT":
                // Extract values and create Event Task
                extractedValues = extractNameDateTime(userInput, EVENT_TYPE);
                task_name = extractedValues[TASK_NAME_INDEX];
                date_time = extractedValues[DATE_TIME_INDEX];
                created_task = addTask(task_name, date_time, EVENT_TYPE);
                printAddTaskMessage(created_task);
                break;
            default:
                // Create default Task and add to tasksList
                created_task = addTask(userInput, NORMAL_TYPE);
                printAddTaskMessage(created_task);
                break;
            }
        }
    }
}
