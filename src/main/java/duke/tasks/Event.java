package duke.tasks;

/**
 * This class stores all the information as the regular Task class, as well as an additional at timing field,
 * which is used to store the time and date which the event will be held
 */
public class Event extends Task {
    private String at;

    /**
     * Creates an uncompleted event which includes the timing field as well
     *
     * @param description stores information about the event
     * @param at stores the time and date which the event will be conducted at
     */
    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * Creates an event task that is read from a saved file, to be stored into the task list
     *
     * @param isDone it stores the boolean for whether the event is marked completed or not
     * @param description stores information about the event
     * @param at stores the time and date which the event will be conducted at
     */
    public Event(boolean isDone,String description, String at){
        super(isDone,description);
        this.at = at;
    }

    /**
     * Returns the task as a CSV string to be written to the file before saving the data. It includes the
     * timing information as well.
     *
     * @return a CSV string of the task
     */
    public String saveFormat() {
        return super.saveFormat() + "," + at;
    }

    /**
     * Returns the timing which the event is held
     *
     * @return the string specified by the constructor for the variable at
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the task in a formatted string, which is more readable when displayed to the user
     *
     * @return a formatted string of the task
     */
    @Override
    public String toString(){
        return super.toString() + String.format(" (%s)",this.getAt());
    }
}
