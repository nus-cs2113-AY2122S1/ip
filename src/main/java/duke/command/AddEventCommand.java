package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddEventCommand extends AddTaskCommand{
    private String description;
    private String at;
    private String input;

    public AddEventCommand(String description, String at, String input) {
        this.description = description;
        this.at = at;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(description, at);
        ui.printAddTaskMessage(tasks);
        storage.storeTask(input);
    }

    @Override
    public void addTask(TaskList tasks) {
        tasks.addEvent(description, at);
    }
}
