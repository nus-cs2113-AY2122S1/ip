package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Error.ERROR_FORMAT_DONE;
import static common.Error.ERROR_INVALID_TASK;
import static common.Message.DONE_TASK;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "/done";
    protected String args;
    protected int taskID;

    public DoneCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            taskID = Integer.parseInt(args.split(" ")[1]);
            tasks.doneTask(taskID);

            ui.showMessage(String.format(DONE_TASK, tasks.getTaskInfo(taskID - 1)));
            data.write(tasks.getTaskList());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_DONE);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(ERROR_INVALID_TASK);
        }
    }
}
