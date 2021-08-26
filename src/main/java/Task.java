public class Task {
    protected String description;
    protected boolean isDone;

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    //constructor
    public Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    //set icon as "X" for done, " " for not done
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
