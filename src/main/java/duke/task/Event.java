package duke.task;

public class Event extends Task {
    private String eventDuration;

    public Event(String name, String eventDuration) {
        super(name);
        this.eventDuration = eventDuration;
        this.type = 'E';
    }

    public Event(String name, String eventDuration, Boolean isDone) {
        super(name, isDone);
        this.eventDuration = eventDuration;
        this.type = 'E';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]" + "[" + done + "] " + description + " (at: " + eventDuration + ")";
    }

    @Override
    public String formatData() {
        return super.formatData() + "|" + eventDuration;
    }

    @Override
    public String getDataForFind() {
        return super.getDataForFind() + " " + eventDuration;
    }
}
