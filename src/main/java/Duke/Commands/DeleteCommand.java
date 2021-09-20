package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Task;
import Duke.UI;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    String[] splittedInput;

    public DeleteCommand(String input) {
        splittedInput = input.split(" ");
    }

    @Override
    public void execute(ArrayList<Task> tasksList) throws DukeException {
        try {
            deleteTask(tasksList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(tasksList);
        }
        UI.printBorder();
    }

    private void deleteTask(ArrayList<Task> tasksList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        System.out.println("\tAlright, I've deleted this task: " + System.lineSeparator()
                + "\t" + tasksList.get(taskIndex).toString());
        tasksList.remove(taskIndex);
        System.out.println("\tYou now have " + tasksList.size() + " tasks on your task list.");
    }
}
