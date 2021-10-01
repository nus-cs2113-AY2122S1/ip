package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String task;

    public DeleteCommand(String task) {
        this.task = task;
    }

    /**
     * Delete the task in list according to the index input by user
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     * @throws ErrorDoneException If the format of index input by user
     *                            is not correct, exception occurs
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws ErrorDeleteException {
        try {
            int index = Integer.parseInt(task.substring(task.indexOf(" ") + 1));
            if (index > list.listSize() || index <= 0) {
                ui.printSplitLine();
                System.out.println("    Out of range. Please try again");
                ui.printSplitLine();
            } else {
                ui.printSplitLine();
                System.out.println("    Noted. I've removed this task:\n    " + list.getTask(index - 1));
                list.removeTask(index - 1);
                System.out.println("    Now you have " + list.listSize() + " tasks in the list.");
                ui.printSplitLine();
            }
        } catch (NumberFormatException e) {
            throw new ErrorDeleteException();
        } catch (IndexOutOfBoundsException e) {
            throw new ErrorDeleteException();
        }
    }
}

