package duke.manager.command;

import duke.manager.task.InvalidTaskNumberException;
import duke.manager.task.TaskManager;

public class CommandManager {

    private boolean isExit = false;
    TaskManager taskManager;

    public CommandManager() {
        this.taskManager = new TaskManager();
    }

    public boolean isExit () {
        return isExit;
    }

    public void executeCommand (Command inputCommand, String commandArguments) {
        String[] argument;
        switch (inputCommand) {
        case EXIT:
            isExit = true;
            break;
        case SHOW_LIST:
                taskManager.printTasks();
            break;
        case ADD_TODO:
            try {
                taskManager.checkInputThenAddToDo(commandArguments); // commandArguments is the description for ToDos
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("todo");
            }
            break;
        case ADD_EVENT:
            argument = commandArguments.split("/at", 2);
            try {
                taskManager.checkInputThenAddEvent(argument);
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("event");
            }
            break;
        case ADD_DEADLINE:
            argument = commandArguments.split("/by", 2);
            try {
                taskManager.checkInputThenAddDeadline(argument);
            } catch (MissingCommandArgumentException mae) {
                taskManager.printMessageForMissingTaskDescription("deadline");
            }
            break;
        case DONE_TASK:
            try {
                taskManager.markTaskAsDone(commandArguments);
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
