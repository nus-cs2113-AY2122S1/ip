public class Task {
    private String description;
    private boolean hasDone;

    public Task(String description) {
        this.description = description;
        hasDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return hasDone;
    }

    public void setAsDone() {
        hasDone = true;
    }

}
