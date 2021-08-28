package ip.src.main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public static void markDone(int n, Task[] arr) {
        arr[n-1].isDone = true;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String description() {
        return description;
    }
}
