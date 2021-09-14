package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to task list represented by tasks array.
     * @param task Task to add
     */
    public void addTasks(Task task, boolean toPrint) {
        tasks.add(task);
        if (toPrint) {
            ui.printAddTaskMessage(task, tasks);
        }
    }

    public void printTasks() {
        ui.printTaskList(tasks);
    }

    /**
     * Mark a task given by its index as done
     * @param taskIndex Index of task in task list to mark as done
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        tasks.get(taskIndex).markAsDone();
        ui.printTaskDoneMessage(taskIndex, tasks);
    }

    /**
     * Deletes a task given by its index
     * @param taskIndex Index of task in task list to delete
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        ui.printDeleteTaskMessage(taskIndex, tasks);
        tasks.remove(taskIndex);
    }
}
