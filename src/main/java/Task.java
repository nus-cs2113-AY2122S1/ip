public class Task {
    private String task;
    private boolean completed = false;

    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean markAsCompleted() {
        if (completed) {
            return false;
        } else {
            this.completed = true;
            return true;
        }
    }

    public String getStatusString() {
        return String.format("[%c] %s", isCompleted() ? 'X' : ' ' ,this.getTask());
    }
}
