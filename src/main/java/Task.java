public class Task {
    protected String description;
    protected boolean isDone;

    protected static int numberOfTasks = 0;
    protected static Task[] tasks = new Task[100];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tasks[numberOfTasks] = this;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public static Task getTask(int num) {
        return tasks[num];
    }
}
