package duke.task;

public class Todo extends Task {

    private static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a todo cannot be empty.";

    /**
     * Creates a Todo Task
     *
     * @param description to describe the todo
     * @throws IllegalArgumentException if the description is empty or null
     */
    public Todo(String description) throws IllegalArgumentException {
        super(description);
        if (isStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        }
    }
    /**
     * Formats todo object to a savable string format
     *
     * @return formatted Todo object as a String to be saved
     */
    @Override
    public String saveToText() {
        return "T | " + super.saveToText();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
