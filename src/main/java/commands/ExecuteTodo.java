package commands;

import storage.Storage;
import task.type.TaskList;
import task.type.Todo;

public class ExecuteTodo extends Command {
    private String description;

    public ExecuteTodo(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage) {
        tasksList.addTaskToList(new Todo(description));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}