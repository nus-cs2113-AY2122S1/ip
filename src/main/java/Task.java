public class Task {
    
    public static int numItemsAdded = 0;
    protected Boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + name;
    }
}
