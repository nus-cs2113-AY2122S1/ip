public class Task {
    protected String description;
    protected boolean complete;

    public Task(String description) {
        this.description = description;
        this.complete = false;
    }
    public void markComplete() {
        this.complete = true;
    }
    public String getStatus() {
        if (complete) {
            return "[X]";
        }
        return "[ ]";
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return "T";
    }
    public String toString() {
        return "[T]" + getStatus() + description;
    }
    public String getTime() {
        return "";
    }
}
