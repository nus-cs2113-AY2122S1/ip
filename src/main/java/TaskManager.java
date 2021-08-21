import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private static List<Task> listOfTasks = new LinkedList<Task>();

    public TaskManager() {

    }

    public static void addTask(String name) {
        Task newTask = new Task(name);
        listOfTasks.add(newTask);
        System.out.println("You have added: " + name);
    }

    public static void printList() {
        if (listOfTasks.size() == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Your list of tasks contains:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            System.out.println((i + 1) + ". " + currentTask.getTaskName());
        }
    }
}
