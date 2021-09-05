public class Todo extends Task {

    public static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a todo cannot be empty.";

    public Todo(String description) {
        super(description);
        if(checkStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
