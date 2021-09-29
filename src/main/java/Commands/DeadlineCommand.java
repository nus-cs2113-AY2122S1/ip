package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Tasks.Deadline;
import Tasks.Todo;

import java.io.IOException;

/**
 * Command representing the addition of a Deadline task
 */
public class DeadlineCommand extends Command {
    protected String line;
    public DeadlineCommand(String line) {
        this.line = line;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {
        tasks.addNewDeadline(this.line);
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
