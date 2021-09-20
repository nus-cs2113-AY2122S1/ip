package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String word;

    public FindCommand(String Command, String word) {
        super(Command);
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList filteredTasks = tasks.getFilteredTask(word);
        ui.showFilteredTask(filteredTasks, word);
    }
}
