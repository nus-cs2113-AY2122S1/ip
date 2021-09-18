package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class WhatsOnCommand extends Command {
    public WhatsOnCommand(String command) {
        super(command);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        /* taskString = new StringBuilder();
        // Checks if tasks exists
        if (filteredTasks.size() == 0) {
            taskString.append(noTasks);
        } else {
            taskString.append(hereAreYourTasks);
        }
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task currentTask = filteredTasks.get(i);
            taskString.append(i + 1).append(".").append(currentTask.toString()).append("\n");
        }
        ui.customPrint(taskString.toString());*/
    }
}
