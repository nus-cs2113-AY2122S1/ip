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
    public String getSaveFormat() {
        return String.format("%s | %s | %s | %s", "E", status? "1":"0", description, this.time.format(saveFormatter));
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0,1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (at: %s)", classIndicator, statusIndicator, description, getTime());
    }
}
