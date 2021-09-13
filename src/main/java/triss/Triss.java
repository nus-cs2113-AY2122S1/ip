package triss;

import triss.exception.TrissException;
import triss.task.Deadline;
import triss.task.Event;
import triss.task.Task;
import triss.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Triss {

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
    private static final ArrayList<Task> tasks = new ArrayList<>();

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

        // Check for any stored data
        try {
            initialiseDataStorage();
        } catch (TrissException error) {
            System.out.println(error.getMessage());
        } catch (IOException error) {
            System.out.println("Tasks storage has been corrupted! Try restarting.");
        }

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            // Get the next line of input, and parse it to find the user's command (first word in input)
            String userInput = getUserInput(in);
            String userCommand = parseUserInput(userInput, 0);
            printLine(SEPARATOR_LINE);

            // Perform actions based on user's command
            switch (userCommand) {
            case "bye":
                saveTasks();
                printShutdownMessage();
                break;
            case "list":
                printAllTasks();
                break;
            case "done":
                handleUserMarkingTaskAsDone(userInput);
                break;
            case "delete":
                handleUserDeletingTask(userInput);
                break;
            default:
                try {
                    handleUserCreatingTask(userInput);
                } catch (TrissException exception) {
                    printLine(exception.getMessage());
                }
                break;
            }

            saveTasks();
            printLine(SEPARATOR_LINE);

        }
    }


    private static void handleUserDeletingTask(String userInput) {
        // Get number of task after the term "done"
        int indexOfRemovableTask;
        // Throw exception if user did not type a number after "done"
        try {
            indexOfRemovableTask = Integer.parseInt(parseUserInput(userInput, 1)) - 1;
        } catch (Exception e) {
            printLine("Ach, nee! That task does not exist.");
            return;
        }


        // If task does not exist, do not delete any task
        if (indexOfRemovableTask >= tasks.size() || indexOfRemovableTask < 0) {
            printLine("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasks.get(indexOfRemovableTask);

        // Remove task from tasks
        tasks.remove(chosenTask);
        printLine("Wunderbar! This task has been deleted:");

        // Print out the task in the following format: "    [X] Task"
        printLine("    " + chosenTask.printTask());
    }

    private static void initialiseDataStorage() throws IOException, TrissException {
        File dataDirectory = new File("data");
        File storedTasks = new File("data/storedtasks.txt");
        if (dataDirectory.exists()) {
            Scanner fileReader = new Scanner(storedTasks);
            while (fileReader.hasNext()) {
                String lineInFile = fileReader.nextLine();
                String[] taskDetails = lineInFile.split(",");
                switch (taskDetails[0]) {
                case "[T]":
                    createNewTodo("todo " + taskDetails[2], true);
                    break;
                case "[E]":
                    createNewEvent("event " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                case "[D]":
                    createNewDeadline("deadline " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                default:
                    throw new TrissException("Tasks storage has been corrupted! Try restarting.");
                }

                if (taskDetails[1].equals("[X]")) {
                    tasks.get(tasks.size() - 1).setDone(true);
                }

            }
        } else {
            dataDirectory.mkdir();
            FileWriter fw = new FileWriter(storedTasks.getAbsoluteFile());
            fw.close();
        }
    }

    private static void saveTasks() {

        try {
            FileWriter fw = new FileWriter("data/storedtasks.txt");

            for (Task task:tasks) {
                fw.write(task.printTaskForStoring() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException error) {
            System.out.println("Tasks storage has been corrupted! Try restarting.");
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
    private static void handleUserCreatingTask(String userInput) throws TrissException {
        String taskType = parseUserInput(userInput, 0);

        switch (taskType) {
        case "deadline":
            createNewDeadline(userInput, false);
            break;
        case "event":
            createNewEvent(userInput, false);
            break;
        case "todo":
            createNewTodo(userInput, false);
            break;
        default:
            String errorMessage = "Oof, I didn't understand your command! Let's try that again.\n"
                    + " \n" + "Type a todo in this format:\n" + "    todo Eat with Friends";
            throw new TrissException(errorMessage);
        }
    }

    /**
     * Creates a new todo based on user's input.
     * If user did not type in this format: "todo Eat with Friends", it asks the user to try again.
     * @param userInput Any user input starting with the words "todo"
     */
    private static void createNewTodo(String userInput, boolean isSilent) throws TrissException {
        String taskName;
        taskName = userInput.substring(END_INDEX_OF_WORD_TODO).trim();

        if (taskName.isBlank()) {
            String errorMessage = "You didn't specify a name for your todo! Let's try that again.\n"
                    + " \n" + "Type a todo in this format:\n" + "    todo Eat with Friends";
            throw new TrissException(errorMessage);
        }

        // Add todo to tasks
        Task newTodo = new Todo(taskName);
        tasks.add(newTodo);

        // Then, echo the task if not silent
        if (!isSilent) {
            printLine("I've added: " + newTodo.printTask());
        }
    }

    /**
     * Creates a new event based on the user's input.
     * User has to type the input in this format: "event Stay in a log cabin /Friday the 13th".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "event".
     */
    private static void createNewEvent(String userInput, boolean isSilent) throws TrissException {
        String taskName;
        String eventTiming;

        // Parse the task's name from the user's input
        try {
            taskName = userInput.substring(END_INDEX_OF_WORD_EVENT, userInput.indexOf("/")).trim();
            eventTiming = userInput.substring(userInput.indexOf("/") + 1).trim();
        } catch (Exception error) {
            String errorMessage = "You didn't format your event properly!\n"
                    + " \n"
                    + "Try inserting an event in this format:\n"
                    + "    event Stay in a log cabin /Friday the 13th";
            throw new TrissException(errorMessage);
        }

        // Catch other possible errors
        // Throw error if user did not type in a name for the task
        if (taskName.isBlank()) {
            throw new TrissException("Your event name is blank! Let's try that again.");
        }

        // Throw error if user did not type in a timing for the event
        if (eventTiming.isBlank()) {
            throw new TrissException("You didn't insert a date in your event! Let's try that again.");
        }

        // Add event to tasks
        Event newEvent = new Event(taskName, eventTiming);
        tasks.add(newEvent);

        // Then, echo the task if not silent
        if (!isSilent) {
            printLine("I've added: " + newEvent.printTask());
        }
    }

    /**
     * Creates a new deadline based on the user's input.
     * User has to type the input in this format: "deadline Meet with Friends /12th July".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "deadline".
     */
    private static void createNewDeadline(String userInput, boolean isSilent) throws TrissException {
        String deadlineDate;
        String taskName;

        try {
            deadlineDate = userInput.substring(userInput.indexOf("/") + 1).trim();
            taskName = userInput.substring(END_INDEX_OF_WORD_DEADLINE, userInput.indexOf("/")).trim();
        } catch (Exception error) {
            String errorMessage = "You didn't write your deadline properly!\n"
                    + " \n"
                    + "Try inserting a deadline in this format:\n"
                    + "    deadline Meet with Friends /12th July";
            throw new TrissException(errorMessage);
        }

        // Catch other possible errors
        // Throw error if user did not type in a name for the task
        if (taskName.isBlank()) {
            throw new TrissException("Your deadline name is blank! Let's try that again.");
        }

        // Throw error if user did not type in a timing for the event
        if (deadlineDate.isBlank()) {
            throw new TrissException("You didn't insert a date in your deadline! Let's try that again.");
        }

        // Add deadline to tasks
        Deadline newDeadline = new Deadline(taskName, deadlineDate);
        tasks.add(newDeadline);

        // Then, echo the task if not silent
        if (!isSilent) {
            printLine("I've added: " + newDeadline.printTask());
        }
    }

    /**
     * Mark user task as done, if request is valid.
     * Stops if user did not specify a task.
     * Stops if user's chosen task does not exist.
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
        if (indexOfCompletedTask >= tasks.size() || indexOfCompletedTask < 0) {
            printLine("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasks.get(indexOfCompletedTask);

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
        for (Task task:tasks) {
            printLine(tasks.indexOf(task) + 1 + "." + task.printTask());
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
