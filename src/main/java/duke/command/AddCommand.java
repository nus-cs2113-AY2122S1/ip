package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public class AddCommand extends Command {

    private Task newTask;

    /**
     * Adds a task to the task list
     * @param ui UI to be used
     * @param taskList TaskList to be used
     * @param task task to be added
     */
    public AddCommand(Ui ui, TaskList taskList, Task task) {
        super(ui, taskList);
        this.newTask = task;
    }

    /**
     * Executes AddCommand
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        tasks.add(newTask);
        ui.printWithLines("added: " + newTask.toString());

    }
}
