package duke.tasks;

/**
 * This class stores all the information as the regular Task class, as well as an additional at timing field,
 * which is used to store the time and date which the deadline will be due
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates an uncompleted deadline which includes the timing field as well
     *
     * @param description stores information about the deadline
     * @param by stores the time and date which the deadline will be due
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task that is read from a saved file, to be stored into the task list
     *
     * @param isDone it stores the boolean for whether the deadline is marked completed or not
     * @param description stores information about the deadline
     * @param by stores the time and date which the deadline will be due
     */
    public Deadline(boolean isDone,String description, String by){
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns the timing which the deadline is due
     *
     * @return the string specified by the constructor for the variable by
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the task as a CSV string to be written to the file before saving the data. It includes the
     * timing information as well.
     *
     * @return a CSV string of the task
     */
    @Override
    public String saveFormat() {
        return String.format(super.saveFormat() + "," + by);
    }

    /**
     * Returns the task in a formatted string, which is more readable when displayed to the user
     *
     * @return a formatted string of the task
     */
    @Override
    public String toString(){
        return super.toString() + String.format(" (%s)",this.getBy());
    }
}
