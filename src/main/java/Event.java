public class Event extends Task{

    protected String by;
    protected String taskType = "[E]";

    public Event(String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public String getTaskType() {
        return taskType;
    }

    public String printTask() {
        super.printTask();
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription()
                + "(at: " + getBy() + ")";
    }

}
