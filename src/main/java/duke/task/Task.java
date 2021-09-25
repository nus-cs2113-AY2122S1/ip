package duke.task;

public class Task implements TaskInterface {
    private static final String COMPLETE_STATUS_ICON = "[X]";
    private static final String INCOMPLETE_STATUS_ICON = "[ ]";
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
