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
        System.out.println("Here are your list of tasks:");
        String output = "[x] No tasks found :(\n";
        if (taskList.size() > 0) {
            output = "";
            for (int i = 0; i < taskList.size(); i++) {
                task = taskList.get(i);
                output += String.format("[%s] %d. %s\n", task.getStatusIcon(), i + 1, task.getDescription());
            }
        }
        System.out.print(output);
    }

    /**
     * Adds the given task description to the list of tasks.
     *
     * @param description Name/Description of task.
     */
    public void addTask(String description) {
        taskList.add(new Task(description));
        System.out.printf("[+] Task added: %s\n", description);
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
        System.out.printf("    [%s] %s\n", taskSelected.getStatusIcon(), taskSelected.getDescription());
    }
}
