public class Event extends Task {
    protected String heldDate;

    /**
     * Class event constructor.
     *
     * @param description Short description of the task.
     * @param heldDate    Date when the event is held.
     */
    public Event(String description, String heldDate) {
        super(description);
        this.heldDate = heldDate;
    }

    /**
     * Gets the description and held date of the task.
     *
     * @return String containing task description and held date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + heldDate + ")";
    }
}
