package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute() {
        Ui.printWithLine(LIST_MESSAGE + taskList.getList());
    }
}
