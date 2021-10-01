package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command type depending on the user's input.
 */
public class Command {
    protected TaskList taskList;
    public Command() {}

    /**
     * Initialise the tasks list to be used by the user.
     * @param tasksList Current task list that is in use.
     */
    public void setData(TaskList tasksList) {
        taskList = tasksList;
    }

    /**
     * Execute different set of instructions in the subclass based on the user's command.
     * @throws DukeException If there is an error upon executing the instructions.
     */
    public void execute() throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Execute different set of instructions in the subclass based on the taskList.txt file.
     * @throws DukeException If there is an error upon executing the instructions.
     */
    public void executeFromFile() throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     *  Returns the due date in a String format.
     *
     * @param input The entire line of command entered by the user.
     * @param slashIndex The index of the slash from the user's input.
     * @return Returns the due date in String.
     * @throws DukeException If there are no characters present after the slashIndex.
     */
    protected static String getDueDate(String input, int slashIndex) throws DukeException {
        if (slashIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of this task type requires a specific time");
        }
        String dueDate = input.substring(slashIndex + 1);
        checkForValidDateFormat(dueDate);
        return dueDate;
    }

    /**
     * Checks if the format of the due date inputted is valid.
     *
     * @param dueDate Due date inputted by the user in String.
     * @throws DukeException If the date is not in the format of "dd-mm-yyyy".
     */
    private static void checkForValidDateFormat(String dueDate) throws DukeException {
        try {
            String[] splittedDueDate = dueDate.split(" ");
            LocalDate date = LocalDate.parse(splittedDueDate[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format." + System.lineSeparator()
                    + "\tPlease enter your date in the following format: dd-mm-yyyy");
        }
    }

    /**
     * Checks if the task list is empty and throws an error message
     * if it is or if an invalid index of the task list is inputted.
     *
     * @param taskList Current task list that is in use.
     * @throws DukeException If the list is empty.
     */
    protected void handleInvalidIndexErrors(TaskList taskList) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("☹ OOPS!!! The list is empty!");
        } else {
            System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
        }
    }
}
