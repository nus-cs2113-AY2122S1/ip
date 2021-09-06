package task.type;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getStatusIcon() {
        return "[E]" + (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + description + "(at:" + at + ")";
    }

    public void markAsDone() {
        this.isDone = true;
    }
}