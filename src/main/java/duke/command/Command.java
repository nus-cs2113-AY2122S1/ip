package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {

    /**
     * A generic execute method which will change according to different command type
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Returns false to let isExit = false to continue the infinite loop of asking for user input
     *
     * @return false to continue accepting user input
     */
    public boolean isExit() {
        return false;
    }

}
