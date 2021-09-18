package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Delete command. Helps to do all operations of the delete command such as
 * removing the task from the list and ensures that the ID input is valid.
 */

public class DeleteCommand extends Command {
    private static final String removeTaskMessage = "Noted. I've removed this task:\n%1$s\nNow you have %2$o tasks"
            + " in the list.";
    public static final String commandSyntax = "Command Syntax: delete <task id>";

    String id;

    public DeleteCommand(String command, String id) {
        super(command);
        this.id = id;
    }

    @Override
    public String help() {
        return commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        if (!Task.isValidTaskId(id, tasks)) { // Invalid task id
            ui.customPrint(Task.taskDoesNotExist + "\n" + help());
        } else {
            int taskId = Integer.parseInt(id) - 1;
            Task removedTask = tasks.get(taskId);
            tasks.remove(taskId);
            ui.customPrint(String.format(removeTaskMessage, removedTask, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
