package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private static final CommandType type = CommandType.DELETE;
    private final int taskNo;

    /**
     * Delete command constructor
     *
     * @param taskNo Task number of task stored in task list to be deleted
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes task in task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        Task task = TaskList.deleteTask(taskNo);
        if (printMessage) {
            Ui.printTaskDeletedMessage(task);
        }
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}