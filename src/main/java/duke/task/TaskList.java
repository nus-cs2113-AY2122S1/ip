package duke.task;

import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Represents the list containing Duke's {@code Task}s.
 * Tasks can be added, deleted, and marked done from the list in this class.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Marks the task at {@code indexOfTaskToMarkDone} in {@code taskList} as done.
     * Prints a message showing the user the task marked done.
     *
     * @param indexOfTaskToMarkDone index of task in {@code taskList} to be marked as done
     * @throws IndexOutOfBoundsException if {@code indexOfTaskToMarkDone} is not contained in {@code taskList}
     */
    public void markTaskDone(int indexOfTaskToMarkDone) throws IndexOutOfBoundsException {
        try {
            taskList.get(indexOfTaskToMarkDone).setDone(true);
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Adds {@code newTask} to {@code taskList}.
     * Prints message informing user that the task has been added successfully.
     *
     * @param newTask task to be added to {@code taskList}
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        Ui.printTaskAddedMessage(newTask, taskList.size());
    }

    /**
     * Deletes the task at {@code indexOfTaskToDelete} from {@code taskList}.
     * Prints a message informing the user that the task has been successfully deleted along with the task.
     *
     * @param indexOfTaskToDelete index of task in {@code taskList} to be deleted
     */
    public void deleteTask(int indexOfTaskToDelete) throws IndexOutOfBoundsException {
        try {
            Ui.printTaskDeletedMessage(taskList.get(indexOfTaskToDelete), taskList.size());
            taskList.remove(taskList.get(indexOfTaskToDelete));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException();
        }
    }
}
