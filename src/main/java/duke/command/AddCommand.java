package duke.command;

import duke.TaskManager;
import duke.task.TaskType;

public class AddCommand extends Command {
    private String taskName;
    private String taskDate;
    private TaskType taskType;

    /**
     * Class add command constructor.
     *
     * @param taskName Name of task to be added.
     * @param taskDate TODO: Not applicable.
     *                 DEADLINE: Due date of the task to be added.
     *                 EVENT: Event date of the task to be added.
     * @param taskType Type of task to be added.
     */
    public AddCommand(String taskName, String taskDate, TaskType taskType) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskType = taskType;
    }

    /**
     * Runs a command to add task to the list.
     */
    @Override
    public void runCommand() {
        TaskManager.addToList(taskName, taskDate, taskType);
    }
}
