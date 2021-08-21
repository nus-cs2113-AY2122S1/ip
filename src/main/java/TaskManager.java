import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    /**
     * Default Constructor
     */
    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(String description) {
        System.out.println("\n[Duke]:\nAdding <" + description + "> to Duke's belly (TaskList)\n" + "-> That was delicious, burrrp!");
        Task temp = new Task(description);
        this.tasks.add(temp);
    }

    public void printTasks() {
        System.out.println("\n[Duke]:\n-> I still have room for more tasks!");
        System.out.println("Duke's Eaten Tasks (May or may not be digested yet...)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i).getTaskDescription());
        }
    }
}
