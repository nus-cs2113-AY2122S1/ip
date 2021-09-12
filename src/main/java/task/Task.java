package task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String desc, Boolean status) {
        setDescription(desc);
        setStatus(status);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        String result;
        if (isDone) {
            result = "X";
        } else {
            result = " ";
        }
        return result;
    }

    public String toString() {
        return "";
    }
}
