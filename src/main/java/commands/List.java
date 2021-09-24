package commands;

import tasklist.TaskList;
import ui.Ui;

public class List extends Command {
    protected Ui ui = new Ui();

    @Override
    public void execute(TaskList tasks) {
        if (tasks.getListSize() == 0) {
            ui.showEmptyListMessage();
        } else {
            ui.showLines();
            for (int i = 1; i <= tasks.getListSize(); i++) {
                System.out.println(i + "." + tasks.getTaskFromList(i - 1));
            }
            ui.showLines();
        }
    }
}
