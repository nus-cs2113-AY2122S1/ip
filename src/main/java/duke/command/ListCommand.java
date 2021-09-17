package duke.command;

import duke.Ui;
import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    public void execute() {
        ui.printWithLines(tasks.listTasks());
    }
}
