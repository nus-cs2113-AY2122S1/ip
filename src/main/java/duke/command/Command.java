package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.io.IOException;

public abstract class Command {

    protected final Ui ui = new Ui();
    protected final Storage storage = new Storage();

    public Command() {
    }

    /**
     * Save task list to file.
     *
     * @param taskList Current task list to be saved.
     */
    protected void saveTaskList(TaskList taskList) {
        try {
            storage.saveTaskList(taskList);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        } catch (IOException e) {
            ui.printTaskFileSavingError();
        }
    }

    abstract public void execute(TaskList taskList);
}
