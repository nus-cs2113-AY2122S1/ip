import java.util.List;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task() {
        this("Unnamed");
    }

    public String getTaskName() {
        return name;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Sweet! You've just completed this task: ");
        System.out.println("[" + this.getStatusIcon() + "]" + name);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
