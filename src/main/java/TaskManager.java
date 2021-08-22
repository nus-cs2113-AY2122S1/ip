public class TaskManager {

    private final String[] tasks = new String[100];
    private int tasksCount = 0;

    public void addTask(String task) {
        if (tasksCount < 100) {
            tasks[tasksCount] = task;
            tasksCount++;
            System.out.println("You have added a new task: " + task);
        } else {
            System.out.println("Your tasks are full, busy man! Please clear some of them first...");
        }
    }

    public void listTasks() {
        System.out.println("You have " + tasksCount + " unfinished tasks:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
