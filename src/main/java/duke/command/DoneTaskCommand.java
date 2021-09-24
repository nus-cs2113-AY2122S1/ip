package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndex;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

import java.io.IOException;

public class DoneTaskCommand extends Command {

    private final int currentTaskIndex;

    public DoneTaskCommand(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskDone(this.currentTaskIndex);
            ui.printMarked(tasks.getTaskDetails(this.currentTaskIndex));
            storage.writeData(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex();
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}