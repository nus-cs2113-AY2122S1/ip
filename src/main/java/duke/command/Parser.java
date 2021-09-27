package duke.command;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Reads user input
 */
public class Parser {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String EXIT = "bye";
    static int taskNumber;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    /**
     * Reads user input to decipher which task to carry out
     * Calls itself again at the end unless the BYE case is triggered.
     * For functions that require description, returns error if no description found
     *
     * @throws IOException If input information is invalid
     */
    static void chooseTask() throws IOException {
        String line, taskType, taskDescription = null;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        try {
            int typePos = line.indexOf(" ");
            taskType = line.substring(0, typePos);
            taskDescription = line.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = line;
        }

        switch (taskType) {
        case TODO:
            TaskList.addTask(TODO, taskDescription);
            break;
        case DEADLINE:
            TaskList.addTask(DEADLINE, taskDescription);
            break;
        case EVENT:
            TaskList.addTask(EVENT, taskDescription);
            break;
        case LIST:
            TaskList.displayList();
            break;
        case DONE:
            if (isWithoutDescription(taskDescription)) {
                break;
            }
            if (isValidNumber(taskDescription)) {
                TaskList.markTaskComplete(taskNumber);
            }
            break;
        case DELETE:
            if (isWithoutDescription(taskDescription)) {
                break;
            }
            if (isValidNumber(taskDescription)) {
                TaskList.deleteTask(taskNumber);
            }
            break;
        case BEFORE:
            if (isWithoutDescription(taskDescription)) {
                break;
            }
            TaskList.beforeDate(taskDescription);
            break;
        case AFTER:
            if (isWithoutDescription(taskDescription)) {
                break;
            }
            TaskList.afterDate(taskDescription);
            break;
        case FIND:
            if (isWithoutDescription(taskDescription)) {
                break;
            }
            TaskList.find(taskDescription);
            break;
        case EXIT:
            Ui.bye();
            return;
        default:
            Ui.printDividerLine();
            System.out.println("Invalid Input!");
            Ui.printDividerLine();
        }
        chooseTask();
    }

    /**
     * Checks if task description is empty, and prints out error code
     *
     * @return True if no description found
     */
    private static boolean isWithoutDescription(String taskDescription) {
        if (taskDescription == null) {
            Ui.printDividerLine();
            System.out.println("Invalid Input!");
            Ui.printDividerLine();
            return true;
        }
        return false;
    }

    /**
     * Checks if task description is a valid task number.
     * Performs addition check to verify the task number is within the range
     * of 0 and total number of tasks.
     *
     * @return True if number passes all checks
     */
    private static boolean isValidNumber(String taskDescription) {
        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > TaskList.numberOfTasks || taskNumber <= 0) {
                System.out.println("Error! This task does not exist!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }
        return true;
    }

    static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, inputFormatter);
    }

    public static String dateStringOutput(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }


}