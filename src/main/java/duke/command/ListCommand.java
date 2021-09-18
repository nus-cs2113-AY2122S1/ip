package duke.command;

import duke.Ui;
import duke.TaskList;

public class ListCommand extends Command {

    /**
     * Lists all the tasks in TaskList
     * @param ui UI to be used
     * @param tasks TaskList to be used
     */
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    /**
     * Executes ListCommand
     */
    public void execute() {
        ui.printWithLines(tasks.listTasks());
    }
}
