public class Todo extends Task {

    public static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a todo cannot be empty.";

    /**
     * Creates a Todo Task
     *
     * @param description to describe the todo
     * @throws IllegalArgumentException if the description is empty or null
     */
    public Todo(String description) throws IllegalArgumentException {
        super(description);
        if (checkStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
