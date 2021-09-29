package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Tasks.Todo;

import java.io.IOException;

/**
 * Command representing the addition of a Todo task
 */
public class TodoCommand extends Command {
    protected String line;

    public TodoCommand(String line) {
        this.line = line;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException {
        tasks.addNewTodo(this.line);
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
