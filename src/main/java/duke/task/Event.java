package duke.task;

public class Event extends Task {

    protected String at;

    /**
     *
     * @param description task information
     * @param at at what time
     * @param isDone true of false
     */
    public Event(String description, String at, boolean isDone) {
        super(description,isDone);
        this.at = at;
        this.taskType = 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}