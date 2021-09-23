package duke.command;

import duke.data.TaskList;
import duke.startup.Ui;

/**
 * Command to echo user input.
 *  * A <code>Echo</code> command can be called with the prefix 'echo' in Duke.
 */
public class EchoCommand extends Command{
    public EchoCommand() {
        super(CommandPrefix.ECHO);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("echoing!");
    }

    public static String readInputEchoCommand() {
        String command = Ui.readCommand();
        System.out.println("    " + command);
        return command;
    }

    @Override
    public void execute(TaskList tasks) {
        readInputEchoCommand();
        saveListAndPrintDone(tasks);
    }
}
