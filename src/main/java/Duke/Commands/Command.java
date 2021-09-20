package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;

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
        return input.substring(slashIndex + 1);
    }

    protected void handleInvalidIndexErrors(TaskList taskList) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("☹ OOPS!!! The list is empty!");
        } else {
            System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
        }
    }
}
