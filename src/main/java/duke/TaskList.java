package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * To perform operations on the list of tasks
 */
public class TaskList {
    public static ArrayList<Task> list = new ArrayList<>();
    static int count = 0;

    /**
     * Adds a task to the list and prints a completion message
     *
     * @param task is the Task object is being added to the list
     */
    public void addTask(Task task) {
        list.add(count, task);
        count++;

        Ui.printAddTaskMessage(task);
    }

    /**
     * Marks a task as done with the given index and prints a completion message
     *
     * @param taskIndex is the index of the task needed to be marked as done
     */
    public void doneTask(int taskIndex) {
        list.get(taskIndex).markAsDone();

        Ui.printDoneTaskMessage(taskIndex);
    }

    /**
     * Deletes a task with the given index and prints a completion message
     *
     * @param taskIndex is the index of the task needed to be deleted
     * @throws DukeException if index provided is larger than the list size or is lesser than 1
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= list.size()) {
            throw new DukeException("Task index does not exist!");
        } else if (taskIndex <= -1) {
            throw new DukeException("Task index must be larger than 0");
        }

        Ui.printDeleteTaskMessage(taskIndex);

        list.remove(taskIndex);
        count--;
    }

    /**
     * Prints all the tasks present in the list
     */
    public void listTasks() {
        Ui.printLineSeparator();
        Ui.printTaskList();
        Ui.printLineSeparator();
    }
}
