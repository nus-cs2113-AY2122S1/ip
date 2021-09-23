package tasks;


import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
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

    /**
     * Returns the task's description.
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     * @return true if the task's hasDone variable is true, false otherwise
     */
    public boolean isDone() {
        return hasDone;
    }

    /**
     * Sets the completion status of the task as done.
     */
    public void setAsDone() {
        hasDone = true;
    }

    public String toString() {
        String hasDone = isDone()? "[X] ": "[ ] ";
        return hasDone + description;
    }

}
