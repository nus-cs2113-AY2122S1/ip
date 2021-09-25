package commands;

import data.Storage;
import data.TaskList;
import task.Task;
import ui.TextUI;

import static common.Error.ERROR_INVALID_TASK;
import static common.Message.DELETE_TASK;
import static common.Error.ERROR_FORMAT_DELETE;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "/delete";
    protected String args;
    protected int taskID;

    public DeleteCommand(String args) {
        this.args = args;
    }

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
