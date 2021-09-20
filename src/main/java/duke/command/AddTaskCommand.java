package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public abstract class AddTaskCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    public abstract void addTask(TaskList tasks);
}
