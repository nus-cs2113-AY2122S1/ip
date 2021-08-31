public class Event extends Task{
    String at;

    /**
     * Creates a new Task object to be stored in Duke's list of Tasks,
     * sets the name of the task as the name passed in by the user and
     * marks the task as incomplete
     *
     * @param description the name of the task to be created
     * @param at the date or time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
