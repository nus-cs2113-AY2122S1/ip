package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Find command. Helps to list out all the tasks with the matching string provided
 * by the user. It also does all the error handling related to that command.
 */

public class FindCommand extends Command{
    public static final String commandSyntax = "Command Syntax: find <string to search>";

    public String searchTerm;

    public FindCommand(String command, String searchTerm) {
        super(command);
        this.searchTerm = searchTerm;
    }

    @Override
    public String help() {
        return commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        StringBuilder taskString = new StringBuilder();
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            // Ignores case
            if (task.getDescription().toUpperCase().contains(searchTerm.toUpperCase())) {
                filteredTasks.add(task);
            }
        }

        // Checks if tasks exists
        if (filteredTasks.size() == 0) {
            taskString.append(Task.noTasks);
        } else {
            taskString.append(Task.hereAreYourTasks);
        }
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task currentTask = filteredTasks.get(i);
            taskString.append(i + 1).append(".").append(currentTask.toString()).append("\n");
        }
        ui.customPrint(taskString.toString());
    }
}
