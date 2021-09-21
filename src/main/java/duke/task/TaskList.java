package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static final String MESSAGE_LIST_HEADER = "Task List:";

    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    /**
     * Checks if task index is valid.
     * Note: Task index starts from 1 (not 0).
     *
     * @param taskNumber The task index.
     * @return true if task index is valid, else false.
     */
    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber > 0 && taskNumber <= getSize());
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, else false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the size of the list.
     *
     * @return The size of list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The task index in the list.
     * @return The Task object if valid, else null.
     */
    public Task getTaskAt(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        }

        return null;
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index The task index in the list.
     * @return The Task object if valid, else null.
     */
    public Task removeTaskAt(int index) {
        if (isValidIndex(index)) {
            return tasks.remove(index);
        }

        return null;
    }

    /**
     * Finds the tasks with description matching the searchString. (Case-insensitive)
     *
     * @param searchString The string to search.
     * @return A list of tasks matching the searchString.
     */
    public ArrayList<Task> findTask(String searchString) {
        searchString = searchString.toLowerCase();

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < getSize(); i += 1) {
            Task task = getTaskAt(i);
            String description = task.description;
            if (description.toLowerCase().contains(searchString)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    @Override
    public String toString() {
        StringBuilder messageBuilder = new StringBuilder(MESSAGE_LIST_HEADER);
        for (int i = 0; i < getSize(); i += 1) {
            messageBuilder.append("\n");
            messageBuilder.append(i + 1);
            messageBuilder.append(": ");
            messageBuilder.append(getTaskAt(i));
        }

        return messageBuilder.toString();
    }
}
