package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;

import java.io.IOException;

public class DeleteCommand extends Command {
    protected int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {
        try {
            String deletedTask = tasks.getList().get(indexToDelete).toString();
            tasks.getList().remove(indexToDelete);
            System.out.println("____________________________________________________________\n"
                    + "Noted. I've removed this task: \n"
                    + deletedTask
                    + System.lineSeparator()
                    + "Now you have " + tasks.getList().size() + " tasks in the list.\n"
                    + "____________________________________________________________\n"
            );

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Choose a task to delete!\n");

        } catch (NumberFormatException e) {
            System.out.println("Invalid task number. Please choose a valid task number to delete!\n");
        }
        storage.updateData(tasks, storage.dukeData.getPath());
    }
}
