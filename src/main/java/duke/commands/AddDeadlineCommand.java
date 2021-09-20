package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Deadline toAdd = getDeadline(description,by);//new Deadline(description, by, false);
        tasks.addTask(toAdd);
        storage.writeToData(toAdd, tasks.getNumberOfTasks());
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
    
    private Deadline getDeadline(String description, String by) {
        try {
            LocalDateTime byDT = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Deadline(description,byDT,false);
        } catch (DateTimeParseException dtpe) {
            return new Deadline(description,by,false);
        }
    }
}
