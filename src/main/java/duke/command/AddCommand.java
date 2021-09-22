package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    /**
     * Adds the provided new task into the task list.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(newTask);
        ui.printAddNewTask(newTask, taskList.getTotalTasks());
        saveTaskList(taskList);
    }
}
