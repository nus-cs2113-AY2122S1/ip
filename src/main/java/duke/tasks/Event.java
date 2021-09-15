package duke.tasks;

/**
 * Event class to represent a task which has an event.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @param "at" event date
 * @return modified message when the toString() method is called.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Changes event task in list format
     * to saved file format
     *
     * @return string of event task in saved file format
     */
    @Override
    public String toStringStore() {
        String storeString = "E | ";
        if (isDone) {
            storeString += "1 | ";
        }
        else {
            storeString += "0 | ";
        }
        storeString += description + " | " + at;
        return storeString;
    }
}
