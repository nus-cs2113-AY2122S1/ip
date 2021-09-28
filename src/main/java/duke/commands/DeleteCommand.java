package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.io.FileNotFoundException;

public class DeleteCommand extends Command {
    public static final int END_OF_DELETE_INDEX = 6;

    public DeleteCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = handleDeleteTask(tasks, ui, storage);
            ui.showDeleteConfirmation(deletedTask, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidDeleteIndexError();
        } catch (NumberFormatException e) {
            ui.showMissingDeleteIndexError();
        }
    }

    private Task handleDeleteTask(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Integer.parseInt(fullCommand.substring(END_OF_DELETE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.getTasks().remove(taskIndex);
        try {
            storage.deleteTaskFromMem(taskIndex);
        } catch (FileNotFoundException e) {
            ui.showDeleteError();
        }
        return deletedTask;
    }

}
