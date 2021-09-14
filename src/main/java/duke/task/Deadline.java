package duke.task;

import duke.exceptions.EmptyField;

public class Deadline extends Task {
    public String symbolSetTime = "/by";

    public Deadline(String description, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
    }

    public Deadline(String description, boolean done, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
        setStatus(done);
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s | %s", "D", status? "1":"0", description, time.format(saveFormatter));
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0,1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (by: %s)", classIndicator, statusIndicator, description, getTime());
    }
}
