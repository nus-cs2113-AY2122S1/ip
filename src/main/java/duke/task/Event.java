package duke.task;

public class Event extends Task{
    private String eventTime;

    public Event(String description,String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description,String eventTime,boolean isDone){
        super(description,isDone);
        this.eventTime = eventTime;
    }


    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }

}
