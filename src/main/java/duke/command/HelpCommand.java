package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    /**
     * Prints out the help message detailing possible user commands
     *
     * @param taskList not used in this subclass implementation
     * @param storage  not used in this subclass implementation
     * @param ui       the ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String outputMessage = Output.getHelpMessage();
        ui.printOutput(outputMessage);
    }
}
