package Duke.Tasks;

import Duke.Tasks.*;
import Duke.DukeException.DukeException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Create a TaskList class with the given list of tasks.
     *
     * @param tasks List of recorded tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Create a TaskList class of an empty list.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Return the tasks in the TaskList.
     *
     * @return tasks Tasks in the TaskList.
     */
    public final ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Tell whether the TaskList is empty.
     *
     * @return isEmpty Status of whether the TaskList is empty.
     */
    public final boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Get the number of tasks in the TaskList.
     *
     * @return size Size of the TaskList.
     */
    public final int getNumOfSize() {
        return this.tasks.size();
    }

    /**
     * Print the tasks in the TaskList.
     *
     */
    public final void printTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("The current list is empty!\n");
        } else {
            int index = 1;
            for (Task task : this.tasks) {
                System.out.println(index++ + ". " + task.toString());
            }
        }
    }

    /**
     * Clear the tasks in the Tasklist.
     *
     */
    public final void clearTasks() {
        this.tasks = new ArrayList<Task> ();
    }

    public final void deleteTask(int index) throws DukeException {
        if (index >= this.tasks.size()) {
            throw new DukeException("The index is out of range :-(\n");
        } else {
            this.tasks.remove(index);
        }
    }

    /**
     * Set a task in the TaskList of certain index as done.
     *
     * @param index Index of the task to be set as done.
     * @throws DukeException If the index is out of range.
     */
    public final Task setDone(int index) throws DukeException {
        if (index >= this.tasks.size()) {
            throw new DukeException("The index is out of range :-(\n");
        } else {
            this.tasks.get(index).markedAsDone();
            return this.tasks.get(index);
        }
    }

    public final ArrayList<Task> find(String target) {
        ArrayList foundTasks = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(target)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public final void addTodo(String description) {
        this.tasks.add(new Todo(description));
    }

    public final void addDeadline(String description, String by) {
        this.tasks.add(new Deadline(description, by));
    }

    public final void addEvent(String description, String at) {
        this.tasks.add(new Event(description, at));
    }
}
