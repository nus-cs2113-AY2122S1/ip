package duke.data;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A list of Task.
 */
public class TaskList implements Iterable<Task>{
    // Attributes
    private final ArrayList<Task> tasks = new ArrayList<>(); // using Java Collections classes

    /**
     * Constructs empty task list.
     */
    public TaskList() {
    }

    /**
     * Constructs a list from the tasks in the given collection.
     *
     * @param sourceList a collection of tasks
     */
    public TaskList(Collection<Task> sourceList){
        tasks.addAll(sourceList);
    }

    /**
     * Constructs a shallow copy of the list.
     */
    public TaskList(TaskList source) {
        tasks.addAll(source.tasks);
    }

    public Task getTask(int index){
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Added task
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Deletes the identified task from the list.
     *
     * @param index last displayed index from the list
     */
    public void deleteTask(int index){
        this.tasks.remove(index);
    }

    /**
     * Marks the identified task as done.
     */
    public void doneTask(int index){
        this.tasks.get(index).markAsDone();
    }

    /**
     * Retrieves all tasks in the list whose descriptions contain the keyword.
     *
     * @param keyword for searching
     * @return lists of tasks found
     */
    public TaskList search(String keyword) {
        final List<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }

        return new TaskList(matchingTasks);
    }

    /**
     * Displays all current tasks in the list.
     */
    public void printList() {
        for (int i=0; i< tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Retrieves the number of tasks holding in the list now.
     *
     * @return size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
