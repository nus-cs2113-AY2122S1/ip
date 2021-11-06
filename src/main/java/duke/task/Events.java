package duke.task;

import duke.exception.DukeException;

public class Events extends Task {

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String parseToStore() {
        return "E |" + super.parseToStore() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
