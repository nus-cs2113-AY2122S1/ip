public class TaskManager {
    protected Task[] tasks;
    protected int taskCount;

    public TaskManager() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void printTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.println(i + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public void doneTask(int index) {
        tasks[index].setDone();
        System.out.println("Nice! I've marked this task as done:\n [X] " + tasks[index].getDescription());
    }

    public void addTask(String task) {
        tasks[taskCount + 1] = new Task(task);
        taskCount++;
        System.out.println("Added task to list: " + task);
    }

}
