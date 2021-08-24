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
        System.out.println("Here is the list of the things your feeble human mind is incapable of keeping track of:");

        int index = 1;

        for (Task task : taskList) {
            String status = " ";

            if (task.isDone()) {
                status = "X";
            }

            System.out.println(index + ".[" + status + "] " + task.getName());
            index++;
        }
    }

    public void markDone(int index) {
        Task task = taskList.get(--index);

        task.setDone();
        System.out.println("The task has been marked as done. No need to thank me.");
        System.out.println("  [X] "+task.getName());
    }
}
