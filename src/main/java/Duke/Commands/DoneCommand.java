package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.UI;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    String[] splittedInput;

    public DoneCommand(String input) {
        splittedInput = input.split(" ");
    }

    @Override
    public void execute() throws DukeException {
        try {
            markTaskAsDone(taskList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(taskList);
        }
        UI.printBorder();
    }

    @Override
    public void executeFromFile() throws DukeException {
        try {
            markTaskAsDone(taskList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(taskList);
        }
        UI.printBorder();
    }

    private void markTaskAsDone(TaskList taskList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        taskList.getTask(taskIndex).markAsDone();
        System.out.println("\tGood job! I've marked this task as done: ");
        System.out.println("\t" + taskList.getTask(taskIndex));
    }
}
