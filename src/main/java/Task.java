public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Getters
    public String getStatusIcon() {return (isDone ? "X" : " ");}
    public String getDescription() { return description; }

    // Setters
    public void markAsDone() { isDone = true; }
}