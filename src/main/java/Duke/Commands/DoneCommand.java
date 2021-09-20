package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Task;
import Duke.UI;

import java.util.ArrayList;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    String[] splittedInput;

    public DoneCommand(String input) {
        splittedInput = input.split(" ");
    }

    @Override
    public void execute(ArrayList<Task> tasksList) throws DukeException {
        try {
            markTaskAsDone(tasksList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(tasksList);
        }
        UI.printBorder();
    }

    @Override
    public void executeFromFile(ArrayList<Task> tasksList) throws DukeException {
        try {
            markTaskAsDone(tasksList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(tasksList);
        }
        UI.printBorder();
    }

    private void markTaskAsDone(ArrayList<Task> tasksList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        tasksList.get(taskIndex).markAsDone();
        System.out.println("\tGood job! I've marked this task as done: ");
        System.out.println("\t" + tasksList.get(taskIndex));
    }
}
