package Duke.Tasks;

public class Event extends Task {
    public String at;

    /**
     * Create a Event class with given description and by time.
     *
     * @param description Description of the event task.
     * @param by At time of the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Present event in string of prescribed format.
     *
     * @return String presented event description format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at
                + ")";
    }
}
