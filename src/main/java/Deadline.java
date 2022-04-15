/**
 * The Deadline object is an extension of the Task object. It handles the storage of the deadline task description and
 * the due date.
 */
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

    /**
     * Takes in description and date of deadline task and return full task details as given below. Changes date format
     * from yyyy-mm-dd to MMM d yyyy by calling the dateTime program.
     * @return task type + task status + task description + due date
     */
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
        }
        else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description + finalDate;
    }
}