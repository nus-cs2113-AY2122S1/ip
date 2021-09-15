package duke.task;

public class Event extends Deadline {
    private String startTime;

    public Event(String name, String eventDuration) {
        super(name, eventDuration);
        this.startTime = null;
        this.type = 'E';
    }

    public Event(String name, String eventDuration, Boolean isDone) {
        super(name, eventDuration, isDone);
        this.startTime = null;
        this.type = 'E';
    }
}
