package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class ByeCommand extends Command{
    public ByeCommand() {
        super(CommandPrefix.bye);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("... and bye!");
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        Ui.sayGoodbye();
        saveListAndPrintDone(tasks);
    }
}
