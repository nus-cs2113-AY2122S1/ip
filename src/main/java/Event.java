package main.java;

public class Event extends Task{
    public static final String EVENT_ICON = "[E]";
    private String duration;

    public Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + " (at: " + duration + ")";
    }
}
