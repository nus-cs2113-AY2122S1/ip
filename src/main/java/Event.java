package ip.src.main.java;

import java.util.Objects;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (Objects.equals(description, " ") || Objects.equals(description, "") ) {
            throw new DukeException();
        }
        else {
            this.at = at;
        }
    }

    @Override
    public String description() {
        return description + " (at: " + at + ")";
    }

}