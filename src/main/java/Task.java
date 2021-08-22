public class Task {

    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        String taskInString;
        if (isDone) {
            taskInString = "[X] " + description;
        } else {
            taskInString = "[ ] " + description;
        }
        return taskInString;
    }
}
