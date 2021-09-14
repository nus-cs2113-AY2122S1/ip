public class Deadline extends Task {
    protected boolean isDeadline;
    protected String Description;
    protected String Date;


    public Deadline(String description, String date) {
        super(description);
        Description = description;
        isDeadline = true;
        Date = date;
    }

    @Override
    public String toString() {
        String taskType = "";
        String description = "";
        String middle = "";
        String finalDate = "";
        if (isDeadline){
            taskType = "[D]";
            description = Description.substring(8, Description.lastIndexOf("/"));
            middle = Date.substring(Date.lastIndexOf("by ") + 3, Date.length());
            finalDate = "(by: " + middle + ")";
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description + finalDate;
    }
}