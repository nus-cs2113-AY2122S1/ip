public class Task {
    protected String description;
    protected boolean isDone;

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

    public void printTask() {
        System.out.println(description);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getStatusValue() {
        return (isDone ? "1" : "0");
    }

    public String getTextString() {
        return (description + "\n");
    }

}
