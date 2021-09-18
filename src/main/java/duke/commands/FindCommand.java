package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.task.Task;


public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = taskList.findTask(keyword);
        ui.showFoundTasks(foundTasks);
    }
}