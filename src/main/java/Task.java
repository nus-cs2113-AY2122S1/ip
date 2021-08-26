public class Task {
    final private String name;
    private Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        isDone = true;
    }
}
