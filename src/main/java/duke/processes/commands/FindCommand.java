package duke.processes.commands;

import duke.Duke;
import duke.exceptions.TaskException;
import duke.processes.tasks.Task;

public class FindCommand extends Command{
    public static final int FIND_LENGTH = 5;
    public static String keyword;
    public FindCommand(String[] command, String response) {
        try {
            if (command[1].isEmpty()) {
                throw new TaskException("specify what you would like to find?");
            }
            keyword = response.substring(FIND_LENGTH);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public CommandResult execute() {
        int i = 0;
        int found = 0;
        for (Task task : Duke.taskList) {
            i++;
            if (task.description.contains(keyword)) {
                System.out.println(i + ".[" + task.getTaskID() + "]" +
                        "[" + task.getStatusIcon() +
                        "] " + task.description + task.getDate());
                found++;
            }
        }
        if (found == 0) {
            return new CommandResult("No results found :(");
        } else {
            return new CommandResult(System.lineSeparator() + found + " results found!");
        }
    }
}
