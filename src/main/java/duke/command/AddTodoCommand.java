package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddTodoCommand extends AddTaskCommand{
    private String description;
    private String input;

    public AddTodoCommand(String description, String input) {
        this.description = description;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(description);
        ui.printAddTaskMessage(tasks);
        storage.storeTask(input);
    }

    @Override
    public void addTask(TaskList tasks) {
        tasks.addTodo(description);
    }
}
