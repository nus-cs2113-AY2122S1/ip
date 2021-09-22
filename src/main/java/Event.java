/**
 * An extension of the Task class.
 * Contains a description of the event as well as the details of when the event takes place.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructs an instance of Event.
     *
     * @param description Description of the event.
     * @param at Details of when the event takes place.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.category = "E";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String categoryIcon = this.getCategoryIcon();
        return categoryIcon + statusIcon + " " + description + " (at: " + at + ")";
    }

}