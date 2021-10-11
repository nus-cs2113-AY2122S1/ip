package data;

import task.Task;

import java.util.ArrayList;

/**
 * The TaskList object contains the arraylist storing all tasks
 * together with operations for accessing and modifying the arraylist
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor, to create new instance with an empty list of tasks
     * Is called when no data.txt is found
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Constructor, to create new instance with existing list of tasks
     * Is called when existing and valid data.txt is found
     * @param tasks List of tasks, derived from parsing data.txt
     */
    public TaskList(ArrayList<Task> tasks) {
        setTaskList(tasks);
    }

    /**
     * Inserts a new task into the list of tasks
     * @param newTask Newly specified task by user
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes an existing task from list of tasks based on identifier
     * @param taskID Positional identifier, can be referenced from output of program
     * @return Task Object that had been specified for removal, used for printing details about removed task
     * @throws IndexOutOfBoundsException If taskID is out of range of list of tasks
     */
    public Task deleteTask(int taskID) throws IndexOutOfBoundsException {
        Task targetTask = tasks.get(taskID - 1);
        tasks.remove(targetTask);
        return targetTask;
    }

    /**
     * Sets the status of a task to done based on identifier
     * @param taskID Positional identifier, can be referenced from output of program
     * @throws IndexOutOfBoundsException If taskID is out of range of list of tasks
     */
    public void doneTask(int taskID) throws IndexOutOfBoundsException {
        tasks.get(taskID - 1).setStatus(true);
    }

    /**
     * Assigns specified arraylist, solely used in constructor
     * @param tasks Arraylist of tasks
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Provides full and current arraylist of tasks
     * @return ArrayList<Task> Updated list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Accesses specific task within list of tasks based on identifier
     * @param taskID Positional identifier, can be referenced from output of program
     * @return Task Object referenced by identifier
     */
    public Task getTask(int taskID) {
        return tasks.get(taskID);
    }

    /**
     * Returns textual representation of particular task based on identifier.
     * Used in printing details of task to users
     * @param taskID Positional identifier, can be referenced from output of program
     * @return String Textual representation of task specified
     */
    public String getTaskInfo(int taskID) {
        Task currentTask = tasks.get(taskID);
        return currentTask.toString();
    }

    /**
     * Gets current number of tasks in list
     * @return int Number of tasks currently
     */
    public int getSize() {
        return tasks.size();
    }
}