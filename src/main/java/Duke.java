import duke.Error.DukeException;
import duke.TaskList.command.*;
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

    public static void greetStart(DisplayManager displayManager) {
        displayManager.printStartGreet();
    }

    public static void greetEnd(DisplayManager displayManager) {
        displayManager.printEndGreet();
    }

    public static void processReply(TaskManager taskManager, Parser parser, String line) throws DukeException {
        Command command = null;
        String taskInfo;
        String commandType = parser.extractCommand(line);

        switch (commandType) {
        case COMMAND_LIST_TASK:
            taskManager.getAndPrintTaskList();
            break;
        case COMMAND_ADD_TODO:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new ToDoCommand(taskManager, taskInfo);
            //taskManager.addToDoTask(taskInfo);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new DeadlineCommand(taskManager, parser, taskInfo);
            //taskManager.addDeadlineTask(parser, taskInfo);
            break;
        case COMMAND_ADD_EVENT:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new EventCommand(taskManager, parser, taskInfo);
            //taskManager.addEventTask(parser, taskInfo);
            break;
        case COMMAND_FINISH_TASK:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new SetDoneCommand(taskManager, taskInfo);
            //taskManager.setAsDone(taskInfo);
            break;
        case COMMAND_DELETE_TASK:
            try {
                taskInfo = parser.extractTaskInfo(line);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new DeleteCommand(taskManager, taskInfo);
            //taskManager.deleteTask(taskInfo);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        if (command != null) {
            command.execute();
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

    public static void loadDataFile(TaskManager taskManager, Parser parser) {
        FileManager.loadData(taskManager, parser);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Parser parser = new Parser();
        DisplayManager displayManager = new DisplayManager();

        greetStart(displayManager);
        loadDataFile(taskManager, parser);
        reply(in, taskManager, parser);
        greetEnd(displayManager);
    }
}
