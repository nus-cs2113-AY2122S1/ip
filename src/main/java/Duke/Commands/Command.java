package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Task;

import java.util.ArrayList;

public class Command {
    public Command() {
    }

    public void execute(ArrayList<Task> tasksList) throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void executeFromFile(ArrayList<Task> tasksList) throws DukeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    protected static String getDueDate(String input, int slashIndex) throws DukeException {
        if (slashIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of this task type requires a specific time");
        }
        return input.substring(slashIndex + 1);
    }

    protected void handleInvalidIndexErrors(ArrayList<Task> tasksList) throws DukeException {
        if (tasksList.size() == 0) {
            throw new DukeException("☹ OOPS!!! The list is empty!");
        } else {
            System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
        }
    }
}
