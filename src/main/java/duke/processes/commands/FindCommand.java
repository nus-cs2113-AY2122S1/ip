package duke.processes.commands;

import duke.Duke;
import duke.exceptions.TaskException;
import duke.processes.tasks.Task;

public class FindCommand extends Command {

    public static final int FIND_LENGTH = 5;
    public static String keyword;

    /**
     * Constructor that checks to see if the user has entered the command in the correct format.
     * initializes the keyword that the user is searching for.
     *
     * @param command  an array of strings of the users response
     * @param response the direct string message of the users response
     */
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

    /**
     * iterates through the main taskList to check for tasks that matches
     * the keyword and prints out the matching tasks onto the console.
     *
     * @return returns the message depending on whether the search yielded results
     */
    public CommandResult execute() {
        int i = 0;
        int found = 0;
        for (Task task : Duke.taskList) {
            i++;
            if (task.description.contains(keyword)) {
                System.out.println(i + ".[" + task.getTaskID() + "]" +
                        "[" + task.getStatusIcon() +
                        "] " + task.description + " " + task.getDate());
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