package IzzIbot.command;

import IzzIbot.Ui;
import IzzIbot.TaskList;
import IzzIbot.tasks.Task;
import IzzIbot.exceptions.IzzIbotException;

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
     * @throws IzzIbotException
     */
    @Override
    public void execute() throws IzzIbotException {
        tasks.add(newTask);
        ui.printWithLines("added: " + newTask.toString());

    }
}
