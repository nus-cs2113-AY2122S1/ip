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
            return new CommandResult(result + " removed" + System.lineSeparator() + Duke.taskList.size()
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
            String task;
            int j;
            j = Integer.parseInt(command[1]) - 1;
            if (j > Duke.taskList.size()) {
                throw new RemoveException("That task does not exist");
            }
            task = Duke.taskList.get(j).description;
            Duke.taskList.remove(j);
            return task;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "Please specify a task [use numbers to indicate the task]";
        } catch (IndexOutOfBoundsException e) {
            return "That task does not exist";
        }
    }
}
