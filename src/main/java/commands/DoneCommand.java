package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Error.ERROR_FORMAT_DONE;
import static common.Error.ERROR_INVALID_TASK;
import static common.Message.DONE_TASK;

/**
 * Represents the /done command
 * This command marks a specified task as complete
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "/done";
    protected String args;
    protected int taskID;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public DoneCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied user after COMMAND_WORD into taskID.
     * Calls method from tasks container to mark specified task as done,
     * writes changes into data.txt, displaying success messages when complete.
     * Error messages also output if command is malformed or invalid taskID had
     * been specified by user.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            String[] argList = args.split(" ");
            if (argList.length == 2) {
                taskID = Integer.parseInt(argList[1]);
                tasks.doneTask(taskID);

                ui.showMessage(String.format(DONE_TASK, tasks.getTaskInfo(taskID - 1)));
                data.write(tasks.getTaskList());
            } else {
                ui.showMessage(ERROR_INVALID_TASK);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_DONE);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(ERROR_INVALID_TASK);
        } catch (NumberFormatException e) {
            ui.showMessage(ERROR_INVALID_TASK);
        }
    }
}
