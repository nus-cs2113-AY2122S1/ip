public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String task) {
        tasks[tasksCount] = new Task(task);
        tasksCount++;
        System.out.println("    _____________________________________________________________");
        System.out.println("    added: " + task);
        System.out.println("    _____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("    _____________________________________________________________");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks[i].getTaskDetails());
        }
        System.out.println("    _____________________________________________________________");
    }
}
