package tasks;


import java.time.LocalDate;
import java.time.LocalTime;

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
            return ((Event) task).date;
        } else if (task instanceof Deadline) {
            return ((Deadline)task).date;
        } else return null;
    }

    public static LocalTime getTime(Task task) {
        if (task instanceof Event) {
            return ((Event) task).time;
        } else if (task instanceof Deadline) {
            return ((Deadline)task).time;
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
