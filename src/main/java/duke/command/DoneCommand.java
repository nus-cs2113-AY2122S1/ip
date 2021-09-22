package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class DoneCommand extends Command{
    public DoneCommand() {
        super(CommandPrefix.done);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("marking as done!");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.markTasksAsDone();
        saveListAndPrintDone(tasks);
    }
}
