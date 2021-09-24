/**
 * a type of task with a date element to it
 */
public class Event extends Task {

    private String at;

    /**
     * Constructor for Event
     *
     * @param description what the event is about
     * @param isDone      whether the event has been done or not
     * @param at          when the event is
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Builds on the string that is formatted for DukeData/data.txt for tasks
     *
     * @return String for storing in DukeData/data.txt
     */
    public String getDataStorageString() {
        return 'E' + super.getDataStorageString() +
                " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + at + ")";
    }


}
