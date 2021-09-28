package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Tasks.Deadline;
import Tasks.Event;

import java.io.IOException;

public class EventCommand extends Command {
    protected String line;
    public EventCommand(String line) {
        this.line = line;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {
        tasks.addNewEvent(this.line);
        System.out.println("____________________________________________________________\n"
                + "Got it! I've added this task: "
                + System.lineSeparator()
                + "  "
                + tasks.getList().get(tasks.getList().size() - 1)
                + System.lineSeparator()
                + "Now you have "
                + tasks.getList().size()
                + " tasks left in the list."
                + System.lineSeparator()
                + "____________________________________________________________\n"
        );
        storage.updateData(tasks, storage.dukeData.getPath());
    }
}
