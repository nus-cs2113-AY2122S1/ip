package duke.task;

public class Deadline extends Task {

    protected String by;

    /**
     * @param description task information
     * @param by by what time
     * @param isDone true of false
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description,isDone);
        this.by = by;
        this.taskType = 'D';
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
