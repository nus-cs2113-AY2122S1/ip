import java.util.ArrayList;

/**
 * The TaskManager class manages the list of tasks, including adding and printing.
 */
public class TaskManager {

    /* List of tasks */
    private ArrayList<Task> taskList;

    /**
     * Initialise a new list of tasks.
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Prints the list of tasks in a neatly formatted way.
     */
    public void printTaskList() {
        Task task;
        System.out.println("[*] Here are your list of tasks:");
        String output = "[x] No tasks found :(\n";
        if (taskList.size() > 0) {
            output = "";
            for (int i = 0; i < taskList.size(); i++) {
                task = taskList.get(i);
                output += String.format("   %d.[%s][%s] %s\n", i + 1, task.getTaskIcon(), task.getStatusIcon(),
                        task.getDescription());
            }
        }
        System.out.print(output);
    }

    /**
     * Adds the given task description to the list of tasks.
     *
     * @param task New task.
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("[+] Task added: ");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(), task.getDescription());
        System.out.printf("[=] You now have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param taskIndex 1-Based index of selected task.
     */
    public void complete(int taskIndex) {
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            System.out.println("[-] Task not found");
            return;
        }

        Task taskSelected = taskList.get(taskIndex - 1);
        taskSelected.markAsDone();
        System.out.println("[+] Task marked as done:");
        System.out.printf("   [%s][%s] %s\n", taskSelected.getTaskIcon(), taskSelected.getStatusIcon(),
                taskSelected.getDescription());
    }
}
