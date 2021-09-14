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

    @Override
    public String toString() {
        String taskType = "";
        String description = "";
        String middle = "";
        String finalDate = "";
        if (isEvent){
            taskType = "[E]";
            description = Description.substring(5, Description.lastIndexOf("/"));
            middle = Date.substring(Date.lastIndexOf("at ") + 3, Date.length());
            finalDate = "(at: " + middle + ")";
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description + finalDate;
    }
}