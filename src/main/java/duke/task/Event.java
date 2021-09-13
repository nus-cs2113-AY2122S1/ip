package duke.task;

public class Event extends Task {
    private String date;

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", this.taskDescription, this.date);
    }
}
