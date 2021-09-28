import duke.Error.DukeException;
import duke.Ui.DisplayManager;
import duke.Storage.FileManager;
import duke.TaskList.TaskManager;
import duke.Ui.Parser;

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

    public static void processReply(TaskManager taskManager, Parser parser, String line) throws DukeException {
        String taskInfo;
        String command = parser.extractCommand(line);

        switch (command) {
        case COMMAND_LIST_TASK:
            taskManager.getAndPrintTaskList();
            break;
        case COMMAND_ADD_TODO:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addToDoTask(taskInfo);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addDeadlineTask(parser, taskInfo);
            break;
        case COMMAND_ADD_EVENT:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.addEventTask(parser, taskInfo);
            break;
        case COMMAND_FINISH_TASK:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.setAsDone(taskInfo);
            break;
        case COMMAND_DELETE_TASK:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + command + " cannot be empty.");
            }
            taskManager.deleteTask(taskInfo);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void reply(Scanner in, TaskManager taskManager, Parser parser) {
        String line;

        line = in.nextLine();
        while (!line.equals(COMMAND_EXIT)) {
            try {
                processReply(taskManager, parser, line);
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
        Parser parser = new Parser();
        DisplayManager displayManager = new DisplayManager();

        greetStart(displayManager);
        loadDataFile(taskManager);
        reply(in, taskManager, parser);
        greetEnd(displayManager);
    }
}
