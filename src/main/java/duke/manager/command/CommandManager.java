package duke.manager.command;

import duke.message.Message;
import duke.storage.UserData;
import duke.manager.task.InvalidTaskNumberException;
import duke.manager.task.TaskManager;
import duke.ui.UserInterface;

import java.io.FileNotFoundException;

/**
 * <h1>CommandManager</h1>
 * A <code>CommandManager</code> object is in charge of managing and executing commands based off user inputs.
 * It executes commands and prints out relevant messages for the user depending on whether commands are valid and
 * whether they are executed successfully.
 */
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
            System.out.println(Message.DATA_FILE_NOT_FOUND_MESSAGE);
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
        case FIND_KEYWORD:
            executeFindKeyword(commandArguments);
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

    /**
     * Prints the task list for the user to look at.
     */
    private void executeShowTaskList() {
        taskManager.printTaskList();
    }

    /**
     * Attempts to find keyword and prints out the filtered task list of tasks containing
     * the keyword.
     *
     * @param keyword Word to be search for in task descriptions.
     */
    private void executeFindKeyword(String keyword) {
        taskManager.printFilteredTaskList(keyword);
    }

    /**
     * Attempts to mark a specific task as done and prints out a relevant message depending on the outcome.
     * If a task is successfully marked as done, updated task list is saved into the user data file.
     *
     * @param commandArguments Argument specified for DONE_TASK.
     */
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

    /**
     * Attempts to delete a specific task and prints out a relevant message depending on the outcome.
     * If a task is successfully deleted, updated task list is saved into the user data file.
     *
     * @param commandArguments Argument specified for DELETE_TASK.
     */
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

    /**
     * Attempts to add a todo into the task list and prints out a relevant message depending on the outcome.
     * If a todo is successfully added, updated task list is saved into the user data file.
     *
     * @param commandArguments Argument specified for ADD_TODO.
     */
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

    /**
     * Attempts to add an event into the task list and prints out a relevant message depending on the outcome.
     * If an event is successfully added, updated task list is saved into the user data file.
     *
     * @param argument Arguments specified for ADD_EVENT.
     */
    private void executeAddEvent(String[] argument) {
        try {
            taskManager.checkInputThenAddEvent(argument);
            // inform user that input is not in the proper date time format
            if (!taskManager.getLastTaskInList().isInDateTimeFormat()) {
                System.out.println(Message.NOT_DATE_TIME_MESSAGE + System.lineSeparator()
                        + UserInterface.HORIZONTAL_BAR);
            }
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (MissingCommandArgumentException mae) {
            UserInterface.printMessage(
                    Message.getMessageForMissingTaskDescription(TASK_TYPE_EVENT)
            );
        }
    }

    /**
     * Attempts to add a deadline into the task list and prints out a relevant message depending on the outcome.
     * If an deadline is successfully added, updated task list is saved into the user data file.
     *
     * @param argument Arguments specified for ADD_DEADLINE.
     */
    private void executeAddDeadline(String[] argument) {
        try {
            taskManager.checkInputThenAddDeadline(argument);
            // inform user that input is not in the proper date time format
            if (!taskManager.getLastTaskInList().isInDateTimeFormat()) {
                System.out.println(Message.NOT_DATE_TIME_MESSAGE + System.lineSeparator()
                        + UserInterface.HORIZONTAL_BAR);
            }
            UserData.saveData(taskManager.saveTasksAsString());
        } catch (MissingCommandArgumentException mae) {
            UserInterface.printMessage(
                    Message.getMessageForMissingTaskDescription(TASK_TYPE_DEADLINE)
            );
        }
    }

    /**
     * Prints relevant message if user input is an invalid command.
     */
    private void printMessageForInvalidCommand() {
        UserInterface.printMessage(
                Message.INVALID_INPUT_MESSAGE
        );
    }
}
