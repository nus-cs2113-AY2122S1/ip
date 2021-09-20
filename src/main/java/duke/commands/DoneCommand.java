package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    private int doneIndex;

    public DoneCommand(String command, int doneIndex) {
        super(command);
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(doneIndex);
        if (task.isDone()){
            ui.showAlreadyDoneMessage();
        } else {
            ui.showDoneMessage(tasks.doneTask(doneIndex));
        }
        storage.store(tasks);
    }
}
