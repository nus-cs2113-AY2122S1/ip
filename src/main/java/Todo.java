/**
 * This represents the subclass To Do under superclass Task in each element of Task[] list in Duke.java.
 */
public class Todo extends Task {

    /**
     * This function initialises the deadline.
     *
     * @param description description input by user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This function modifies the output format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T" + FILE_STRING_SEPARATOR + super.toFile() + "\n";
    }

    @Override
    public boolean isInTask(String input) {
        return super.isInTask(input);
    }
}