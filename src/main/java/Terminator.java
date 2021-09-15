import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private static final String DELETE_STRING = "DELETE";
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
    public static final int COMPLETION_INDEX = 2;

    /**
     * Global variable used to show if loop to get user input should continue running.
     */
    public static Boolean toContinue = true;

    /**
     * Global Array List of all tasks created by the user.
     */
    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    /**
     * Global File Path to save and retrieve Task Records
     */
    public static String DIR_LOCATION = "./data";
    public static String FILE_LOCATION = "./data/records.txt";

    /**
     * Global variables used to determine if input for Task Creation is from file or from user
     */
    public static int FROM_USER = 0;
    public static int FROM_FILE = 1;

    /**
     * Global variable to show the deliminator for strings stored in the file format
     */
    public static String DELIMINATOR_FOR_FILE = " | ";

    /**
     * Given user input, extract Task Name and UserInput.
     * @param userInput The string given by the user.
     * @param eventType The type of task to extract based on.
     * @return String array containing userinput and eventType.
     */
    private static String[] extractNameDateTime(String userInput, String eventType) {
        String[] returnArray = new String[3];
        if (Objects.equals(eventType, DEADLINE_TYPE)) {
            // Get indexes to substring
            int startOfByIndex = userInput.indexOf(BY_KEYWORD);
            int endOfByIndex = startOfByIndex + 4;
            int endOfDeadlineStringIndex = userInput.indexOf(DEADLINE_KEYWORD) + 9;

            // Get specific task_name and date_time
            String taskName = userInput.substring(endOfDeadlineStringIndex, startOfByIndex).strip();
            String dateTime = userInput.substring(endOfByIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = taskName;
            returnArray[DATE_TIME_INDEX] = dateTime;
        } else if (Objects.equals(eventType, EVENT_TYPE)) {
            // Get indexes to substring
            int startOfAtIndex = userInput.indexOf(AT_KEYWORD);
            int endOfAtIndex = startOfAtIndex + 4;
            int endOfEventStringIndex = userInput.indexOf(EVENT_KEYWORD) + 6;

            // Get specific task_name and date_time
            String taskName = userInput.substring(endOfEventStringIndex, startOfAtIndex).strip();
            String dateTime = userInput.substring(endOfAtIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = taskName;
            returnArray[DATE_TIME_INDEX] = dateTime;
        } else {
            // Extract values for ToDo
            int endOfToDoStringIndex = userInput.indexOf(TODO_KEYWORD) + 5;
            String taskName = userInput.substring(endOfToDoStringIndex).strip();
            returnArray[TASK_NAME_INDEX] = taskName;

        }
        return returnArray;
    }

    /**
     * Prints response back to user of task that is modified.
     * @param taskNumber The index of the task to be updated.
     */
    private static void printUpdateMessage(int taskNumber) {
        Task currentTask = tasksList.get(taskNumber);
        System.out.println("Great! The following item has been marked as completed" + System.lineSeparator() +
                currentTask.toString());
        System.out.println(formatWithHeading("Is there anything else you would like me to do?",
                TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is deleted.
     * @param taskNumber The index of the task to be deleted.
     */
    private static void printDeleteMessage(int taskNumber) {
        Task currentTask = tasksList.get(taskNumber);
        System.out.println("Noted. The following item has been deleted" + System.lineSeparator() +
                currentTask.toString());
        System.out.println(formatWithHeading("Is there anything else you would like me to do?",
                TERMINATOR_FORMATTING));
    }

    /**
     * Update the completion status of the Task to true.
     * @param taskNumber The index of the task to be updated.
     */
    private static void updateTaskCompletionStatus(int taskNumber) throws IndexOutOfBoundsException {
        tasksList.get(taskNumber).setCompleted(true);
    }

    /**
     * Delete the Task from the list.
     * @param taskNumber The index of the task to be deleted.
     */
    private static void deleteTaskFromList(int taskNumber) throws IndexOutOfBoundsException {
        tasksList.remove(taskNumber);
    }

    /**
     * Prints error message if requested task to access is out of bounds.
     */
    private static void printOutOfBoundsMessage() {
        System.out.println(formatWithHeading("Index is out of bounds! Try again :(", TERMINATOR_FORMATTING));
    }

    /**
     * Prints the tasks in the Task list with formatting.
     */
    private static void printTasks() {
        System.out.println("Here is a list of taskings:");
        System.out.println("===================================================");
        for (int i = 0; i < tasksList.size(); ++i) {
            Task currentTask = tasksList.get(i);
            // If the current task is completed, check the completion status
            System.out.printf("%d.%s" + System.lineSeparator(), i+1, currentTask.toString());
        }
        System.out.println(formatWithHeading("Anything else?", TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is added.
     * @param newTask The task that is added by the user.
     */
    private static void printAddTaskMessage(Task newTask) {
        System.out.println(formatWithHeading("Added the following Task" + System.lineSeparator() +
                newTask.toString(), TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading(String.format("Now you have %d task(s) in the list.",
                        tasksList.size()), TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Hmmm... what should I do now?", TERMINATOR_FORMATTING));
    }

    /**
     * Creates a new default/ToDo Task with name provided and adds it to ArrayList.
     * @param taskName The name of the task to be added.
     * @return The task object created by the user.
     */
    private static Task createTask(String taskName, String taskType) {
        Task newTask;
        if (Objects.equals(taskType, TODO_TYPE)) {
            newTask = new ToDo(taskName);
        } else {
            newTask = new Task(taskName);
        }
        return newTask;
    }

    /**
     * Overloaded class that creates a Deadline/Event Task with name and appropriate date time.
     * If task_type = DEADLINE_TYPE, create a Deadline object.
     * Else If task_type = EVENT_TYPE, create a Event object.
     * @param taskName The name assigned to the given task.
     * @param dateTime The date-time associated with the task.
     * @param taskType The type of task to determine the subclass to create.
     * @return The task object created by the user.
     */
    private static Task createTask(String taskName, String dateTime, String taskType) {
        Task newTask = null;
        if (Objects.equals(taskType, DEADLINE_TYPE)) {
            newTask = new Deadline(taskName, dateTime);
        } else if (Objects.equals(taskType, EVENT_TYPE)) {
            newTask = new Event(taskName, dateTime);
        }
        return newTask;
    }

    /**
     * Given a new task, add it into the list of tasks.
     * @param newTask A task created by the user.
     */
    private static void addTask(Task newTask) {
        tasksList.add(newTask);
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
    private static String getUserInput() {
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
     * @param userInput String containing the TaskNumber.
     * @return The TaskNumber to be marked as done
     */
    private static int getTaskNumberFromInput(String userInput) throws IndexOutOfBoundsException {
        return Integer.parseInt(userInput.split(" ")[TASK_NUMBER_INDEX]) - 1;
    }

    /**
     * Worker class to create ToDo Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    private static void createToDoTask(String userLine, int option) {
        // Extract values and create ToDo Task
        String[] extractedValues = new String[3];
        String taskName, completionStatus = " ";
        if (option == FROM_USER) {
            extractedValues = extractNameDateTime(userLine, TODO_TYPE);
        } else if (option == FROM_FILE) {
            extractedValues = parseFileFormattedString(userLine, TODO_TYPE);
        }
        taskName = extractedValues[TASK_NAME_INDEX];
        completionStatus = extractedValues[COMPLETION_INDEX];
        Task createdTask = createTask(taskName, TODO_TYPE);
        if (Objects.equals(completionStatus, "X")) {
            createdTask.setCompleted(true);
        }
        addTask(createdTask);
        printAddTaskMessage(createdTask);
    }

    /**
     * Worker class to create Deadline Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    private static void createDeadlineTask(String userLine, int option) {
        // Extract values and create Deadline Task
        String[] extractedValues = new String[3];
        String taskName, dateTime, completionStatus = " ";
        if (option == FROM_USER) {
            extractedValues = extractNameDateTime(userLine, DEADLINE_TYPE);
        } else if (option == FROM_FILE) {
            extractedValues = parseFileFormattedString(userLine, DEADLINE_TYPE);
        }
        taskName = extractedValues[TASK_NAME_INDEX];
        dateTime = extractedValues[DATE_TIME_INDEX];
        completionStatus = extractedValues[COMPLETION_INDEX];
        Task createdTask = createTask(taskName, dateTime, DEADLINE_TYPE);
        if (Objects.equals(completionStatus, "X")) {
            createdTask.setCompleted(true);
        }
        addTask(createdTask);
        printAddTaskMessage(createdTask);
    }

    /**
     * Worker class to create Event Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    private static void createEventTask(String userLine, int option) {
        // Extract values and create Event Task
        String[] extractedValues = new String[3];
        String taskName, dateTime, completionStatus = " ";
        if (option == FROM_USER) {
            extractedValues = extractNameDateTime(userLine, EVENT_TYPE);
        } else if (option == FROM_FILE) {
            extractedValues = parseFileFormattedString(userLine, EVENT_TYPE);
        }
        taskName = extractedValues[TASK_NAME_INDEX];
        dateTime = extractedValues[DATE_TIME_INDEX];
        completionStatus = extractedValues[COMPLETION_INDEX];
        Task createdTask = createTask(taskName, dateTime, EVENT_TYPE);
        if (Objects.equals(completionStatus, "X")) {
            createdTask.setCompleted(true);
        }
        addTask(createdTask);
        printAddTaskMessage(createdTask);
    }

    /**
     * Worker class to create Normal Tasks.
     * @param userLine Line that is inputted by the user.
     */
    private static void createNormalTask(String userLine) {
        // Create default Task and add to tasksList
        Task createdTask = createTask(userLine, NORMAL_TYPE);
        addTask(createdTask);
        printAddTaskMessage(createdTask);
    }

    /**
     * Handles the scenario if a file is not found.
     */
    private static void handleFileNotFound() {
        System.out.println(formatWithHeading("Directory (data) and/or File (data/records.txt) does not exist!",
                TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Create a directory named data in your local directory!",
                TERMINATOR_FORMATTING));
        handleByeSequence();
    }

    /**
     * Handle the bye sequence for Terminator.
     */
    private static void handleByeSequence() {
        toContinue = false;
        printGoodByeMessage();
    }

    /**
     * Generates string version of all the tasks to be stored.
     * @return A String of all the tasks to be stored in a text file.
     */
    private static String generateStringToStore() {
        StringJoiner taskJoiner = new StringJoiner(System.lineSeparator());
        for (Task t: tasksList) {
            taskJoiner.add(t.toFileStringFormat());
        }
        return taskJoiner.toString();
    }

    /**
     * Stores given string into the file path.
     * @param stringToBeSaved A string to be be stored into the file.
     */
    private static void writeToFile(String stringToBeSaved) {
        try {
            FileWriter fw = new FileWriter(FILE_LOCATION);
            fw.write(stringToBeSaved);
            fw.close();
        } catch (IOException e) {
            handleFileNotFound();
        }
    }

    /**
     * Handler function for updating Tasks to File.
     */
    private static void updateTasksToFile() {
        String generatedString = generateStringToStore();
        writeToFile(generatedString);
    }

    /**
     * Reads Strings from file, add them to arraylist and return arraylist.
     */
    private static ArrayList<String> readFromFile() {
        ArrayList<String> returnArrayList = new ArrayList<String>();
        try {
            File fileObject = new File(FILE_LOCATION);
            Scanner scanObject = new Scanner(fileObject);

            // Iterate through the lines and add it into the arrayList.
            while (scanObject.hasNext()) {
                returnArrayList.add(scanObject.nextLine());
            }
        } catch (FileNotFoundException e) {
            handleFileNotFound();
        }
        return returnArrayList;
    }

    /**
     * Parse the String into different parts based on format.
     * @param fileFormattedString A string extracted from the input file.
     * @param eventType The type of task to extract based on.
     * @return String array of parsed Strings
     */
    private static String[] parseFileFormattedString(String fileFormattedString, String eventType) {
        String[] returnArray = new String[3];
        String[] splitResult = fileFormattedString.split(Pattern.quote(DELIMINATOR_FOR_FILE));
        returnArray[COMPLETION_INDEX] = splitResult[1];
        returnArray[TASK_NAME_INDEX] = splitResult[2];
        if (Objects.equals(eventType, DEADLINE_TYPE) || Objects.equals(eventType, EVENT_TYPE)) {
            returnArray[DATE_TIME_INDEX] = splitResult[3];
        }
        return returnArray;
    }

    /**
     * Facilitates the task object creation given the strings from the file.
     * @param fileFormattedTaskStrings An ArrayList of Strings read from a file
     */
    private static void facilitateTaskObjectCreation(ArrayList<String> fileFormattedTaskStrings) {
        for (String fileFormatString: fileFormattedTaskStrings) {
            // Get the first char from the string
            String firstChar = fileFormatString.substring(0,1);
            switch (firstChar.toUpperCase()) {
            case EVENT_TYPE:
                createEventTask(fileFormatString, FROM_FILE);
                break;
            case DEADLINE_TYPE:
                createDeadlineTask(fileFormatString, FROM_FILE);
                break;
            default:
                createToDoTask(fileFormatString, FROM_FILE);
            }
        }
    }

    /**
     * Handler function for updating Tasks from File.
     */
    private static void loadTasksFromFile() {
        ArrayList<String> contentFromFile =  readFromFile();
        facilitateTaskObjectCreation(contentFromFile);
    }

    private static void printUnknownCommand() {
        System.out.println(formatWithHeading("Entered command is invalid", TERMINATOR_FORMATTING));
    }

    /**
     * Executes the command based on what is given by the user.
     * @param userLine Line that is inputted by the user.
     */
    private static void executeCommand(String userLine) {
        String keyword = userLine.split(" ")[KEYWORD_INDEX];

        // Checks for the input for keywords BYE and LIST
        switch (keyword.toUpperCase()) {
        case DONE_STRING:
            // Go to helper function to mark task as done
            handleDoneTask(userLine);
            break;
        case DELETE_STRING:
            // Go to helper function to mark task as done
            handleDeleteTask(userLine);
            break;
        case LIST_STRING:
            // Print Tasks with in-built tasksList
            printTasks();
            break;
        case BYE_STRING:
            // Stop loop and print Goodbye
            handleByeSequence();
            break;
        case TODO_STRING:
            createToDoTask(userLine, FROM_USER);
            break;
        case DEADLINE_STRING:
            createDeadlineTask(userLine, FROM_USER);
            break;
        case EVENT_STRING:
            createEventTask(userLine, FROM_USER);
            break;
        default:
            printUnknownCommand();
            break;
        }

        // Update the list
        updateTasksToFile();

    }

    /**
     * Handles the creation of done tasks.
     * @param userLine Line of input from user.
     */
    private static void handleDoneTask(String userLine) {
        try {
            // Parse out task number from user input
            int taskNumber = getTaskNumberFromInput(userLine);
            // Update the list and print respective message
            updateTaskCompletionStatus(taskNumber);
            printUpdateMessage(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            printOutOfBoundsMessage();
        }
    }

    /**
     * Handler function to delete task from list.
     * @param userLine Line of input from user.
     */
    private static void handleDeleteTask(String userLine) {
        try {
            // Parse out task number from user input
            int taskNumber = getTaskNumberFromInput(userLine);
            // Print respective message and delete the task
            printDeleteMessage(taskNumber);
            deleteTaskFromList(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            printOutOfBoundsMessage();
        }
    }

    /**
     * Main Function that is called upon program execution.
     * @param args System Arguments added to the program.
     */
    public static void main(String[] args) {
        // Prints opening message
        printHelloMessage();
        loadTasksFromFile();

        // Continue Running Loop until bye is called
        while (toContinue) {
            // Gets user input
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }
}
