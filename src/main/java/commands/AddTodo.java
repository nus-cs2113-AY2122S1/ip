package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Todo;

public class AddTodo extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public AddTodo(String input) {
        this.input = input;
    }

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
