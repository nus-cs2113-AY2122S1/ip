package duke.task;

public class Event extends Task{

    private static final String TASK_SYMBOL = "[E]";
    private String startTime;

    public Event(String description, String startTime){
        super(description);
        this.startTime = startTime;
    }

    @Override
    public String toString(){
        return TASK_SYMBOL + super.toString() + "(at:" + startTime + ")";
    }
}
