public class Task {
    private String description;
    private boolean isDone;
    private int noOfTask;

    public Task(String description, int noOfTask) {
        setDescription(description);
        this.isDone = false;
        setNoOfTask(noOfTask);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setNoOfTask(int index) {
        this.noOfTask = index;
    }

    public String getDescription() {
        return (this.description);
    }

    public String getStringNo() {
        return (Integer.toString(noOfTask));
    }

    public String toString() {
        if (isDone) {
            return("[X] " + description);
        } else {
            return("[ ] " + description);
        }
    }

}
