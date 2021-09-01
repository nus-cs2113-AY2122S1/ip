import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Terminator {

    /**
     * Initialize the scanner to be used globally.
     */
    private static final Scanner scanObject = new Scanner(System.in);

    /**
     * Keywords for commands.
     */
    private static final String BYE_STRING = "BYE";
    private static final String DONE_STRING = "DONE";
    private static final String LIST_STRING = "LIST";
    private static final String TODO_STRING = "TODO";
    private static final String EVENT_STRING = "EVENT";
    private static final String DEADLINE_STRING = "DEADLINE";

    /**
     * List of constants used to define who is speaking.
     */
    public static final String TERMINATOR_STRING = "[The Terminator]";
    public static final String USER_STRING = "[User]";

    /**
     * List of constants used in extractNameDateTime
     */
    public static final String BY_KEYWORD = "/by";
    public static final String AT_KEYWORD = "/at";
    public static final String TODO_KEYWORD = "todo";
    public static final String EVENT_KEYWORD = "event";
    public static final String DEADLINE_KEYWORD = "deadline";

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
    private static String[] extractNameDateTime(String userinput, String eventType){
        String[] returnArray = new String[2];
        if (Objects.equals(eventType, DEADLINE_TYPE)){
            // Get indexes to substring
            int startOfByIndex = userinput.indexOf(BY_KEYWORD);
            int endOfByIndex = startOfByIndex + 4;
            int endOfDeadlineStringIndex = userinput.indexOf(DEADLINE_KEYWORD) + 9;

            // Get specific task_name and date_time
            String task_name = userinput.substring(endOfDeadlineStringIndex, startOfByIndex).strip();
            String date_time = userinput.substring(endOfByIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = task_name;
            returnArray[DATE_TIME_INDEX] = date_time;
        } else if (Objects.equals(eventType, EVENT_TYPE)){
            // Get indexes to substring
            int startOfAtIndex = userinput.indexOf(AT_KEYWORD);
            int endOfAtIndex = startOfAtIndex + 4;
            int endOfEventStringIndex = userinput.indexOf(EVENT_KEYWORD) + 6;

            // Get specific task_name and date_time
            String task_name = userinput.substring(endOfEventStringIndex, startOfAtIndex).strip();
            String date_time = userinput.substring(endOfAtIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = task_name;
            returnArray[DATE_TIME_INDEX] = date_time;
        } else {
            // Extract values for ToDo
            int endOfToDoStringIndex = userinput.indexOf(TODO_KEYWORD) + 5;
            String task_name = userinput.substring(endOfToDoStringIndex).strip();
            returnArray[TASK_NAME_INDEX] = task_name;

        }
        return returnArray;
    }

    /**
     * Prints response back to user of task that is modified.
     * @param task_number The index of the task to be updated.
     */
    private static void printUpdateMessage(int task_number) {
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
    private static void updateCompletion(int task_number) {
        tasksList.get(task_number).setCompleted(true);
    }

    /**
     * Prints the tasks in the Task list with formatting.
     */
    private static void printTasks() {
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
    private static void printAddTaskMessage(Task new_task) {
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
    private static Task createTask(String task_name, String task_type) {
        Task new_task;
        if (Objects.equals(task_type, TODO_TYPE)) {
            new_task = new ToDo(task_name);
        } else {
            new_task = new Task(task_name);
        }
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
    private static Task createTask(String task_name, String date_time, String task_type){
        Task new_task = null;
        if (Objects.equals(task_type, DEADLINE_TYPE)){
            new_task = new Deadline(task_name, date_time);
        } else if (Objects.equals(task_type, EVENT_TYPE)){
            new_task = new Event(task_name, date_time);
        }
        return new_task;
    }

    /**
     * Given a new task, add it into the list of tasks.
     * @param new_task A task created by the user.
     */
    private static void addTask(Task new_task){
        tasksList.add(new_task);
    }

    /**
     * Prints Goodbye message to user.
     */
    private static void printGoodByeMessage() {
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
    private static String formatWithHeading(String msg, Integer option) {
        String prepend = "";
        switch (option) {
        case TERMINATOR_FORMATTING:
            prepend = TERMINATOR_STRING;
            break;
        case USER_FORMATTING:
            prepend = USER_STRING;
            break;
        default:
            break;
        }
        return String.format("%s: %s", prepend, msg);
    }

    /**
     * Prints the welcome message to the user.
     */
    private static void printHelloMessage() {
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
     * Prompts the user for input and returns the received input.
     * @return Line entered by the user.
     */
    private static String getUserInput(){
        System.out.print(formatWithHeading("", USER_FORMATTING));
        String input = scanObject.nextLine();
        // Keep getting input until its not empty
        while (input.trim().isEmpty()){
            input = scanObject.nextLine();
        }
        return input;
    }

    /**
     * Given user command to mark task as completed, return the task number.
     * @param userLine String containing the TaskNumber.
     * @return The TaskNumber to be marked as done
     */
    private static int getTaskNumberFromInput(String userLine){
        return Integer.parseInt(userLine.split(" ")[TASK_NUMBER_INDEX]) - 1;
    }

    /**
     * Worker class to create ToDo Tasks.
     * @param userLine Line that is inputted by the user.
     */
    private static void createToDoTask(String userLine){
        // Extract values and create ToDo Task
        String[] extractedValues = extractNameDateTime(userLine, TODO_TYPE);
        String task_name = extractedValues[TASK_NAME_INDEX];
        Task created_task = createTask(task_name, TODO_TYPE);
        addTask(created_task);
        printAddTaskMessage(created_task);
    }

    /**
     * Worker class to create Deadline Tasks.
     * @param userLine Line that is inputted by the user.
     */
    private static void createDeadlineTask(String userLine) {
        // Extract values and create Deadline Task
        String[] extractedValues = extractNameDateTime(userLine, DEADLINE_TYPE);
        String task_name = extractedValues[TASK_NAME_INDEX];
        String date_time = extractedValues[DATE_TIME_INDEX];
        Task created_task = createTask(task_name, date_time, DEADLINE_TYPE);
        addTask(created_task);
        printAddTaskMessage(created_task);
    }

    /**
     * Worker class to create Event Tasks.
     * @param userLine Line that is inputted by the user.
     */
    private static void createEventTask(String userLine) {
        // Extract values and create Event Task
        String[] extractedValues = extractNameDateTime(userLine, EVENT_TYPE);
        String task_name = extractedValues[TASK_NAME_INDEX];
        String date_time = extractedValues[DATE_TIME_INDEX];
        Task created_task = createTask(task_name, date_time, EVENT_TYPE);
        addTask(created_task);
        printAddTaskMessage(created_task);
    }

    /**
     * Worker class to create Normal Tasks.
     * @param userLine Line that is inputted by the user.
     */
    private static void createNormalTask(String userLine) {
        // Create default Task and add to tasksList
        Task created_task = createTask(userLine, NORMAL_TYPE);
        addTask(created_task);
        printAddTaskMessage(created_task);
    }

    /**
     * Executes the command based on what is given by the user.
     * @param userLine Line that is inputted by the user.
     */
    private static void executeCommand(String userLine){
        String keyword = userLine.split(" ")[KEYWORD_INDEX];

        // Checks for the input for keywords BYE and LIST
        switch (keyword.toUpperCase()) {
        case DONE_STRING:
            // Assumption that there are at least 2 tokens in split input
            // No check to see if task number is valid yet
            // No check for format of user input, like is there an /at? or /by?
            // TODO: Might want to include error checks (like try/catch) at later levels

            // Parse out task number from user input
            int taskNumber = getTaskNumberFromInput(userLine);
            // Update the list and print respective message
            updateCompletion(taskNumber);
            printUpdateMessage(taskNumber);
            break;
        case LIST_STRING:
            // Print Tasks with in-built tasksList
            printTasks();
            break;
        case BYE_STRING:
            // Stop loop and print Goodbye message
            toContinue = false;
            printGoodByeMessage();
            break;
        case TODO_STRING:
            createToDoTask(userLine);
            break;
        case DEADLINE_STRING:
            createDeadlineTask(userLine);
            break;
        case EVENT_STRING:
            createEventTask(userLine);
            break;
        default:
            createNormalTask(userLine);
            break;
        }
    }

    /**
     * Main Function that is called upon program execution.
     * @param args System Arguments added to the program.
     */
    public static void main(String[] args) {
        // Prints opening message
        printHelloMessage();

        // Continue Running Loop until bye is called
        while (toContinue) {
            // Gets user input
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }
}
