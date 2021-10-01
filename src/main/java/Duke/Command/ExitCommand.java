package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.Tasks.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": exit the programme.\n"
            + " Example: " + COMMAND_WORD;

    @Override
    /**
     * Execute the exit command by showing the closing messagee
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    /**
     * Set the flag isExit to true to terminate the program
     *
     * @return boolean Flag IsExit set to be true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
