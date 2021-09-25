package duke.commands;

import duke.storage.DataStorage;
import duke.exception.EmptyListException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the {@code list} command and prints out each task in {@code taskList} along with its
     * type, description and done status.
     *
     * @param taskList {@code TaskList} containing the tasks to be printed
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        try {
            checkListSize(taskList);
            Ui.printTaskList(taskList);
        } catch (EmptyListException ele) {
            Ui.printEmptyListMessage();
        }
    }

    /**
     * Helper function which checks if {@code taskList} is empty. This method throws an exception to allow for the
     * printing of an error message in {@code execute}.
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
