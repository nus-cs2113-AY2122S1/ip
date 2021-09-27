package tasklist;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon depicting whether the task is done or not.
     * @return [X] icon if task is done and [ ] empty icon is task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone(){
        //change isDone for task to true
        this.isDone = true;
    }

    public String getType() {
        return type;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getStatusIcon() + description;
    }
}
