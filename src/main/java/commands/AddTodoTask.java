package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Todo;

public class AddTodoTask extends Command {
    protected Ui ui = new Ui();
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public AddTodoTask(String input) {
        this.input = input;
    }

    /**
     * Adds a todo task into the task list.
     * If there is no input after the command word, the user will be prompted to include one in the correct format.
     *
     * @param tasks task list to be updated when a todo task is added.
     */
    @Override
    public void execute(TaskList tasks) {
        if (input.trim().length() <= 4) {
            ui.showMissingTaskDescriptionMessage();
        } else {
            tasks.addTaskToList(new Todo(input.trim().substring(5)));
            int taskIndex = tasks.getListSize() - 1;
            ui.showTaskAddedMessage(tasks.getTaskFromList(taskIndex), tasks.getListSize());
            int indexOfAddedTask = tasks.getListSize() - 1;
            Storage.appendDatabase("T", tasks.getTaskFromList(indexOfAddedTask).getDescription(), "0");
        }
    }
}
