package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.UI;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    String[] splittedInput;

    /**
     * Splits the input given the regular expression of a whitespace and
     * initialise it to a String array named splittedInput.
     *
     * @param input The entire line of command entered by the user.
     */
    public DeleteCommand(String input) {
        splittedInput = input.split(" ");
    }

    /**
     * Delete a particular task from the task list.
     *
     * @throws DukeException If the index that the user wants to delete is out of bounds, cease to exist or is not of an integer format.
     */
    @Override
    public void execute() throws DukeException {
        try {
            deleteTask(taskList);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            handleInvalidIndexErrors(taskList);
        }
        UI.printBorder();
    }

    /**
     * Removes the task of the index entered by the user from the task list and notify the user of the deletion.
     *
     * @param taskList Current task list that is in use.
     */
    private void deleteTask(TaskList taskList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        System.out.println("\tAlright, I've deleted this task: " + System.lineSeparator()
                + "\t" + taskList.getTask(taskIndex).toString());
        taskList.removeTask(taskIndex);
        System.out.println("\tYou now have " + taskList.getSize() + " tasks on your task list.");
    }
}
