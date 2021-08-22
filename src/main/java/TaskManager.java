import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private static List<Task> listOfTasks = new LinkedList<Task>();

    public TaskManager() {

    }

    /**
     * Returns the Task which is at
     * the index, null otherwise.
     *
     * @param indexTask index of the task to obtain.
     * @return Task at specified index.
     */
    private static Task getTask(int indexTask) {
        if (indexTask > listOfTasks.size() || indexTask < 0) {
            System.out.println("Task is out of range!");
            return null;
        }
        return listOfTasks.get(indexTask);
    }

    /**
     * Marks a certain task as done.
     *
     * @param indexTask The index of the task to be marked as done.
     */
    public static void markTaskAsDone(int indexTask) {
        if (indexTask > listOfTasks.size() || indexTask < 0) {
            System.out.println("No such task!");
            return;
        }
        Task currentTask = getTask(indexTask - 1);
        if (currentTask != null) {
            currentTask.markAsDone();
        }
    }

    /**
     * Adds a task to the tracking list.
     *
     * @param name The name/description of the task.
     */
    public static void addTask(String name) {
        Task newTask = new Task(name);
        listOfTasks.add(newTask);
        System.out.println("You have added: " + name);
    }

    /**
     * Prints all the task & their done status
     * in the list.
     */
    public static void printList() {
        if (listOfTasks.size() == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Your list of tasks contains:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            System.out.println((i + 1) + "." + "[" + currentTask.getStatusIcon() + "] " + currentTask.getTaskName());
        }
    }
}
