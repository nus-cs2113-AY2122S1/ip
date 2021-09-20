package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        Event toAdd = getEvent(description, at);
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
    
    private Event getEvent(String description, String at) {
        try {
            LocalDateTime atDT = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Event(description,atDT,false);
        } catch (DateTimeParseException dtpe) {
            return new Event(description,at,false);
        }
    }
    
}
