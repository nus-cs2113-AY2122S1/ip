package duke.manager.command;

import duke.logic.UserData;
import duke.manager.task.InvalidTaskNumberException;
import duke.manager.task.TaskManager;

import java.io.FileNotFoundException;

public class CommandManager {

    private boolean isExit = false;
    private TaskManager taskManager;

    public CommandManager() {
        taskManager = new TaskManager();
        try {
            taskManager.preloadTasks();
        } catch (FileNotFoundException fne) {
            System.out.println("Data file not found!");
        }
    }

    public boolean isExit () {
        return isExit;
    }

    public void executeCommand (Command inputCommand, String commandArguments) {
        String[] argument;
        switch (inputCommand) {
        case EXIT:
            UserData.saveData(taskManager.saveTasksAsString());
            isExit = true;
            break;
        case SHOW_LIST:
            taskManager.printTasks();
            break;
        case ADD_TODO:
            try {
                // commandArguments is the description for ToDos
                taskManager.checkInputThenAddToDo(commandArguments);
                UserData.saveData(taskManager.saveTasksAsString());
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("todo");
            }
            break;
        case ADD_EVENT:
            argument = commandArguments.split("/at", 2);
            try {
                taskManager.checkInputThenAddEvent(argument);
                UserData.saveData(taskManager.saveTasksAsString());
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("event");
            }
            break;
        case ADD_DEADLINE:
            argument = commandArguments.split("/by", 2);
            try {
                taskManager.checkInputThenAddDeadline(argument);
                UserData.saveData(taskManager.saveTasksAsString());
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("deadline");
            }
            break;
        case DELETE_TASK:
            try {
                taskManager.deleteTask(commandArguments);
                UserData.saveData(taskManager.saveTasksAsString());
            } catch (InvalidTaskNumberException ite) {
                taskManager.printMessageForTaskNumberOutOfRange();
            } catch (NumberFormatException nfe) {
                taskManager.printMessageForTaskNumberNonInteger();
            }
            break;
        case DONE_TASK:
            try {
                taskManager.markTaskAsDone(commandArguments);
                UserData.saveData(taskManager.saveTasksAsString());
            } catch (InvalidTaskNumberException ite) {
                taskManager.printMessageForTaskNumberOutOfRange();
            } catch (NumberFormatException nfe) {
                taskManager.printMessageForTaskNumberNonInteger();
            }
            break;
        case INVALID:
        default:
            taskManager.printMessageForInvalidInput();
            break;
        }
    }
}
