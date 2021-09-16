import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    //@@author nus-cs2113-AY2122S1-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts/edit/master/src/main/java/Contacts1.java
    // with minor modifications
    private static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    private static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    private static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";


    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_EXIT_DESC = "Exits the program.";
    public static final String COMMAND_EXIT_EXAMPLE = COMMAND_EXIT_WORD;

    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_LIST_DESC = "Displays all tasks as a list with index numbers.";
    public static final String COMMAND_LIST_EXAMPLE = COMMAND_LIST_WORD;

    public static final String COMMAND_DONE_WORD = "done";
    public static final String COMMAND_DONE_DESC = "Marks a task as complete.";
    public static final String COMMAND_DONE_PARAMETER = "TASK_INDEX";
    public static final String COMMAND_DONE_EXAMPLE = COMMAND_DONE_WORD + " 1";

    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_TODO_DESC = "Adds a task of \"TODO\" type to the list";
    public static final String COMMAND_TODO_PARAMETER = "TASK_DESCRIPTION";
    public static final String COMMAND_TODO_EXAMPLE = COMMAND_TODO_WORD + " do laundry";

    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT = "/at ";

    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_DEADLINE_DESC = "Adds a task of \"DEADLINE\" type to the list";
    public static final String COMMAND_DEADLINE_PARAMETER = "TASK_DESCRIPTION "
            + TASK_DATA_PREFIX_DEADLINE
            + "DEADLINE";
    public static final String COMMAND_DEADLINE_EXAMPLE = COMMAND_DEADLINE_WORD + " do laundry /by 3pm";


    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_EVENT_DESC = "Adds a task of \"EVENT\" type to the list";
    public static final String COMMAND_EVENT_PARAMETER = "TASK_DESCRIPTION "
            + TASK_DATA_PREFIX_EVENT + "TIME_RANGE";
    public static final String COMMAND_EVENT_EXAMPLE = COMMAND_EVENT_WORD + " do laundry /at 2-3pm";

    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_DELETE_DESC = "Deletes a task";
    public static final String COMMAND_DELETE_PARAMETER = "TASK_INDEX";
    public static final String COMMAND_DELETE_EXAMPLE = COMMAND_DELETE_WORD + " 1";


    /** List of all Tasks and Length of list*/
    private static ArrayList<Task> allTasks;

    /** Create a database file **/
    private static final String FILE_PATH = "data/task_list.txt";
    private static final String FOLDER_PATH = "data";


    private static boolean exit = false;

    /** Text Messages **/
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "____________________________________________________________" + System.lineSeparator();
    private static final String MESSAGE_EMPTY_LIST = "☹ There are currently no tasks in the list";
    private static final String MESSAGE_EXIT = "Bye, see you!";
    private static final String MESSAGE_NEW_FOLDER_CREATED = "New data folder created.";


    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        showWelcomeMessage();
        initTaskList();
        while(!exit) {
            String userInput = getUserInput();
            executeCommand(userInput);
        }
        System.out.println(MESSAGE_EXIT + System.lineSeparator() + DIVIDER);
    }

    private static void exitProgram() {
        exit = true;
    }

    private static String getUserInput() {
        String input = SCANNER.nextLine();
        System.out.println(DIVIDER);
        return input;
    }

    private static void executeCommand(String userInput) {
        final String[] commandTypeAndParams =  splitCommandWordAndArgs(userInput);
        final String commandType = commandTypeAndParams[0];
        final String commandParams = commandTypeAndParams[1];

        try {
            switch (commandType) {
            case COMMAND_EXIT_WORD:
                exitProgram();
                break;
            case COMMAND_LIST_WORD:
                executeListTasks();
                System.out.println(DIVIDER);
                break;
            case COMMAND_DONE_WORD:
                executeCompleteTask(commandParams);
                System.out.println(DIVIDER);
                break;
            case COMMAND_TODO_WORD:
            case COMMAND_DEADLINE_WORD:
            case COMMAND_EVENT_WORD:
                addTasks(commandType, commandParams);
                System.out.println(DIVIDER);
                break;
            case COMMAND_DELETE_WORD:
                deleteTask(commandParams);
                System.out.println(DIVIDER);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            showInvalidCommandMessage();
        } catch (EmptyParamsException e) {
            showEmptyParamMessage(commandType);
        }
    }

    /**
     *Checks the type of Task and correspondingly calls the add function
     *
     * @param taskType type of task to add
     * @param taskParams string containing the task parameters
     * @throws EmptyParamsException If the task parameter is left empty
     */
    private static void addTasks(String taskType, String taskParams) throws EmptyParamsException {
        if (taskParams.equals("")) {
            throw new EmptyParamsException();
        }
        createFolder();
        updateDataFile();
        switch(taskType) {
        case COMMAND_TODO_WORD:
            addToDoTask(taskParams);
            break;
        case COMMAND_DEADLINE_WORD:
            addDeadlineTask(taskParams);
            break;
        case COMMAND_EVENT_WORD:
            addEventTask(taskParams);
            break;
        }
    }

    /**
     * Adds a task of 'todo' type to the list
     *
     * @param toDoDescription the description of the ToDo task
     */
    private static void addToDoTask(String toDoDescription) {
        try {
            ToDo newToDo = new ToDo(toDoDescription);
            allTasks.add(newToDo);
            showTaskMessage(newToDo);
            appendToDataFile("T / 0 / " + toDoDescription);
        } catch (IOException e){
            showIOExceptionMessage(e);
        }

    }

    private static void addDeadlineTask(String taskParams) {
        try {
            String[] taskDescriptionAndDeadline = taskParams.split("/by", 2);
            String taskDescription = taskDescriptionAndDeadline[0];
            String deadline = taskDescriptionAndDeadline[1];
            if(taskDescription.equals("") || deadline.equals("")) {
                showMissingTaskDescriptionMessage();
            } else {
                Deadline newDeadline = new Deadline(taskDescription, deadline);
                allTasks.add(newDeadline);
                showTaskMessage(newDeadline);
                appendToDataFile("D / 0 / " + taskDescription + "|" + deadline);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            showInvalidDeadlineMessage();
        } catch (IOException e){
            showIOExceptionMessage(e);
        }
    }


    private static void addEventTask(String taskParams) {
        try {
            String[] taskDescriptionAndTimeRange = taskParams.split("/at", 2);
            String taskDescription = taskDescriptionAndTimeRange[0];
            String timeRange = taskDescriptionAndTimeRange[1];
            if(taskDescription.equals("") || timeRange.equals("")) {
                showMissingTaskDescriptionMessage();
            } else {
                Event newEvent = new Event(taskDescription, timeRange);
                allTasks.add(newEvent);
                showTaskMessage(newEvent);
                appendToDataFile("E / 0 / " + taskDescription + "|" + timeRange);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            showInvalidEventMessage();
        } catch (IOException e){
            showIOExceptionMessage(e);
        }
    }

    private static void executeCompleteTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (allTasks.get(taskIndex).isDone()) {
                System.out.println("You have already marked this task as done! Time to move on :)");
            } else {
                allTasks.get(taskIndex).markAsDone();
                System.out.println("Awesome! You've completed the following task:");
                System.out.println(" [X] " + allTasks.get(taskIndex).getDescription());
                updateDataFile();
            }
        } catch (NumberFormatException e) {
            showInvalidTaskIndexMessage(COMMAND_DONE_WORD);
        } catch (IndexOutOfBoundsException e) {
            showExceedTaskIndexMessage();
        }
    }

    public static void deleteTask(String userInput) {
        try {
            int indexOfTaskToDelete = Integer.parseInt(userInput) - 1;
            Task taskToDelete = allTasks.get(indexOfTaskToDelete);
            System.out.println("Successfully deleted the following task:" + System.lineSeparator() +
                    "   " + taskToDelete);
            allTasks.remove(taskToDelete);
            System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
            updateDataFile();
        } catch (NumberFormatException e) {
            showInvalidTaskIndexMessage(COMMAND_DELETE_WORD);
        } catch (IndexOutOfBoundsException e) {
            showExceedTaskIndexMessage();
        }
    }

    private static void executeListTasks() {
        int tasksCount = allTasks.size();
        if (tasksCount == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
        }
        else {
            for(int i = 0; i < tasksCount; i++){
                System.out.println((i + 1) + "." + allTasks.get(i));
            }
        }
    }


    public static void updateDataFile() {
        try {
            clearDataFile();
            for (Task task: allTasks) {
                String textToAppend = null;
                String isDone = task.isDone ? "1" : "0";
                if (task instanceof ToDo) {
                    textToAppend = "T / " + isDone + " / " + task.description;
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String taskParams = deadline.description + "|" + deadline.getDeadline();
                    textToAppend = "D / " + isDone + " / " + taskParams;
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String taskParams = event.description + "|" + event.getEventTimeRange();
                    textToAppend = "E / " + isDone + " / " + taskParams;
                }
                appendToDataFile(textToAppend);
            }
        } catch (IOException e) {
            showIOExceptionMessage(e);
        }
    }

    private static void appendToDataFile(String textToAppend) throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fileWriter.write(textToAppend + System.lineSeparator());
        fileWriter.close();
    }

    private static void clearDataFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write("");
        fileWriter.close();
    }

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        return split.length == 2 ? new String[] { split[0].toLowerCase(), split[1] } : new String[] { split[0].toLowerCase() , "" };
    }

    private static void initTaskList() {
        allTasks = new ArrayList<>();
        try {
            File dataFile = new File(FILE_PATH);
            Scanner s = new Scanner(dataFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] taskTypeAndParams = task.split(" / ");
                String taskType = taskTypeAndParams[0];
                String isDone = taskTypeAndParams[1];
                String taskParams =  taskTypeAndParams[2];
                int taskIndex = allTasks.size() - 1;
                switch(taskType) {
                case "T":
                    allTasks.add(new ToDo(taskParams));
                    if (isDone.equals("1")) {
                        allTasks.get(taskIndex).markAsDone();
                    }
                    break;
                case "D":
                    String[] taskDescriptionAndDeadline = taskParams.split(" \\| ");
                    String deadlineDescription = taskDescriptionAndDeadline[0];
                    String deadline = taskDescriptionAndDeadline[1];
                    allTasks.add(new Deadline(deadlineDescription, deadline));

                    if (isDone.equals("1")) {
                        allTasks.get(taskIndex).markAsDone();
                    }
                    break;
                case "E":
                    String[] taskDescriptionAndTimeRange = taskParams.split(" \\| ");
                    String eventDescription = taskDescriptionAndTimeRange[0];
                    String timeRange = taskDescriptionAndTimeRange[1];
                    allTasks.add(new Event(eventDescription, timeRange));
                    if (isDone.equals("1")) {
                        allTasks.get(taskIndex).markAsDone();
                    }
                    break;
                default:
                    throw new CorruptedFileException();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            createFolder();
        } catch (CorruptedFileException | ArrayIndexOutOfBoundsException e) {
            showInvalidFileFormatMessage();
        }
    }

    private static void createFolder() {
        File folder = new File(FOLDER_PATH);
        boolean folderIsCreated = folder.mkdir();
        if (folderIsCreated) {
            System.out.println(MESSAGE_NEW_FOLDER_CREATED);
            System.out.println(DIVIDER);
        }
    }

    private static void showTaskMessage(Task task) {
        System.out.println("Successfully added. Here's the added task: ");
        System.out.println("   " + task);
        int tasksCount = allTasks.size();
        if (tasksCount == 1) {
            System.out.println("There is 1 task in the list now.");
        } else {
            System.out.println("There are " + tasksCount + " tasks in the list now.");
        }
    }

    private static void showWelcomeMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "   Hello! I'm Duke\n"
                + "Here is a list of commands I can understand:\n" + System.lineSeparator()
                + showUsageInfoForAllCommands() + DIVIDER);
    }

    /*
     * ===============================================
     *         COMMAND HELP INFO FOR USERS
     * ===============================================
     */

    /** Prints usage info for all commands **/
    private static String showUsageInfoForAllCommands() {
        return  getUsageInfoForTodoCommand() + System.lineSeparator()
                + getUsageInfoForDeadlineCommand() + System.lineSeparator()
                + getUsageInfoForEventCommand() + System.lineSeparator()
                + getUsageInfoForDoneCommand() + System.lineSeparator()
                + getUsageInfoForDeleteCommand() + System.lineSeparator()
                + getUsageInfoForListCommand() + System.lineSeparator()
                + getUsageInfoForExitCommand() + System.lineSeparator();
    }

    /** Prints the string for 'todo' command usage instruction **/
    private static String getUsageInfoForTodoCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_TODO_WORD, COMMAND_TODO_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_TODO_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_TODO_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'deadline' command usage instruction **/
    private static String getUsageInfoForDeadlineCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DEADLINE_WORD, COMMAND_DEADLINE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DEADLINE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DEADLINE_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'event' command usage instruction **/
    private static String getUsageInfoForEventCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EVENT_WORD, COMMAND_EVENT_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_EVENT_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EVENT_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'done' command usage instruction **/
    private static String getUsageInfoForDoneCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DONE_WORD, COMMAND_DONE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DONE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DONE_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'delete' command usage instruction **/
    private static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DELETE_WORD, COMMAND_DELETE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DELETE_PARAMETER) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'list' command usage instruction **/
    private static String getUsageInfoForListCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_LIST_WORD, COMMAND_LIST_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + System.lineSeparator();
    }

    /** Prints the string for 'exit' command usage instruction **/
    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EXIT_WORD, COMMAND_EXIT_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE) + System.lineSeparator();
    }


    /*
     * ===========================================
     *           ERROR MESSAGES
     * ===========================================
     */

    private static void showInvalidCommandMessage() {
        System.out.println("☹ Sorry, I did not understand your command.");
        System.out.println(DIVIDER);
    }

    private static void showInvalidDeadlineMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /by DEADLINE");
    }

    private static void showInvalidEventMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /at TIME_RANGE");
    }

    private static void showEmptyParamMessage(String commandType) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty." + System.lineSeparator(), commandType);
        System.out.println(DIVIDER);
    }

    private static void showMissingTaskDescriptionMessage() {
        System.out.println("☹ OOPS!!! Your task description is missing some key fields.");
    }

    private static void showInvalidTaskIndexMessage(String commandType) {
        System.out.printf("☹ OOPS!!! \"%s\" command should be followed by Task index."+ System.lineSeparator(), commandType);
    }

    private static void showExceedTaskIndexMessage() {
        int tasksCount = allTasks.size();
        if (tasksCount == 0) {
            System.out.println("☹ OOPS!!! You do not have any tasks in the list yet!");
        }
        else {
            System.out.println("☹ OOPS!!! The task index you are referring to does not exist in the list!");
        }
    }

    private static void showIOExceptionMessage(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    private static void showInvalidFileFormatMessage() {
        System.out.println("Data file is corrupted.");
        System.out.println(DIVIDER);
    }
}
