package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;

import java.io.IOException;

public class DoneCommand extends Command {
    protected int doneIndex;

    public DoneCommand(int indexToDelete) {
        this.doneIndex = indexToDelete;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {
        try {
            tasks.getList().get(doneIndex).markAsDone();

            System.out.println("____________________________________________________________\n"
                    + "Well done! I've marked this task as done: \n"
                    + tasks.getList().get(doneIndex)
                    + System.lineSeparator()
                    + "____________________________________________________________\n"
            );
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number. Please mark a valid task number as done!\n");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please mark a task number as done!\n");
        }
        storage.updateData(tasks, storage.dukeData.getPath());
    }
}
