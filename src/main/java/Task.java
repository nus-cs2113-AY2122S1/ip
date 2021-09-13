
public abstract class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return getName();
    }

    public String toFileString() {
        return getName();
    }

    public abstract String getTypeIcon();
}
