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
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter the task to be marked as done?");
            } else if (taskManager.getNumberOfTasksUndone() == 0) {
                throw new DukeException("Oops, there are no tasks to be marked done!");
            } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(Parser.getItem(inputStr))) {
                throw new DukeException("Oops, there is no task " + Integer.parseInt(Parser.getItem(inputStr)) + "!");
            } else if (taskManager.isTaskDone(inputStr)) {
                throw new DukeException("Oops, this task is already marked as done!");
            } else {
                taskManager.markTaskAsDone(inputStr);
                break;
            }
        case COMMAND_TODO:
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of a ToDo cannot be empty!");
            }
            String item = Parser.getItem(inputStr);
            taskManager.addToDoTaskToList(item);
            break;
        case COMMAND_DEADLINE:
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of a deadline cannot be empty!");
            } else if (Parser.isInvalidDeadline(inputStr)) {
                throw new DukeException("Oops, the time of a deadline cannot be empty!");
            }
            item = Parser.getItem(inputStr);
            taskManager.addDeadlineTaskToList(item);
            break;
        case COMMAND_EVENT:
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, the description of an event cannot be empty!");
            } else if (Parser.isInvalidEvent(inputStr)) {
                throw new DukeException("Oops, the time of an event cannot be empty!");
            }
            item = Parser.getItem(inputStr);
            taskManager.addEventTaskToList(item);
            break;
        case COMMAND_DELETE:
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter the task to be deleted?");
            } else if (taskManager.getNumberOfTasksAdded() == 0) {
                throw new DukeException("Oops, there are no tasks in the list yet!");
            } else if (taskManager.getNumberOfTasksAdded() < Integer.parseInt(Parser.getItem(inputStr))) {
                throw new DukeException("Oops, there is no task " + Integer.parseInt(Parser.getItem(inputStr)) + "!");
            } else {
                taskManager.deleteTask(inputStr);
            }
            break;
        case COMMAND_FIND:
            if (Parser.isEmptyItem(inputStr)) {
                throw new DukeException("Oops, did you forget to enter a keyword?");
            }
            String keyword = Parser.getItem(inputStr);
            printArrayList(taskManager.findTask(keyword));
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
