package duke.task;

/**
 * Class that encapsulates a Todo task
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo object
     *
     * @param description The name of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for user output
     *
     * @return A string representation of the Todo task formatted for user output,
     * consisting of its description and status
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Returns a string representation of the Todo task to write to file
     *
     * @return A string representation of the Todo task formatted for file writing,
     * consisting of its description and status
     */
    @Override
    public String parseDataIntoString() {
        return "T" + super.parseDataIntoString();
    }
}
