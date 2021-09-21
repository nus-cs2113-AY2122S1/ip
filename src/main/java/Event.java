
public class Event extends Task{

    public void setDone() {
        isDone = "[X]";
    }

    public String isDone() {
        return isDone;
    }

    protected String isDone;

    protected  String date;

    protected String taskType = "[E]";

    /**
     * Constructor of the Event class
     *
     * @param description the user input to describe the deadline task.
     * @throws StringIndexOutOfBoundsException the user incorrect format of input to describe the event.
     */
    public Event(String description) throws StringIndexOutOfBoundsException {
        this.description = description.substring(6, description.indexOf("/"));
        this.date = description.substring(description.indexOf("/") + 4);
        isDone = "[ ]";
    }
    @Override
    public String toString() {
        return taskType + isDone + " " + description + " (at: " + date + ")";
    }
}
