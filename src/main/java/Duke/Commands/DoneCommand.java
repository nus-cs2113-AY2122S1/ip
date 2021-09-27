package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.UI;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    String[] splittedInput;

    /**
     * Splits the input given the regular expression of a whitespace and
     * initialise it to a String array named splittedInput.
     *
     * @param input The entire line of command entered by the user.
     */
    public DoneCommand(String input) {
        splittedInput = input.split(" ");
    }

    /**
     * Marks a particular task from the task list as done and notify the user.
     *
     * @throws DukeException If the index that the user wants to mark as done is out of bounds, cease to exist or is not of an integer format.
     */
    @Override
    public void execute() throws DukeException {
        try {
            int taskIndex = markTaskAsDone(taskList);
            UI.printMarkedAsDoneMessage(taskList, taskIndex);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            handleInvalidIndexErrors(taskList);
        }
        UI.printBorder();
    }

    /**
     * Marks a particular task from the task list as done without notifying the user.
     *
     * @throws DukeException If the index that the user wants to mark as done is out of bounds, cease to exist or is not of an integer format.
     */
    @Override
    public void executeFromFile() throws DukeException {
        try {
            markTaskAsDone(taskList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(taskList);
        }
    }

    /**
     * Marks the task of the index entered by the user as done and
     * returns the particular index.
     *
     * @param taskList Current task list that is in use.
     * @return Index of task to be marked as done.
     */
    private int markTaskAsDone(TaskList taskList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        taskList.getTask(taskIndex).markAsDone();
        return taskIndex;
    }
}
