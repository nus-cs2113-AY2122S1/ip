package Duke;

public class Todo extends Task {

    /**
     * Class constructor for Todo
     *
     * @param description Description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Used to print todo including its task type and when it occurs
     *
     * @return String to print when trying to print object Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
