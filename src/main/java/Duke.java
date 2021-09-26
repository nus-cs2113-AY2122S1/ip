import duke.DukeException;
import duke.Parser;
import duke.tasks.FileManager;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import duke.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
     * Checks if the done command entered is in the correct format with the correct information
     *
     * @param inputStr the input string the user entered containing the command and the description
     * @param taskManager the taskManager class that handles all the task list functions
     * @return returns true if the done command is in the correct format, throws exception otherwise
     * @throws DukeException if there is no description after command, if there are no tasks undone in task list, if the task index entered does not exist, and if the task is already done
     */
    private static boolean isValidDoneCommand(String inputStr, TaskManager taskManager) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, did you forget to enter the task to be marked as done?");
        } else if (taskManager.getNumberOfTasksUndone() == 0) {
            throw new DukeException("Oops, there are no tasks to be marked done!");
        } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(Parser.getItem(inputStr))) {
            throw new DukeException("Oops, there is no task " + Integer.parseInt(Parser.getItem(inputStr)) + "!");
        } else if (taskManager.isTaskDone(inputStr)) {
            throw new DukeException("Oops, this task is already marked as done!");
        } else {
            return true;
        }
    }

    /**
     * Checks if the todo command entered is in the correct format with the correct description
     *
     * @param inputStr the input string the user entered containing the command and the description
     * @return returns true if the todo command is in the correct format, throws exception otherwise
     * @throws DukeException if there is no description after command
     */
    private static boolean isValidToDoCommand(String inputStr) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, the description of a ToDo cannot be empty!");
        } else {
            return true;
        }
    }

    /**
     * Checks if the deadline command entered is in the correct format with the correct description
     *
     * @param inputStr the input string the suer entered containing the command and the description
     * @return returns true if the deadline command is in te correct format, throws exception otherwise
     * @throws DukeException if there is no description after command, and if the time of the deadline is empty
     */
    private static boolean isValidDeadlineCommand(String inputStr) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, the description of a deadline cannot be empty!");
        } else if (Parser.isInvalidDeadline(inputStr)) {
            throw new DukeException("Oops, the time of a deadline cannot be empty!");
        } else {
            return true;
        }
    }

    /**
     * Checks if the event command entered is in the correct format with the correct description
     *
     * @param inputStr the input string the user entered containing the command and the description
     * @return returns true if the event command is in the correct format, throws exception otherwise
     * @throws DukeException if there is no description after command, and if the time of the event is empty
     */
    private static boolean isValidEventCommand(String inputStr) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, the description of an event cannot be empty!");
        } else if (Parser.isInvalidEvent(inputStr)) {
            throw new DukeException("Oops, the time of an event cannot be empty!");
        } else {
            return true;
        }
    }

    /**
     * Checks if the delete command entered is in the correct format with the correct description
     *
     * @param inputStr the input string the user entered containing the command and the description
     * @param taskManager the taskManager class that handles all the task list functions
     * @return returns true if the delete command is in the correct format, throws exception otherwise
     * @throws DukeException if there is no task index entered after command, if there is no tasks in the list to be deleted, and if the task index entered does not exist
     */
    private static boolean isValidDeleteCommand(String inputStr, TaskManager taskManager) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, did you forget to enter the task to be deleted?");
        } else if (taskManager.getNumberOfTasksAdded() == 0) {
            throw new DukeException("Oops, there are no tasks in the list yet!");
        } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(Parser.getItem(inputStr))) {
            throw new DukeException("Oops, there is no task " + Integer.parseInt(Parser.getItem(inputStr)) + "!");
        } else {
            return true;
        }
    }

    /**
     * Checks if the find command entered is in the correct format with the correct description
     *
     * @param inputStr the input string the user entered containing the command and the description
     * @return returns true if the find command is in the correct format, throws exception otherwise
     * @throws DukeException if there is keyword entered after command
     */
    private static boolean isValidFindCommand(String inputStr) throws DukeException {
        if (Parser.isEmptyItem(inputStr)) {
            throw new DukeException("Oops, did you forget to enter a keyword?");
        } else {
            return true;
        }
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
            Ui.printFarewellMessage();
            canRunDuke = false;
            break;
        case COMMAND_LIST:
            taskManager.printTaskList();
            break;
        case COMMAND_DONE:
            if (isValidDoneCommand(inputStr, taskManager)) {
                taskManager.markTaskAsDone(inputStr);
            }
            break;
        case COMMAND_TODO:
            if (isValidToDoCommand(inputStr)) {
                String item = Parser.getItem(inputStr);
                taskManager.addToDoTaskToList(item);
            }
            break;
        case COMMAND_DEADLINE:
            if (isValidDeadlineCommand(inputStr)) {
                String item = Parser.getItem(inputStr);
                taskManager.addDeadlineTaskToList(item);
            }
            break;
        case COMMAND_EVENT:
            if (isValidEventCommand(inputStr)) {
                String item = Parser.getItem(inputStr);
                taskManager.addEventTaskToList(item);
            }
            break;
        case COMMAND_DELETE:
            if (isValidDeleteCommand(inputStr, taskManager)) {
                taskManager.deleteTask(inputStr);
            }
            break;
        case COMMAND_FIND:
            if (isValidFindCommand(inputStr)) {
                String keyword = Parser.getItem(inputStr);
                printArrayList(taskManager.findTask(keyword));
            }
            break;
        default:
            throw new DukeException("Oops, command not recognised!");
        }
    }

    /**
     * Prints the arrayList using streams
     *
     * @param arrayList the arrayList to be printed
     */
    public static void printArrayList(ArrayList<Task> arrayList) {
        System.out.println(LINE_SEPARATOR);
        arrayList.stream()
                .forEach(System.out::println);
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();

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
            String command = Parser.getCommand(inputStr);
            try {
                executeCommand(inputStr, command, taskManager, fileManager);
            } catch (DukeException dukeException) {
                System.out.println(LINE_SEPARATOR);
                System.out.println(dukeException.getMessage());
                System.out.println(LINE_SEPARATOR);
            } catch (NumberFormatException numberFormatException) {
            System.out.println(LINE_SEPARATOR);
            System.out.println("Oops, please enter the task number!");
            System.out.println(LINE_SEPARATOR);
            }
        }
    }

}
