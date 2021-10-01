package duke.manager;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

public class TaskManager {

    public static final String TASK_TODO = "todo";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_ERROR = "I really cannot understand what you wrote";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";


    /**
     * Adds the task inputted by the user. The function
     * converts the input string message to a Task object
     * according to instructions provided in the string
     *
     * @param tasks   array of tasks
     * @param message input string containing the task
     */
    public static void addTask(ArrayList<Task> tasks, String message) {
        switch (Parser.taskType(message)) {
        case TASK_TODO:
            Parser.addTodo(tasks, message);
            break;
        case TASK_DEADLINE:
            Parser.addDeadline(tasks, message);
            break;
        case TASK_EVENT:
            Parser.addEvent(tasks, message);
            break;
        default:
            DukeException.taskError();
            break;
        }
    }

    /**
     * Process the inputs given by the user
     * to do the required task. The function
     * keeps reading every line until bye is
     * written by the user
     */
    public static void processInput() {

        ArrayList<Task> tasks = new ArrayList<>();
        Storage.getStoredData(tasks);
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (isNotCommandBye(message)) {
            processMessage(tasks, message);
            message = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Process the input given by the user
     * and calls the necessary functions to
     * add, print, delete and find tasks.
     */
    public static void processMessage(ArrayList<Task> tasks, String message) {
        if (isCommandList(message)) {
            Ui.printTasks(tasks);
        } else if (isCommandFind(message)) {
            Parser.findTask(tasks, message);
        } else if (isCommandDone(message)) {
            Parser.markDone(tasks, message);
        } else if (isCommandDelete(message)) {
            Parser.deleteTask(tasks, message);
        } else {
            addTask(tasks, message);
        }
    }

    public static boolean isCommandDelete(String message) {
        return message.contains(COMMAND_DELETE);
    }

    public static boolean isCommandDone(String message) {
        return message.contains(COMMAND_DONE);
    }

    public static boolean isCommandFind(String message) {
        return message.contains(COMMAND_FIND);
    }

    public static boolean isCommandList(String message) {
        return message.equals(COMMAND_LIST);
    }

    public static boolean isNotCommandBye(String message) {
        return !message.equals(COMMAND_BYE);
    }

}
