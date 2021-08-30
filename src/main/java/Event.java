public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", this.taskDescription, this.date);
    }
}
