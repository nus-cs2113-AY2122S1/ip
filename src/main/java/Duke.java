import duke.Error.DukeException;
import duke.Ui.DisplayManager;
import duke.Storage.FileManager;
import duke.TaskList.task.TaskManager;

import java.util.Scanner;

public class Duke {

    private static final String COMMAND_LIST_TASK = "list";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_FINISH_TASK = "done";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_EXIT = "bye";

    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_TASK_INFO = 1;

    public static void greetStart(DisplayManager displayManager) {
        displayManager.printStartGreet();
    }

    public static void greetEnd(DisplayManager displayManager) {
        displayManager.printEndGreet();
    }

    public static void processReply(TaskManager taskManager, String line) throws DukeException {
        String[] inputs = line.split(" ", 2);
        String taskInfo;
        String command = inputs[INDEX_COMMAND];

        switch (command) {
        case COMMAND_LIST_TASK:
            taskManager.getAndPrintTaskList();
            break;
        case COMMAND_ADD_TODO:
            try {
                taskInfo = inputs[INDEX_TASK_INFO];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addToDoTask(taskInfo);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                taskInfo = inputs[INDEX_TASK_INFO];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addDeadlineTask(taskInfo);
            break;
        case COMMAND_ADD_EVENT:
            try {
                taskInfo = inputs[INDEX_TASK_INFO];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addEventTask(taskInfo);
            break;
        case COMMAND_FINISH_TASK:
            try {
                taskInfo = inputs[INDEX_TASK_INFO];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.setAsDone(taskInfo);
            break;
        case COMMAND_DELETE_TASK:
            try {
                taskInfo = inputs[INDEX_TASK_INFO];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.deleteTask(taskInfo);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void reply(Scanner in, TaskManager taskManager) {
        String line;

        line = in.nextLine();
        while (!line.equals(COMMAND_EXIT)) {
            try {
                processReply(taskManager, line);
            } catch (DukeException e) {
                DisplayManager.printHorizontalSeparator();
                System.out.println(e);
                DisplayManager.printHorizontalSeparator();
            }
            line = in.nextLine();
        }
    }

    public static void loadDataFile(TaskManager taskManager) {
        FileManager.loadData(taskManager);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        DisplayManager displayManager = new DisplayManager();

        greetStart(displayManager);
        loadDataFile(taskManager);
        reply(in, taskManager);
        greetEnd(displayManager);
    }
}
