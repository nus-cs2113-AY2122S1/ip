
public class Deadline extends Task {
    protected boolean isDeadline;
    protected String Description;
    protected String Date;
    protected boolean isData;
    dateTime dateTime = new dateTime();


    public Deadline(String description, String date, boolean isData) {
        super(description);
        Description = description;
        isDeadline = true;
        Date = date;
        this.isData = isData;
    }

    @Override
    public String toString() {
        String taskType = "";
        String description = "";
        String middle = "";
        String time = "";
        String finalDate = "";
        if (isDeadline){
            taskType = "[D]";
            description = Description.substring(8, Description.lastIndexOf("/"));
            if (!isData) {
                middle = Date.substring(Date.indexOf(" ") + 1, Date.lastIndexOf(" "));
                time = Date.substring(Date.lastIndexOf(" "), Date.length());
                middle = dateTime.getDate(middle);
                time = dateTime.getTime(time);
                finalDate = "(by: " + middle + time + ")";
            }
            else {
                middle = Date;
                finalDate = "(" + middle + ")";
            }
//            finalDate = "(by: " + middle + time + ")";
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description + finalDate;
    }
}