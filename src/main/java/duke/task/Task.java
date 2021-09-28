package duke.task;

public class Task implements TaskInterface {
    private static final String COMPLETE_STATUS_ICON = "[X]";
    private static final String INCOMPLETE_STATUS_ICON = "[ ]";
    protected static final String DATE_PATTERN = "\\d{4}-\\d{1,2}-\\d{1,2}";
    protected static final String TIME_PATTERN = "\\d{2}:\\d{2}";
    protected static final String EMPTY_STRING = "";

    private final String description;
    private boolean isDone;
    protected static final String SEPARATOR = ";";

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void setDone(){
        isDone = true;
    }

    public String getDate(String date, boolean isForFile){return EMPTY_STRING;}

    public String convertStringToDate(){return EMPTY_STRING;}

    public String getDescription(){
        return description;
    }

    public String getStatusIcon(){
        return(isDone ? COMPLETE_STATUS_ICON : INCOMPLETE_STATUS_ICON);
    }

    public String toString(){
        return getStatusIcon() + description;
    }

    public String toFile(){
        return getStatusIcon() + SEPARATOR + description;
    }
}
