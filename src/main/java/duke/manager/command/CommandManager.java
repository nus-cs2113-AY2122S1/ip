package duke.manager.command;

import duke.message.Message;
import duke.storage.UserData;
import duke.manager.task.InvalidTaskNumberException;
import duke.manager.task.TaskManager;
import duke.ui.UserInterface;

import java.io.FileNotFoundException;

public class CommandManager {

    private boolean isExit = false;
    private TaskManager taskManager;
    private static final String TASK_TYPE_TODO = "ToDo";
    private static final String TASK_TYPE_EVENT = "Event";
    private static final String TASK_TYPE_DEADLINE = "Deadline";

    public CommandManager(TaskManager taskManager) {
        this.taskManager = taskManager;
        try {
            this.taskManager.preloadTasks();
        } catch (FileNotFoundException fne) {
            System.out.println(Message.DATA_FILE_NOT_FOUND);
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public void executeCommand(Command inputCommand, String commandArguments) {
        switch (inputCommand) {
        case EXIT:
            UserData.saveData(taskManager.saveTasksAsString());
            isExit = true;
            break;
        case SHOW_LIST:
            executeShowTaskList();
            break;
        case ADD_TODO:
            executeAddToDo(commandArguments);
            break;
        case ADD_EVENT:
            executeAddEvent(commandArguments.split("/at", 2));
            break;
        case ADD_DEADLINE:
            executeAddDeadline(commandArguments.split("/by", 2));
            break;
        case DELETE_TASK:
            executeDeleteTask(commandArguments);
            break;
        case DONE_TASK:
            executeDoneTask(commandArguments);
            break;
        case INVALID:
        default:
            printMessageForInvalidCommand();
            break;

        }
    }

    private void executeShowTaskList() {
        taskManager.printTasks();
    }

    private void executeDoneTask(String commandArguments) {
        try {
            taskManager.markTaskAsDone(commandArguments);
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (InvalidTaskNumberException ite) {
            UserInterface.printMessage(
                    Message.TASK_NUMBER_OUT_OF_RANGE_MESSAGE
            );
        } catch (NumberFormatException nfe) {
            UserInterface.printMessage(
                    Message.TASK_NUMBER_WRONG_FORMAT_MESSAGE
            );
        }
    }

    private void executeDeleteTask(String commandArguments) {
        try {
            taskManager.deleteTask(commandArguments);
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (InvalidTaskNumberException ite) {
            UserInterface.printMessage(
                    Message.TASK_NUMBER_OUT_OF_RANGE_MESSAGE
            );
        } catch (NumberFormatException nfe) {
            UserInterface.printMessage(
                    Message.TASK_NUMBER_WRONG_FORMAT_MESSAGE
            );
        }
    }

    private void executeAddToDo(String commandArguments) {
        try {
            // commandArguments is the description for ToDos
            taskManager.checkInputThenAddToDo(commandArguments);
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (MissingCommandArgumentException mae) {
            UserInterface.printMessage(
                    Message.getMessageForMissingTaskDescription(TASK_TYPE_TODO)
            );
        }
    }

    private void executeAddEvent(String[] argument) {
        try {
            taskManager.checkInputThenAddEvent(argument);
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (MissingCommandArgumentException mae) {
            UserInterface.printMessage(
                    Message.getMessageForMissingTaskDescription(TASK_TYPE_EVENT)
            );
        }
    }

    private void executeAddDeadline(String[] argument) {
        try {
            taskManager.checkInputThenAddDeadline(argument);
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (MissingCommandArgumentException mae) {
            UserInterface.printMessage(
                    Message.getMessageForMissingTaskDescription(TASK_TYPE_DEADLINE)
            );
        }
    }

    private void printMessageForInvalidCommand() {
        UserInterface.printMessage(
                Message.INVALID_INPUT_MESSAGE
        );
    }
}
