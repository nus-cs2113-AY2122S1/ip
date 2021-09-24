package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndex;
import duke.exception.InvalidInput;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command {

    private final int selectedTaskIndex;

    public DeleteTaskCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.printDeleted(tasks.getTaskDetails(this.selectedTaskIndex));
            tasks.deleteTask(this.selectedTaskIndex);
            storage.writeData(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex();
        } catch (IOException e) {
            throw new UnsavedFile();
        }

    }
}