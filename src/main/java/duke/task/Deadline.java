package duke.task;

public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", this.taskDescription, this.date);
    }
}
