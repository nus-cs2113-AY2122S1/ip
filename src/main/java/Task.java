public class Task {
    protected String description;
    protected boolean isDone;
    protected String category;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
