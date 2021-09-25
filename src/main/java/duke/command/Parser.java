package duke.command;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Parser {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    public static final String DELETE = "delete";
    public static final String EXIT = "bye";
    static int taskNumber;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

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
            if (isValidNumber(taskDescription)) {
                TaskList.markTaskComplete(taskNumber);
            }
            break;
        case DELETE:
            if (isValidNumber(taskDescription)) {
                TaskList.deleteTask(taskNumber);
            }
            break;
        case BEFORE:
            TaskList.beforeDate(taskDescription);
            break;
        case AFTER:
            TaskList.afterDate(taskDescription);
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

    static boolean isValidNumber(String taskDescription) {
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