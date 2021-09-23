package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printFound();
        ArrayList<Task> tasksFound = taskList.findTask(toFind);

        int size = tasksFound.size();

        for (int i = 0; i < size; i++) {
            ui.printToUser("    ", Integer.toString(i + 1), ".", tasksFound.get(i).toString());
        }
    }
}
