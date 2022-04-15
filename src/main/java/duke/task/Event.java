package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event as a string to be written in a data file.
     *
     * @return Task in text format e.g. "E | 0 | [description] | [event time]".
     */
    @Override
    public String toText() {
        return "E " + super.toText() + " | " + at;
    }

    /**
     * Returns the event as a string to be displayed in the terminal for the user
     * to read the task in string format.
     *
     * @return Task in string format e.g. "[E][X] [description] (at: [event time])".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
