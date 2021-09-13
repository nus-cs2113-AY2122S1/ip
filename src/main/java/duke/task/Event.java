package duke.task;

import duke.exceptions.EmptyField;

public class Event extends Task{
    public String symbolSetTime = "/at";

    public Event(String description, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
    }

    public Event(String description, boolean done, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
        setStatus(done);
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String time) throws EmptyField {
        if (time.isBlank()) {
            throw new EmptyField();
        }
        this.time = time;
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0,1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (at: %s)", classIndicator, statusIndicator, description, time);
    }
}
