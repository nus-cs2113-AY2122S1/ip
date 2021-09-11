package duke.tasks;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }
    
    @Override
    public String toData() {
        return "E | " + ((isDone) ? 1 : 0) + " | " + task + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + task + " (at: " + at + ")";
    }

}
