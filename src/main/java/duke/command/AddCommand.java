package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Ui ui, TaskList taskList, Task task) {
        super(ui, taskList);
        this.newTask = task;
    }

    @Override
    public void execute() throws DukeException {
        tasks.add(newTask);
        ui.printWithLines("added: " + newTask.toString());

    }
}
