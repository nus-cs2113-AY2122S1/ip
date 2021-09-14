package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Calls the printTasks method in taskManager to list out its Tasks
     *
     * @param taskList the TaskManager that will be read
     * @param storage  not used in this subclass implementation
     * @param ui       the ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String outputMessage = taskList.getMessageString();
        ui.printOutput(outputMessage);
    }
}
