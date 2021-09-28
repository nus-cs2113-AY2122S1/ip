package commands;

import storage.Storage;
import inputvalidator.CheckId;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Delete command. Helps to do all operations of the delete command such as
 * removing the task from the list and ensures that the ID input is valid.
 */

public class DeleteCommand extends Command {
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n%1$s\nNow you have %2$o tasks"
            + " in the list.";
    public static final String COMMAND_SYNTAX = "Command Syntax: delete <task id>";

    String id;

    public DeleteCommand(String command, String id) {
        super(command);
        this.id = id;
    }

    @Override
    public String help() {
        return COMMAND_SYNTAX;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        if (!CheckId.isValidTaskId(id, tasks)) { // Invalid task id
            ui.customPrint(Task.TASK_DOES_NOT_EXIST + "\n" + help());
        } else {
            int taskId = Integer.parseInt(id) - 1;
            Task removedTask = tasks.get(taskId);
            tasks.remove(taskId);
            ui.customPrint(String.format(REMOVE_TASK_MESSAGE, removedTask, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
