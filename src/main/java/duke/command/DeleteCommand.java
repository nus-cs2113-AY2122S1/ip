package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class DeleteCommand extends Command{
    public DeleteCommand() {
        super(CommandPrefix.delete);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("deleting tasks!");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTasks();
        saveListAndPrintDone(tasks);
    }
}
