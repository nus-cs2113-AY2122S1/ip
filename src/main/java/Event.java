package main.java;

public class Event extends Task{
    private String duration;

    public Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
