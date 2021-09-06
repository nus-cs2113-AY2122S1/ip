import java.util.Scanner;

public class Duke {

    private static final String COMMAND_LIST_TASK = "list";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_FINISH_TASK = "done";
    private static final String COMMAND_EXIT = "bye";

    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_TASK_INFO = 1;

    public static void greetStart(DisplayManager displayManager) {
        displayManager.printStartGreet();
    }

    public static void greetEnd(DisplayManager displayManager) {
        displayManager.printEndGreet();
    }

    public static void processReply(TaskManager taskManager, String line) throws DukeException{
        String[] inputs = line.split(" ", 2);
        String taskInfo;
        String command = inputs[INDEX_COMMAND];

        switch (command) {
        case COMMAND_LIST_TASK:
            taskManager.getAndPrintTaskList();
            break;
        case COMMAND_ADD_TODO:
            taskInfo = inputs[INDEX_TASK_INFO];
            taskManager.addToDoTask(taskInfo);
            break;
        case COMMAND_ADD_DEADLINE:
            taskInfo = inputs[INDEX_TASK_INFO];
            taskManager.addDeadlineTask(taskInfo);
            break;
        case COMMAND_ADD_EVENT:
            taskInfo = inputs[INDEX_TASK_INFO];
            taskManager.addEventTask(taskInfo);
            break;
        case COMMAND_FINISH_TASK:
            taskInfo = inputs[INDEX_TASK_INFO];
            taskManager.setAsDone(taskInfo);
            break;
        default:
            throw new DukeException();
        }
    }

    public static void reply(Scanner in, TaskManager taskManager) {
        String line;

        line = in.nextLine();
        while (!line.equals(COMMAND_EXIT)) {
            try {
                processReply(taskManager, line);
            } catch (IndexOutOfBoundsException e) {
                DisplayManager.printIndexOutOfBoundsError();
            } catch (DukeException e) {
                DisplayManager.printDukeExceptionError();
            }
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        DisplayManager displayManager = new DisplayManager();

        greetStart(displayManager);
        reply(in, taskManager);
        greetEnd(displayManager);
    }
}
