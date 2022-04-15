package Duke;

import Duke.Task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> TASKS_ARRAY_LIST = new ArrayList<>();

    public TaskList() {};

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        TASKS_ARRAY_LIST.add(task);
    }

    /**
     * Returns the whole array list.
     *
     * @return Returns the entire task list as an array list.
     */
    public ArrayList<Task> getEntireList() {
        return TASKS_ARRAY_LIST;
    }

    /**
     * Returns the task given its index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return Returns a Task type of the particular task given its index.
     */
    public Task getTask(int taskIndex) {
        return TASKS_ARRAY_LIST.get(taskIndex);
    }

    /**
     * Remove the a particular task from the array list given its index.
     *
     * @param taskIndex The index of the task to retrieve.
     */
    public void removeTask(int taskIndex) {
        TASKS_ARRAY_LIST.remove(taskIndex);
    }

    /**
     *  Returns the size of the array list.
     * @return Returns the size of the array list.
     */
    public int getSize() {
        return TASKS_ARRAY_LIST.size();
    }

    /**
     * Search the array list for task that satisfies the search parameters.
     * Prints the new array list of tasks that satisfy the query.
     *
     * @param searchParams The parameters to search for in the array list.
     */
    public void searchList(String searchParams) {
        ArrayList<Task> taskFoundList = new ArrayList<>();
        for (Task task: TASKS_ARRAY_LIST) {
            if (task.toString().contains(searchParams)) {
                taskFoundList.add(task);
            }
        }
        UI.printTasksFound(taskFoundList);
    }

}
