package duke.command;

import duke.datasaver.DataManager;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnrecognizedCommand extends Command {

    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        Ui.printUnrecognizedCommandMessage();
    }
}
