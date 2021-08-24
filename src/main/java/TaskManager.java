import java.util.ArrayList;

/**
 * The TaskManager class manages the list of tasks, including adding and printing.
 */
public class TaskManager {

    /* List of tasks */
    private ArrayList<String> taskList;

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
        System.out.println("Here are your list of tasks:");
        String output = "[x] No tasks found :(\n";
        if (taskList.size() > 0) {
            output = "";
            for (int i = 0; i < taskList.size(); i++) {
                output += String.format("%d. %s\n", i + 1, taskList.get(i));
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
        taskList.add(description);
        System.out.printf("[+] Task Added: %s\n", description);
    }
}
