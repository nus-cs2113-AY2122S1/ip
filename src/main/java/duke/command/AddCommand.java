package duke.command;

import duke.ui.Ui;
import duke.storage.TaskList;
import duke.task.Task;

public class AddCommand extends Command {

    private Task newTask;

    /**
     * A constructor to add new task into taskList.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     * @param newTask task to be added into task list.
     */
    public AddCommand(TaskList taskList, Ui ui, Task newTask) {
        super(taskList, ui);
        this.newTask = newTask;
    }

    /**
     * Executes the command.
     */
    @Override
    public void executed() {
        taskList.addTask(newTask);
        Ui.printWithLine("Alright! Added to the list:\n" + "  " + newTask +
                "\nYou currently have " + taskList.size() + " task recorded in your list.\n");
    }
}
