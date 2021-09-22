package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class ListCommand extends Command{
    public ListCommand() {
        super(CommandPrefix.list);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("listing tasks!");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
        saveListAndPrintDone(tasks);
    }
}
