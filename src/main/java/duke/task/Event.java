package duke.task;

public class Event extends Task {
    private final String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.TaskStatus() + "] " + this.getContent()
                + "(at: " + this.time + ")";
    }
}