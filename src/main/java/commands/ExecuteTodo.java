package commands;

import storage.Storage;
import task.type.TaskList;
import task.type.Todo;

public class ExecuteTodo extends Command {
    private String description;

    /**
     * Constructor
     *
     * @param description Task description
     */
    public ExecuteTodo(String description) {
        this.description = description;
    }

    /**
     * Adds events of type Todo to the list
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        tasksList.addTaskToList(new Todo(description));
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take input from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}