public class Task {

    protected String description;
    protected boolean isDone;
    protected String category;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getCategoryIcon() {
        return "[" + category + "]";
    }

}
