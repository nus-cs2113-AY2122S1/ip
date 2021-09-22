package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class AddCommand extends Command{
    public AddCommand() {
        super(CommandPrefix.add);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("adding");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.addTasksToList();
        saveListAndPrintDone(tasks);
    }
}
