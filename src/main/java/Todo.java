public class Todo extends Task {

    /**
     * Initialises the deadline.
     *
     * @param description description input by user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Modifies the output format.
     *
     * @return the desired output format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Modifies the string format for adding to file.
     *
     * @return the correct format for the task to be added to file.
     */
    @Override
    public String toFile() {
        return "T" + FILE_STRING_SEPARATOR + super.toFile() + "\n";
    }

    /**
     * Returns if the to do contains the input.
     *
     * @param input the input of the user.
     * @return returns whether input is contained in to do.
     */
    @Override
    public boolean isInTask(String input) {
        return super.isInTask(input);
    }
}