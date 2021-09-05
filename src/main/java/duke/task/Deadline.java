package duke.task;

public class Deadline extends Task {

    public static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a deadline cannot be empty.";
    public static final String BY_EMPTY_ERROR_MESSAGE = "The time of a deadline should be completed cannot be empty.";
    private String by;

    /**
     * Creates a Deadline Task
     *
     * @param description to describe the deadline
     * @param by          the time at which the deadline should be completed
     * @throws IllegalArgumentException if the description or by is empty or null
     */
    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        if (checkStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } else if (checkStringNullOrEmpty(by)) {
            throw new IllegalArgumentException(BY_EMPTY_ERROR_MESSAGE);
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
