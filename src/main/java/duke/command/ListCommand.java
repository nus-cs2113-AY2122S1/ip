package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;

public class ListCommand extends Command {

    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws DukeException {
        ui.printList(list, ui);
    }
}
