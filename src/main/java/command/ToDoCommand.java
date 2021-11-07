package command;

import storage.Storage;
import task.TaskList;
import task.ToDo;

/**
 * Represents todo command given by user to
 * as task without time.
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds todo task to the list.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        tasksList.addToList(new ToDo(description));
    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return false To continue taking user commands
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
