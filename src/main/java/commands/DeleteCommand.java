package commands;

import data.Storage;
import data.TaskList;
import task.Task;
import ui.TextUI;

import static common.Error.ERROR_INVALID_TASK;
import static common.Message.DELETE_TASK;
import static common.Error.ERROR_FORMAT_DELETE;

/**
 * Represents the /delete command
 * This command removes an existing task
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "/delete";
    protected String args;
    protected int taskID;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied user after COMMAND_WORD into taskID.
     * Calls method from tasks container to remove specified task,
     * write changes into data.txt, displaying success messages when complete.
     * Error messages also output if command is malformed or invalid taskID had
     * been specified by user
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            taskID = Integer.parseInt(args.split(" ")[1]);
            Task deletedTask = tasks.deleteTask(taskID);

            ui.showMessage(String.format(DELETE_TASK, deletedTask, tasks.getSize()));
            data.write(tasks.getTaskList());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_DELETE);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(ERROR_INVALID_TASK);
        }
    }
}
