package commands;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.TaskManager;

/**
 * Adds a new 'todo' type task to TaskManager.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    /**
     * Creates a new todo command and sets the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public ToDoCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Adds a new 'todo' task.
     *
     * @return The todo command type.
     */
    @Override
    public String executeCommand() {
        try {
            taskManager.addTodoTask(InputParser.getTaskDetails(commandComponents));
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
        return COMMAND_WORD;
    }
}
