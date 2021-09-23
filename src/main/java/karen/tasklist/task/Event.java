package karen.tasklist.task;

public class Event extends Task{
    private String at;
    private String eventTask;
    private String formattedDescription;

    public Event(String fullTaskDescription, String at, String eventTask){
        super(fullTaskDescription);
//        this.at = getAt();
        this.at = at;
        this.eventTask = eventTask;
        this.formattedDescription = getFormattedDescription();
    }


    public String getType(){
        return "Event";
    }

    public String getAt(){
        return at;
    }

    public String getFormattedDescription(){
        return String.format("%s (at: %s)", this.eventTask,this.at);
    }


    public String getFormattedFileDescription() {
        return String.format("Event,%s,%s,%s",getStatusIcon(), eventTask, this.at);
    }

    //obtain the task to do from the input description
    public String getTask(){
        return eventTask;
    }

}
