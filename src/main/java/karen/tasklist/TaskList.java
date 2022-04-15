package karen.tasklist;

import karen.tasklist.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * This class constructor is used to instantiate a new TaskList object that creates
     * a new ArrayList to store the Task objects.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Task objects in TaskList.
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * This method adds a Task object to the taskList.
     *
     * @param task Task object to be added into the taskList
     */
    public void addTask(Task task){
        this.taskList.add(task);
    }

    /**
     * Returns the number of Task objects in taskList.
     *
     * @return number of Task objects in taskList as an Integer
     */
    public int getSize() {
        return this.getTaskList().size();
    }

    /**
     * This method removes a Task object from the taskList.
     *
     * @param taskIndex index of the Task object to be removed from taskList
     */
    public void removeTask(int taskIndex) {
        this.taskList.remove(taskIndex);
    }
}
