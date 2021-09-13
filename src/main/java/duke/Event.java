package duke;
import duke.Task;

public class Event extends Task {
    public static final String MARK_IS_EVENT = "[E]";
    public Event(String event){
        super(event);
    }

    @Override
    public String getTask(){
        int indexDivider = task.indexOf("/");
        String taskContent = task.substring(0,indexDivider).trim();
        String timeContent = task.substring(indexDivider + 1).trim();
        String[] timeSplit = timeContent.split(" ", 2);
        String time = timeSplit[1].trim();
        if(isDone){
            return MARK_IS_EVENT + MARK_AS_DONE + " " + taskContent +  " (at: " + time + ")";
        }
        else{
            return MARK_IS_EVENT + MARK_AS_NOT_DONE + " " + taskContent +  " (at: " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "event " + task;
    }
}
