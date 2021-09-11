package duke;

import duke.exception.CorruptedDataException;
import duke.exception.EmptyArgumentException;
import duke.exception.EmptyParameterException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * The input scanner for the program
     */
    public static final Scanner SCANNER_INPUT = new Scanner(System.in);

    /**
     * An array that stores all the tasks
     */
    public static ArrayList<Task> taskList;

    /**
     * Stores the most recent input from the user in String format
     */
    public static String strInput = "";

    /**
     * "false" by default. "true" when session with duke is to be ended.
     */
    public static boolean isSessionEnding = false;

    public static String taskDataPath;

    public static void main(String[] args) {
        initDuke();
        printGreetingMessage();

        //Main loop
        while (!isSessionEnding) {
            strInput = SCANNER_INPUT.nextLine();
            String command = extractCommand(strInput);
            switch (command) {
            case "bye":
                isSessionEnding = true;
                printGoodbyeMessage();
                break;

            case "list":
                printTaskList();
                break;

            case "done":
                try {
                    markTaskAsDone(extractContent(strInput));
                } catch (NumberFormatException e) { //not a number
                    printBorder();
                    System.out.println("Not a number!");
                    printBorder();
                } catch (EmptyArgumentException e) {
                    printBorder();
                    System.out.println("Please specify the index of the task to be marked as done!");
                    System.out.println("use: done [task index]");
                    printBorder();
                }
                break;

            case "todo":
                addTodo(strInput);
                break;

            case "deadline":
                addDeadline(strInput);
                break;

            case "event":
                addEvent(strInput);
                break;

            case "delete":
                try {
                    deleteTask(extractContent(strInput));
                } catch (NumberFormatException e) { //not a number
                    printBorder();
                    System.out.println("Not a number!");
                    printBorder();
                } catch (EmptyArgumentException e) {
                    printBorder();
                    System.out.println("Please specify the index of the task to be marked as done!");
                    System.out.println("use: delete [task index]");
                    printBorder();
                }
                break;

            default:
                printInvalidCommand();
                break;
            }
        }
    }

    /*
     * ===============================================
     *           Task class related functions
     * ===============================================
     */

    /**
     * Adds a new Event type task to the taskList
     *
     * @param input the input string
     */
    private static void addEvent(String input) {
        printBorder();

        try {
            String content = extractContent(input);
            String descr = extractDescrFromEvent(content);
            String at = extractAtFromEvent(content);
            taskList.add(new Event(descr, at));
            updateDataFile();
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the event details!");
            System.out.println("use: event [description] /at [date]");
        } catch (EmptyParameterException e) {
            System.out.println("use: event [description] /at [date]");
        }
        printBorder();
    }

    /**
     * Adds a new Deadline type task to the taskList
     *
     * @param input the input string
     */
    private static void addDeadline(String input) {

        printBorder();
        try {
            String content = extractContent(input);
            String descr = extractDescrFromDeadline(content);
            String by = extractByFromDeadline(content);
            taskList.add(new Deadline(descr, by));
            updateDataFile();
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the deadline details!");
            System.out.println("use: deadline [description] /by [date]");
        } catch (EmptyParameterException e) {
            System.out.println("use: deadline [description] /by [date]");
        }
        printBorder();
    }

    /**
     * Adds a new Todo type task to the taskList
     *
     * @param input the input string
     */
    private static void addTodo(String input) {
        printBorder();
        try {
            String content = extractContent(input);
            taskList.add(new Todo(content));
            updateDataFile();
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the todo description!");
            System.out.println("use: todo [description]");
        }
        printBorder();
    }

    /**
     * Prints the success message for the most recently added task
     */
    private static void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(Task.getTotalTasks() - 1));
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
    }

    /**
     * Extracts the date from the content of an event input (everything after "/at")
     *
     * @param input content of the event input
     * @return the date of the event
     */
    private static String extractAtFromEvent(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/at");
        if ((positionOfSeparator + 3) >= input.length()) {
            throw new EmptyParameterException("event date");
        }
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of an event input (everything before "/at")
     *
     * @param input content of the event input
     * @return the description of the event
     */
    private static String extractDescrFromEvent(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/at");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException("event description");
        }
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the deadline from the content of a deadline input (everything after "/by")
     *
     * @param input content of the deadline input
     * @return the deadline of the deadline
     */
    private static String extractByFromDeadline(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/by");
        if ((positionOfSeparator + 3) >= input.length()) {
            throw new EmptyParameterException("deadline date");
        }
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of a deadline input (everything before "/by")
     *
     * @param input content of the deadline input
     * @return the description of the deadline
     */
    private static String extractDescrFromDeadline(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/by");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException("deadline description");
        }
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the content from an input string (everything except the first word)
     *
     * @param input the input string
     * @return the input string with the first word excluded
     */
    private static String extractContent(String input) throws EmptyArgumentException {
        int firstSpacePosition = input.trim().indexOf(" ");
        if (firstSpacePosition < 0) {
            throw new EmptyArgumentException();
        }
        return input.substring(firstSpacePosition + 1).trim();
    }

    /**
     * Extracts the command from an input string (the first word)
     *
     * @param input the input string
     * @return first word of the input string
     */
    private static String extractCommand(String input) {
        String[] words = input.trim().split(" ");
        return words[0].trim();
    }

    /**
     * Marks the task of the given ranking as done
     *
     * @param taskNumber numerical ranking (as a string) of the task to be marked as done
     */
    private static void markTaskAsDone(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1; //get the index of the task in the taskList array
        printBorder();
        try {
            taskList.get(taskIndex).setDone(true);
            updateDataFile();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number " + (taskIndex + 1) + " does not exist!");
        }
        printBorder();
    }

    /**
     * Deletes the task of the given ranking from the list
     *
     * @param taskNumber numerical ranking (as a string) of the task to be deleted
     */
    private static void deleteTask(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1; //get the index of the task in the taskList array
        printBorder();
        try {
            Task removedTask = taskList.remove(taskIndex);
            Task.decreaseTotalTasks();
            updateDataFile();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number " + (taskIndex + 1) + " does not exist!");
        }
        printBorder();
    }

    /**
     * Prints out the list of tasks and their current status
     */
    private static void printTaskList() {
        printBorder();
        if (Task.getTotalTasks() == 0) {
            System.out.println("There are no tasks!");
            printBorder();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        printBorder();
    }

    /*
     * ===============================================
     *         Basic message printing functions
     * ===============================================
     */

    /**
     * Prints a border made of "_" characters
     */
    private static void printBorder() {
        System.out.println("____________________________________________________________");
    }

    private static void printGreetingMessage() {
        printBorder();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printBorder();
    }

    private static void printGoodbyeMessage() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }

    /**
     * Prints the error message for an invalid command
     */
    private static void printInvalidCommand() {
        printBorder();
        System.out.println("Command not recognized!");
        System.out.println("try the following: \"list\", \"done\", \"todo\", \"deadline\", \"event\", \"bye\"");
        System.out.println("\"delete\"");
        printBorder();
    }

    /*
     * ===============================================
     *            Miscellaneous Functions
     * ===============================================
     */

    /**
     * Initialize Duke and generate taskList
     */
    private static void initDuke() {
        taskList = new ArrayList<>();
        File dataFile = new File("data");
        dataFile.mkdir(); //create "data" folder if it does not exist
        taskDataPath = "data/tasks.txt";
        try {
            loadFileContents(taskDataPath);
            System.out.println("Previous data loaded successfully");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data");
        } catch (CorruptedDataException e) {
            System.out.println("Data file corrupted...");
        }
    }

    /**
     * Tries to load an existing task data file
     *
     * @param filePath String representing the path of the data file
     */
    private static void loadFileContents(String filePath) throws FileNotFoundException, CorruptedDataException {
        File f = new File(filePath);
        Scanner dataScanner = new Scanner(f);
        while (dataScanner.hasNext()) {
            String[] data = dataScanner.nextLine().split(" \\| ");
            switch (data[0]) {
            case "T":
                taskList.add(new Todo(data[2]));
                if (data[1].equals("1")) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            case "D":
                taskList.add(new Deadline(data[2], data[3]));
                if (data[1].equals("1")) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            case "E":
                taskList.add(new Event(data[2], data[3]));
                if (data[1].equals("1")) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            default:
                throw new CorruptedDataException();
            }
        }
    }

    /**
     * Updates the data file to the current state of the task list
     */
    private static void updateDataFile() {
        try {
            FileWriter fw = new FileWriter(taskDataPath);
            fw.write(""); //clear data file before write
            fw.close();
            for (Task task : taskList) {
                task.writeToFile(taskDataPath);
            }
        } catch (IOException e) {
            System.out.println("Failed to update data file");
            System.exit(0);
        }
    }
}
