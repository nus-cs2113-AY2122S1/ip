package duke.command;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String EXIT = "bye";
    public static final String DELETE = "delete";

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
            TaskList.markTaskComplete(taskDescription);
            break;
        case EXIT:
            Ui.bye();
            return;
        case DELETE:
            TaskList.deleteTask(taskDescription);
            break;
        default:
            Ui.printDividerLine();
            System.out.println("Invalid Input!");
            Ui.printDividerLine();
        }
        chooseTask();
    }
}