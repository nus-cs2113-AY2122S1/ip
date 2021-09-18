package duke;

import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.io.IOException;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String by;
    
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Deadline toAdd = new Deadline(description,by,false);
        tasks.addTask(toAdd);
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
}
