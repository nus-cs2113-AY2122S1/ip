package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

/**
 * Deadline task that has a due date/time
 */
public class Deadline extends Task {
    protected String date;
    protected String time;

    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Get due date of deadline task
     * @return due date
     */
    private String getDate() {
        return this.date;
    }

    /**
     * Get due time of deadline task
     * @return due time
     */
    private String getTime() {
        return this.time;
    }

    /**
     * Returns the char that represents deadlines tasks
     * @return char for identifying deadlines
     */
    private String getCode() {
        return "D";
    }

    /**
     * To print deadline task in a certain format
     * @return String that shows the information and status of deadline task
     */
    @Override
    public String toString() {
        return "[D]" +
                super.getStatusIcon() +
                " " +
                super.getDescription() +
                " (by: " +
                this.getDate() +
                " " +
                this.getTime() +
                ")";
    }

    /**
     * Get the information and status of deadline task in a certain format for saving
     * @return String that is formatted and ready to save deadline
     */
    @Override
    public String toSave() {
        return this.getCode() +
                LOAD_DELIMITER +
                super.getDoneValue() +
                LOAD_DELIMITER +
                super.getDescription() +
                LOAD_DELIMITER +
                this.getDate() +
                LOAD_DELIMITER +
                this.getTime();
    }
}
