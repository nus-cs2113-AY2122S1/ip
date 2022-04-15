package duke.command;

import duke.data.TaskList;
import duke.type.Deadline;
import duke.ui.Ui;

/**
 * Command to add a deadline to a todo
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        super(CommandPrefix.DEADLINE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("adding deadline!");
    }

    /**
     * Adds a deadline to a todo
     *
     * @param tasks task list
     */
    @Override
    public void execute(TaskList tasks) {
        String userInput = Ui.readLine();
        String[] formattedInput = userInput.split(" ");
        int indexToModify = 0;
        try {
            indexToModify = readIndexAndModifyTask(tasks, userInput, formattedInput);
            saveListAndPrintDone(tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.printNotInRange(indexToModify);
            Ui.printDeadlineFormat();
        } catch (NumberFormatException e) {
            Ui.printIntegerOnly();
            Ui.printDeadlineFormat();
        }
    }

    /**
     * Modifies a <code>todo</code> into a <code>deadline</code> with a byWhen attribute
     *
     * @param tasks          task list to modify
     * @param userInput      user input in the format [INDEX] [DEADLINE]
     * @param formattedInput input separated by index, deadline
     * @return indexToModify to indicate if index input is out of range to user
     */
    private int readIndexAndModifyTask(TaskList tasks, String userInput, String[] formattedInput) {
        int indexToModify = Integer.parseInt(formattedInput[0]) - 1;
        String deadlineToInclude = Ui.stringWithoutFirstWord(userInput);
        Deadline updatedTask = new Deadline(tasks.getTaskList().get(indexToModify).getDescription(), deadlineToInclude);
        tasks.getTaskList().set(indexToModify, updatedTask);
        return indexToModify;
    }
}
