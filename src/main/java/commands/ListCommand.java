package commands;

import processors.TaskList;
import processors.Ui;

public class ListCommand extends Command {
    public Ui ui = new Ui();

    public void execute(TaskList taskList) {
        ui.printListMessage(taskList.taskList);
    }
}
