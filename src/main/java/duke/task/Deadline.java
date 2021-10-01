package duke.task;
public class Deadline extends Todo {
    String deadline;

    /**
     * Returns a Deadline object.
     *
     * @param name Name of the deadline.
     * @param deadline Deadline of the deadline.
     */
    public Deadline(String name, String deadline) {
        super(name);
        setDeadline(deadline);
    }

    /**
     * Returns deadline of the deadline.
     *
     * @return Deadline of the deadline.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline of the deadline.
     *
     * @param deadline Deadline of the deadline.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns a formatted string which contains information about the deadline.
     *
     * @returnm Formatted string which contains information about the deadline.
     */
    @Override
    public String toString() {
        String boolString = super.getIsDone() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", boolString, super.getName(), deadline);
    }
}
