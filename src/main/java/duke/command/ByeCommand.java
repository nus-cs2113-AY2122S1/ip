package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Command to wave goodbye to user.
 *  A <code>Bye</code> command can be called with the prefix 'bye' in Duke.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandPrefix.BYE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("... and bye!");
    }

    @Override
    public void execute(TaskList tasks) {
        Ui.sayGoodbye();
        saveListAndPrintDone(tasks);
    }
}
