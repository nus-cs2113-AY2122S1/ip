package tasks;


import java.time.LocalDate;

public class Task {
    protected String by;
    private String description;
    private boolean hasDone;

    public Task(String description) {
        this.description = description;
        hasDone = false;
    }

    public static LocalDate getDate(Task task) {
        if (task instanceof Event) {
            return ((Event) task).at;
        } else if (task instanceof Deadline) {
            return ((Deadline)task).by;
        } else return null;
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

    public String toString() {
        String hasDone = isDone()? "[X] ": "[ ] ";
        return hasDone + description;
    }

}
