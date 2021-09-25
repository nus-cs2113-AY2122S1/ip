import duke.DukeException;
import duke.tasks.FileManager;
import duke.tasks.Task;
import duke.tasks.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final String BY_DIVIDER = "/by";
    public static final String AT_DIVIDER = "/at";
    public static final String LINE_SEPARATOR = "_____________________________";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String FILE_PATH = "data/duke.txt";
    public static final String DIRECTORY_PATH = "data";

    public static boolean canRunDuke = true;

    /**
     * Splits the input string and returns the command at the start of the string
     *
     * @param args the input string
     * @return the command the user entered
     */
    public static String getCommand(String args) {
        String[] words = args.split(" ");
        String[] commandArray = Arrays.copyOf(words, 1);
        String command = getFormattedString(Arrays.toString(commandArray));
        return command;
    }

    /**
     * Splits the input string and returns the item after the command
     *
     * @param args the input string
     * @return the item after the command the user entered
     */
    public static String getItem(String args) {
        String[] words = args.split(" ");
        String[] itemArray = Arrays.copyOfRange(words, 1, words.length);
        String item = getFormattedString(Arrays.toString(itemArray));
        return item;
    }

    /**
     * Formats the string that was converted from an array. Delete brackets and commas
     *
     * @param args the input string that was converted from an array
     * @return the formatted string without brackets and commas
     */
    public static String getFormattedString(String args) {
        String formattedString = args.replace(",", "")
                .replace("]", "")
                .replace("[", "")
                .trim();
        return formattedString;
    }


    /**
     * Checks if the input string has an empty item after a command
     *
     * @param args the input string the user entered
     * @return true if the item after the command is empty, false otherwise
     */
    public static boolean isEmptyItem(String args) {
        if (getItem(args).equals("")) {
            return true;
        }
        return false;
    }

    /**
     * returns if there is a time entered for a deadline or event task
     *
     * @param args the input string the user entered
     * @return true if there is a time entered, false otherwise
     */
    public static boolean canGetTime(String args) {
        String description = getItem(args);
        String[] time = description.substring(description.indexOf("/")).split(" ");
        if (time.length < 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the item from the user input is invalid for deadline and event tasks
     *
     * @param args the item to be checked
     * @return true if the item does not contain a '/by' or does not have time after /by, false otherwise
     */
    public static boolean isInvalidDeadline(String args) {
        if (!getItem(args).contains(BY_DIVIDER)) {
            return true;
        }
        if (!canGetTime(args)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the item from the user input is invalid for deadline and event tasks
     *
     * @param args the item to be checked
     * @return true if the item does not contain a '/at' or does not have time after /at, false otherwise
     */
    public static boolean isInvalidEvent(String args) {
        if (!getItem(args).contains(AT_DIVIDER)) {
            return true;
        }
        if (!canGetTime(args)) {
            return true;
        }
        return false;
    }

    /**
     * Reads the input and command entered by the user and execute task according to user command
     *
     * @param inputStr the full input the user entered
     * @param command the command parsed from the input
     * @param taskManager the task manager instance handling the tasks
     * @param fileManager the file manager handling the files
     *
     **/
    public static void executeCommand(String inputStr, String command, TaskManager taskManager, FileManager fileManager) throws DukeException {
        switch (command) {
        case COMMAND_EXIT:
            fileManager.writeArrayToFile(taskManager);
            System.out.println(LINE_SEPARATOR);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(LINE_SEPARATOR);
            canRunDuke = false;
            break;
        case COMMAND_LIST:
            taskManager.printTaskList();
            break;
        case COMMAND_DONE:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter the task to be marked as done?");
            } else if (taskManager.getNumberOfTasksUndone() == 0) {
                throw new DukeException("Oops, there are no tasks to be marked done!");
            } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(getItem(inputStr))) {
                throw new DukeException("Oops, there is no task " + Integer.parseInt(getItem(inputStr)) + "!");
            } else if (taskManager.isTaskDone(inputStr)) {
                throw new DukeException("Oops, this task is already marked as done!");
            } else {
                taskManager.markTaskAsDone(inputStr);
                break;
            }
        case COMMAND_TODO:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of a ToDo cannot be empty!");
            }
            String item = getItem(inputStr);
            taskManager.addToDoTaskToList(item);
            break;
        case COMMAND_DEADLINE:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of a deadline cannot be empty!");
            } else if (isInvalidDeadline(inputStr)) {
                throw new DukeException("Oops, the time of a deadline cannot be empty!");
            }
            item = getItem(inputStr);
            taskManager.addDeadlineTaskToList(item);
            break;
        case COMMAND_EVENT:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of an event cannot be empty!");
            } else if (isInvalidEvent(inputStr)) {
                throw new DukeException("Oops, the time of an event cannot be empty!");
            }
            item = getItem(inputStr);
            taskManager.addEventTaskToList(item);
            break;
        case COMMAND_DELETE:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter the task to be deleted?");
            } else if (taskManager.getNumberOfTasksAdded() == 0) {
                throw new DukeException("Oops, there are no tasks in the list yet!");
            } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(getItem(inputStr))) {
                throw new DukeException("Oops, there is no task " + Integer.parseInt(getItem(inputStr)) + "!");
            } else {
                taskManager.deleteTask(inputStr);
            }
            break;
        case COMMAND_FIND:
            if (isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter a keyword?");
            }
            String keyword = getItem(inputStr);
            printArrayList(taskManager.findTask(keyword));
            break;
        default:
            throw new DukeException("Oops, command not recognised!");
        }
    }

    public static void printArrayList(ArrayList<Task> arrayList) {
        System.out.println(LINE_SEPARATOR);
        arrayList.stream()
                .forEach(System.out::println);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * prints welcome message
     *
     */
    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        // greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        // to read input on each new line, Duke constantly scans input in this loop
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        FileManager fileManager = new FileManager();
        try {
            File taskFile = new File(FILE_PATH);
            fileManager.retrieveFile(taskFile, taskManager);
        } catch (FileNotFoundException e) {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }

        while(canRunDuke) {
            String inputStr = sc.nextLine();
            String command = getCommand(inputStr);
            try {
                executeCommand(inputStr, command, taskManager, fileManager);
            } catch (DukeException dukeException) {
                System.out.println(LINE_SEPARATOR);
                System.out.println(dukeException.getMessage());
                System.out.println(LINE_SEPARATOR);
            }
        }
    }

}
