package ip.src.main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public static Task[] addTask(int n, Task[] tasks, String line)
    {
        Task task = new Task(line);

        Task[] newTasks = new Task[n + 1];
        int i;
        for (i = 0; i < n; i++) newTasks[i] = tasks[i];

        newTasks[i] = task;

        return newTasks;
    }

    public static void printList(int n, Task[] arr)
    {
        for (int i = 0; i < n; i++) {
            System.out.println(i+1 + ". [" + arr[i].getStatusIcon() + "]" + arr[i].description);
        }
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(Task t) {
        this.isDone = true;
    }

}
