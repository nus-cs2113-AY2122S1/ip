public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String task) {
        tasks[tasksCount] = new Task(task);
        tasksCount++;
        System.out.println("    _____________________________________________________________");
        System.out.println("    Added: " + task);
        System.out.println("    _____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("    _____________________________________________________________");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println("    " + (i + 1) + "." + getStatusIcon(i) + " " + tasks[i].getTaskDetails());
        }
        System.out.println("    _____________________________________________________________");
    }

    public void markAsDone(int index) {
        tasks[index - 1].setDone();
        System.out.println("    _____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     [X] " + tasks[index - 1].getTaskDetails());
        System.out.println("    _____________________________________________________________");
    }

    public String getStatusIcon(int index) {
        return (tasks[index].isDone() ? "[X]" : "[ ]"); // mark done task with X
    }
}
