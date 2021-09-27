package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.task.*;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Print all tasks in the list
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     */
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui){
        ui.printList(list, ui);
    }
}
