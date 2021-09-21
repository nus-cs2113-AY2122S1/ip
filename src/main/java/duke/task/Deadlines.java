package duke.task;

public class Deadlines extends Task {

    private final String byWhen;

    /**
     * A constructor to create a task of type deadline
     * @param taskName name of the task.
     * @param by deadline date of the task.
     */
    public Deadlines(String taskName, String by) {
        super(taskName);
        this.byWhen = by;
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String storageText () {
        return "D" + super.storageText() + "|" + byWhen;
    }
}
