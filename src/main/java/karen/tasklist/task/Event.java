package karen.tasklist.task;

public class Event extends Task{
    private String at;

    public Event(String taskDescription, String at){
        super(taskDescription);
        this.at = at;
    }

    /**
     * Returns "Event" as the task type.
     *
     * @return String to represent task type of Event object
     */
    public String getType(){
        return "Event";
    }

    /**
     * Returns the date which the Event task object is at as a String.
     *
     * @return String to represent the date which the Event task object is at
     */
    public String getAt(){
        return at;
    }

    /**
     * Returns a formatted task description of the Event task object, eg. "Finish Homework (at: 21-09-2021)".
     *
     * @return a formatted task description of the Event task object as a String
     */
    public String getFormattedDescription(){
        return String.format("%s (at: %s)", this.taskDescription,this.at);
    }

    /**
     * Returns a formatted task description of the Event task object as a String to be
     * saved in the storage file, eg. "Event,X,Finish Homework, 21-9-2021".
     *
     * @return a formatted task description of the Event task object as String to be saved in the storage file
     */
    public String getFormattedFileDescription() {
        return String.format("Event,%s,%s,%s",getStatusIcon(), taskDescription, this.at);
    }


}
