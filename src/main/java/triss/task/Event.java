package triss.task;

public class Event extends Task{
    /** The timing this event occurs at */
    private String timing;

    /**
     * Creates a deadline with task type [E], and timing based on user's input.
     * @param name The name of the event.
     * @param timing The timing of the event.
     */
    public Event(String name, String timing) {
        super(name);
        this.timing = timing;
        this.typeOfTask = "[E]";
    }

    /**
     * Get the timing of the event.
     * @return Timing of the event.
     */
    public String getTiming() {
        return timing;
    }

    /**
     * Returns the event in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task] ([Timing of Task])
     */
    @Override
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getTiming() + ")";
    }
}
