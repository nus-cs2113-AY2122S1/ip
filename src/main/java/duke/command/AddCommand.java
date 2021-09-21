package duke.command;

import duke.ui.Ui;
import duke.storage.TaskList;
import duke.task.Task;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(TaskList taskList, Ui ui, Task newTask) {
        super(taskList, ui);
        this.newTask = newTask;
    }

    @Override
    public void execute() {
        taskList.addTask(newTask);
        Ui.printWithLine("Alright! Added to the list:\n" + "  " + newTask +
                "\nYou currently have " + taskList.size() + " task recorded in your list.\n");
    }
}
