package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    String commandArgs;

    public DeleteCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        int taskIndex = Integer.parseInt(commandArgs.strip()) - 1;
        ui.printConfirmDelete(tasks.getTaskByIndex(taskIndex), tasks.getNumberOfTasks());
        tasks.removeTaskByIndex(taskIndex);
        storage.saveDataToFile(tasks);
    }
}
