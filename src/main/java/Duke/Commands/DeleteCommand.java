package Duke.Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.UI;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    String[] splittedInput;

    public DeleteCommand(String input) {
        splittedInput = input.split(" ");
    }

    @Override
    public void execute() throws DukeException {
        try {
            deleteTask(taskList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            handleInvalidIndexErrors(taskList);
        }
        UI.printBorder();
    }

    private void deleteTask(TaskList taskList) {
        int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
        System.out.println("\tAlright, I've deleted this task: " + System.lineSeparator()
                + "\t" + taskList.getTask(taskIndex).toString());
        taskList.removeTask(taskIndex);
        System.out.println("\tYou now have " + taskList.getSize() + " tasks on your task list.");
    }
}
