package duke.task;

public class Event extends Task {
    protected String timing;

    /**
     * Constructs Event class given description and timing.
     *
     * @param description Description of event.
     * @param timing Timing of event.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Constructs Event class given description, timing and status.
     *
     * @param description Description of event.
     * @param timing Timing of event.
     * @param isDone Status of event.
     */
    public Event(String description, String timing, boolean isDone) {
        this(description, timing);
        super.taskDone(isDone);
    }

    /**
     * Gets icon of event.
     *
     * @return Icon of event.
     */
    public String getIcon() {
        return "E";
    }

    /**
     * Gets timing of event.
     *
     * @return Timing of event.
     */
    public String getTiming() {
        return "(at:" + timing + ")";
    }

    /**
     * Gets time of event.
     *
     * @return Time of event.
     */
    public String getTime() {
        return timing;
    }

    /**
     * Returns string representing event.
     *
     * @return String representing event.
     */
    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + getTiming();
    }
}
