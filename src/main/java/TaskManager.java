public class TaskManager {

    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String task) {
        if (tasksCount < 100) {
            tasks[tasksCount] = new Task(task);
            tasksCount++;
            System.out.println("You have added a new task: " + task);
        } else {
            System.out.println("Your tasks are full, busy man! Please clear some of them first...");
        }
    }

    public void listTasks() {
        System.out.println("You have the following tasks:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public void markTaskAsDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
        System.out.println("Good job! You have finished the following:");
        System.out.println(tasks[taskIndex - 1].toString());
    }
}
