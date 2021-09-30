package commands;

import storage.Storage;
import inputvalidator.CheckId;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Done command. Helps to do all operations of the done command such as
 * marking a task as completed and ensuring that the ID input is valid.
 */

public class DoneCommand extends Command {
    private static final String TASK_ALREADY_COMPLETED = "You have already completed the task [%1$s]!";
    private static final String TASK_MARKED_DONE = "Nice! I've marked this task as done:\n%1$s";
    public static final String COMMAND_SYNTAX = "Command Syntax: done <task id>";
    public String id;

    public DoneCommand(String command, String id) {
        super(command);
        this.id = id;
    }

    @Override
    public String help() {
        return COMMAND_SYNTAX;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        if (!CheckId.isValidTaskId(id, tasks)) { // Task id is invalid
            ui.customPrint(Task.TASK_DOES_NOT_EXIST + "\n" + help());
        } else {
            int taskId = Integer.parseInt(id) - 1; // -1 as array index starts from 0

            // Checks if task has been completed
            Task currentTask = tasks.get(taskId);
            if (currentTask.isDone()) {
                ui.customPrint(String.format(TASK_ALREADY_COMPLETED, currentTask.getDescription()));
            } else {
                currentTask.markAsDone(); // Mark current task as done
                ui.customPrint(String.format(TASK_MARKED_DONE, currentTask));
                storage.saveData(ui, tasks);
            }
        }
    }
}
