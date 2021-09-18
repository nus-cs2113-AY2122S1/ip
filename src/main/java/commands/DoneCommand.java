package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class DoneCommand extends Command {
    private static final String taskAlreadyCompleted = "You have already completed the task [%1$s]!";
    private static final String taskMarkedDone = "Nice! I've marked this task as done:\n%1$s";
    public static final String commandSyntax = "Command Syntax for Done: done <task id>";
    public String id;

    public DoneCommand(String command, String id) {
        super(command);
        this.id = id;
    }

    @Override
    public String help() {
        return commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        if (!Task.isValidTaskId(id, tasks)) { // Task id is invalid
            ui.customPrint(Task.taskDoesNotExist + "\n" + help());
        } else {
            int taskId = Integer.parseInt(id) - 1; // -1 as array index starts from 0

            // Checks if task has been completed
            Task currentTask = tasks.get(taskId);
            if (currentTask.isDone()) {
                ui.customPrint(String.format(taskAlreadyCompleted, currentTask.getDescription()));
            } else {
                currentTask.markAsDone(); // Mark current task as done
                ui.customPrint(String.format(taskMarkedDone, currentTask));
            }
        }
    }
}
