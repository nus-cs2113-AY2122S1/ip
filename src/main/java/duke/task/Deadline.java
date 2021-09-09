package duke.task;

import duke.exceptions.EmptyField;

public class Deadline extends Task {
    public String symbolSetTime = "/by";

    public Deadline(String description, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
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
        return String.format("[%s][%s] %s (by: %s)", classIndicator, statusIndicator, description, time);
    }
}
