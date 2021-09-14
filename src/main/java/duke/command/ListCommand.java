package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Lists out the Tasks in taskList to the terminal
     *
     * @param taskList the taskList that will be read
     * @param storage  not used in this subclass implementation
     * @param ui       the ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String outputMessage = taskList.getMessageString();
        ui.printOutput(outputMessage);
    }
}
