package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class ClearCommand extends Command{
    public ClearCommand() {
        super(CommandPrefix.clear);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("clearing list!");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTaskList();
        saveListAndPrintDone(tasks);
    }
}
