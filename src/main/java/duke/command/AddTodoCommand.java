package duke.command;

import duke.exception.DukeException;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.ToDo;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private final String todoName;

    public AddTodoCommand(String todoName) {
        this.todoName = todoName;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(new ToDo(this.todoName));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}
