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
     * Parses {@code userInput} to obtain the task ID of the task to mark done.
     * Marks the task with the task ID in {@code taskList} as done. An error message is printed if task ID entered by
     * the user is non-numeric, lacking from the command or not in the task list.
     *
     * @throws IndexOutOfBoundsException if task ID is not in the task list
     */
    public void markTaskDone(int indexOfTaskToMarkDone) throws IndexOutOfBoundsException {
        try {
            taskList.get(indexOfTaskToMarkDone).setDone(true);
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        Ui.printTaskAddedMessage(newTask, taskList.size());
    }

    /**
     * Parses {@code userInput} to obtain the task ID of the task to delete.
     * Deletes the task with the task ID in {@code taskList}. An error message is printed if task ID entered by
     * the user is non-numeric, lacking from the command or not in the task list.
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
