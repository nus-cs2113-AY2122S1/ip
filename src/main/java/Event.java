/**
 * The Event object is an extension of the Task object. It handles the storage of the event task description and
 * the date/place of event.
 */
public class Event extends Task {
    protected boolean isEvent;
    protected String Description;
    protected String Date;

    public Event(String description, String date) {
        super(description);
        Description = description;
        Date = date;
        isEvent = true;
    }

    /**
     * Takes in description and date/place of event and returns full task details as given below.
     * @return task type + task status + task description + date/place
     */
    @Override
    public String toString() {
        String taskType = "";
        String description = "";
        String middle = "";
        String finalDate = "";
        if (isEvent){
            taskType = "[E]";
            description = Description.substring(5, Description.lastIndexOf("/"));
            middle = Date.substring(Date.indexOf(" ")+1, Date.length());
            finalDate = "(at: " + middle + ")";
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description + finalDate;
    }
}