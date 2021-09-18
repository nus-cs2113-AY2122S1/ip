package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        String taskString = "";
        // Checks if tasks exists
        if (tasks.size() == 0) {
            taskString += Task.noTasks;
        } else {
            taskString += Task.hereAreYourTasks;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            taskString += (i + 1) + "." + currentTask.toString() + "\n";
        }
        ui.customPrint(taskString);
    }
}
