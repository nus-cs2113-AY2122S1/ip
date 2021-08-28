public class Deadline extends Task {
    protected String due;
    protected int keywordIdx = 0;

    /**
     * Extracts deadline info from user input String.
     *
     * @param inp User input string.
     * @return Date/time task is due.
     */
    public String extractDeadline(String inp) {
        // Deadline commands by user will contain "/by" substring.
        this.keywordIdx = inp.indexOf("/");
        return inp.substring(keywordIdx + 4);
    }

    /**
     * Returns deadline date to be appended to task description.
     *
     * @return Deadline input by user, within parentheses.
     */
    public String getDueString() {
        return "(by: " + this.due + ")";
    }

    /**
     * Constructor for Deadline subclass.
     *
     * @param deadlineDescription Name of task.
     */
    public Deadline(String deadlineDescription) {
        super(deadlineDescription, "deadline");
        this.due = extractDeadline(deadlineDescription);
        super.description = deadlineDescription.substring(9, this.keywordIdx) + getDueString();
    }
}
