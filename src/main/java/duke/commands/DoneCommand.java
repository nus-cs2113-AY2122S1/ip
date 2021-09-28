package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    public static final int END_OF_DONE_INDEX = 4;

    public DoneCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleDone(tasks, ui, storage);
        } catch (NumberFormatException e) {
            ui.showMissingDoneIndexError();
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskIndexError();
        }
    }

    private void handleDone(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Integer.parseInt(fullCommand.substring(END_OF_DONE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        if (tasks.getTask(taskIndex).isDone()) {
            ui.showAlreadyDone();
        } else {
            tasks.getTask(taskIndex).markAsDone();
            ui.showDone(taskIndex, tasks);
            storage.writeDone(taskIndex);
        }
    }
}
