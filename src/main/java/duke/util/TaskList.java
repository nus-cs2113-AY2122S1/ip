package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks. Also has various functions to modify the list of tasks.
 */
public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Prints all Tasks in taskList to the command line.
     */
    public void listTasks() {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    /**
     * Marks the task at the specified index in taskList as done.
     * @param index The index of the task to be marked done.
     * @throws DukeException If the task is already done.
     */
    public void markTaskDone(int index) throws DukeException {
        taskList.get(index).markComplete();
        System.out.println("Task " + taskList.get(index).getDescription() + " marked as complete.");
    }

    /**
     * Deletes the task at the specified index in taskList.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        String taskStorage = taskList.get(index).toString();
        taskList.get(index).decrementTaskNumber();
        taskList.remove(index);

        System.out.println("Alright. I've deleted this task:");
        System.out.println(taskStorage);
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    /**
     * Adds a Task to the end of taskList.
     * @param task The Task to be added to the list.
     */
    public void addNewTask(Task task) {
        taskList.add(task);

        System.out.println("Gotcha. I've added this task:");
        System.out.println(taskList.get(Task.getTotalTasks() - 1));
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    /**
     * Finds a task in a list of Tasks given a String key.
     * @param key The String to match when searching in the list of Tasks.
     * @return ArrayList The list of Tasks matching the given key.
     */
    public ArrayList<Task> findTasks(String key) {
        ArrayList<Task> keyMatch = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().contains(key)) {
                keyMatch.add(t);
            }
        }
        return keyMatch;
    }
}
