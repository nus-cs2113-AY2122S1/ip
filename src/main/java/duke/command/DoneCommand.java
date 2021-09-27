package duke.command;

import duke.exception.ErrorDoneException;
import duke.task.*;

import duke.ui.Ui;

public class DoneCommand extends Command {
    private String task;
    public DoneCommand(String task) {
        this.task = task;
    }

    /**
     * Mark the task as done in list according to the index input by user
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     * @throws ErrorDoneException If the format of index
     *                            is not correct, exception occurs
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws ErrorDoneException {
        try {
            int index = Integer.parseInt(task.substring(task.indexOf(" ") + 1));
            if (index > list.listSize() || index <= 0) {
                ui.printSplitLine();
                System.out.println("    Out of range. Please try again");
                ui.printSplitLine();
            } else {
                index = index - 1;
                list.getTask(index).isDone = true;
                list.getTask(index).description = list.getTask(index).description.replaceFirst(" ", "X");
                doneList.addDoneTask(list.getTask(index));
                ui.printSplitLine();
                System.out.println("    Nice! I've marked this task as done:\n    "
                    + list.getTask(index));
                ui.printSplitLine();
                ui.printDoneList(doneList, ui);
            }
        } catch (NumberFormatException e) {
            throw new ErrorDoneException();
        } catch (IndexOutOfBoundsException e) {
            throw new ErrorDoneException();
        }
    }
}
