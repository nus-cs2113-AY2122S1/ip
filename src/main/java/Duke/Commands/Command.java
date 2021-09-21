package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    protected TaskList taskList;
    public Command() {}

    public void setData(TaskList tasksList) {
        taskList = tasksList;
    }

    public void execute() throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void executeFromFile() throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    protected static String getDueDate(String input, int slashIndex) throws DukeException {
        if (slashIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of this task type requires a specific time");
        }
        String dueDate = input.substring(slashIndex + 1);
        checkForValidDateFormat(dueDate);
        return dueDate;
    }

    private static void checkForValidDateFormat(String dueDate) throws DukeException {
        try {
            String[] splittedDueDate = dueDate.split(" ");
            LocalDate date = LocalDate.parse(splittedDueDate[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format." + System.lineSeparator()
                    + "Please enter your date in the following format: dd-mm-yyyy");
        }
    }

    protected void handleInvalidIndexErrors(TaskList taskList) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("☹ OOPS!!! The list is empty!");
        } else {
            System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
        }
    }
}
