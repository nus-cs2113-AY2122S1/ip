public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public String getIcon() {
        return "";
    }
    public String getBy() {
        return "";
    }
    public String getAt() {
        return "";
    }


    public String toString() {
        return getStatusIcon() + " " + this.description ;
    }


}
