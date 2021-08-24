import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskList;

    /* Constructor for Taskmanger */
    public TaskManager() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the task manager.
     *
     * @param taskName Name of the task to add.
     */
    public void addTask(String taskName) {
        taskList.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    /* Prints a list of all tasks in task manager */
    public void listTasks() {
        int index = 1;

        for (Task task : taskList) {
            System.out.println(index + ". " + task.getName());
            index++;
        }
    }
}
