package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.io.IOException;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String at;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Event toAdd = new Event(description, at, false);
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
}
