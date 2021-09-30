package duke.task;

/**
 * Represent a task to be done with a description of task
 */
public class Task implements TaskInterface {

    private static final String COMPLETE_STATUS_ICON = "[X]";
    private static final String INCOMPLETE_STATUS_ICON = "[ ]";
    protected static final String DATE_PATTERN = "\\d{4}-\\d{1,2}-\\d{1,2}";
    protected static final String TIME_PATTERN = "\\d{2}:\\d{2}";
    protected static final String EMPTY_STRING = "";
    protected static final String SEPARATOR = ";";

    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task
     * Set description of task
     * Set tasks to be incomplete by default
     *
     * @param description description of task
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set task as complete
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Get date of task, Empty String by default
     *
     * @param date String containing date of task
     * @param isForFile Boolean variable to decide format of date return
     * @return Empty String by default representing no date in task
     */
    public String getDate(String date, boolean isForFile){return EMPTY_STRING;}

    /**
     * Convert String into LocalDate
     *
     * @return Empty String by default representing no date in task
     */
    public String convertStringToDate(){return EMPTY_STRING;}

    /**
     * Get description of task
     *
     * @return String description of task
     */
    public String getDescription(){
        return description;
    }

    /**
     * Convert completion status of task into string form
     *
     * @return String representing if task is done
     */
    public String getStatusIcon(){
        return(isDone ? COMPLETE_STATUS_ICON : INCOMPLETE_STATUS_ICON);
    }

    /**
     * Set Task to print completion status and its description when printed
     *
     * @return String format of when task is printed
     */
    public String toString(){
        return getStatusIcon() + description;
    }

    /**
     * Set format of task to be saved into text file
     *
     * @return String format of when task is saved into text file
     */
    public String toFile(){
        return getStatusIcon() + SEPARATOR + description;
    }
}
