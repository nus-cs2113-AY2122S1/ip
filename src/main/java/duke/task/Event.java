package duke.task;

public class Event extends Task{
    private String eventTime;

    /**
     * Constructor with task description and event time.
     * @param description Task description.
     * @param eventTime Event time.
     */
    public Event(String description,String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructor with task description and event time and whether event has been done.
     * @param description Task description.
     * @param eventTime Event time.
     * @param isDone Whether event has been done.
     */
    public Event(String description,String eventTime,boolean isDone){
        super(description,isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns event time.
     * @return Event time.
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * Returns the event in string.
     * @return Event in string.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }

}
