package duke.commands;

import duke.datasaver.DataManager;
import duke.exception.EmptyListException;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Executes the {@code list} command and prints out the entire each task in a list along with its
     * type, description and done status.
     *
     * @param taskList {@code TaskList} containing the tasks to be printed
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        try {
            checkListSize(taskList);
            Ui.printTaskList(taskList);
        } catch (EmptyListException ele) {
            Ui.printEmptyListMessage();
        }
    }

    /**
     * Helper function which checks if {@code taskList} is empty. This method throws an exception to allow for the
     * printing of an error message in {@code executeList}.
     *
     * @param taskList task list to be checked
     * @throws EmptyListException if the task list is empty
     */
    private static void checkListSize(TaskList taskList) throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
    }
}
