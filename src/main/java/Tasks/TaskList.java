package Tasks;

import java.util.ArrayList;

/**
 * Handles and Keeps track of the tasks
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private static final String NO_TASK_MESSAGE = "No tasks\n";

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Getter Function
     * @return the taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the task list
     * @param task the task to be added
     * @return the string describing the task added
     */
    public String addTask(Task task) {
        taskList.add(task);
        return task.toString();
    }

    /**
     * Sends the list of tasks to be printed. No task message if no tasks are in the system.
     * @return the list of tasks
     */
    public String printAllTasks() {
        if (taskList.isEmpty()) {
            return NO_TASK_MESSAGE;
        }
        String listOfTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            listOfTasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return listOfTasks;
    }

    /**
     * Marks the specified task as done
     * @param index the index of the task to be marked as done
     * @return the description of the tasked marked as done
     */
    public String markTaskAsDone(int index) {
        taskList.get(index - 1).markAsDone();
        return taskList.get(index - 1).toString();
    }

    /**
     * Deletes a task from the task list
     * @param index the index of the task to be deleted
     * @return the description of the task deleted
     */
    public String deleteTask(int index) {
        String removedTask = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        return removedTask;
    }

    /**
     * Sends the description of the newest task to be printed
     * @return the description of the newest task
     */
    public String printNewestTask() {
        return taskList.get(taskList.size() - 1).toString();
    }

    public String findTasksContaining(String stringToFind) {
        String listOfTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(stringToFind)) {
                listOfTasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
            }
        }
        return listOfTasks;
    }
}
