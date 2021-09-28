package duke.task;

/**
 * Represent one of the task object
 */
public class ToDo extends Task{

    private static final String TASK_SYMBOL = "[T]";

    /**
     * Constructor for ToDo task
     *
     * @param description Description of task
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Format string to print task to user
     *
     * @return Formatted String
     */
    @Override
    public String toString() {
        return TASK_SYMBOL + super.toString();
    }

    /**
     * Format string to save in text file
     *
     * @return Formatted String
     */
    @Override
    public String toFile(){
        return TASK_SYMBOL + SEPARATOR + super.toFile();
    }
}
