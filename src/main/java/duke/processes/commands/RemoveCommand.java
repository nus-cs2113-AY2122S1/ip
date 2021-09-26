package duke.processes.commands;

import duke.Duke;
import duke.exceptions.RemoveException;


public class RemoveCommand extends Command {

    public String[] tasksToRemove;

    /**
     * Constructo for RemoveCommand class, initializes tasksToRemove array using users
     * response
     *
     * @param command an array of strings of the users response
     */
    public RemoveCommand(String[] command) {

        tasksToRemove = command;
    }

    /**
     * removes the specified tasks
     *
     * @return CommandResult indicating if tasks was removed successfully
     */
    public CommandResult execute() {

        String result;

        try {
            result = remove(tasksToRemove);
            return new CommandResult(result + " tasks removed" + System.lineSeparator() + Duke.taskList.size()
                    + " tasks remaining");
        } catch (RemoveException e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * finds the index of the task that is to be removed and removes it from the main
     * Arraylist. If task specified no longer exists error message will be returned
     *
     * @param command an array of strings of the users response
     * @return CommandResults indicating whether task was successfully removed
     * @throws RemoveException Throws an error message if task specified no longer exists
     */
    public static String remove(String[] command) throws RemoveException {

        try {
            String[] tasks = command[1].split(",");
            int j = 0;
            for (String s : tasks) {
                int i = Integer.parseInt(s) - 1;
                if (i > Duke.taskList.size()) {
                    throw new RemoveException("That task does not exist");
                }
                Duke.taskList.remove(i - 1);
                j++;
            }
            return String.valueOf(j);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "Please specify a task [use numbers to indicate the task]";
        }
    }
}
