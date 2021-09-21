package duke.task;

public class Events extends Task {

    private final String atWhen;

    /**
     * A constructor to create a task of type event.
     *
     * @param taskName name of task.
     * @param at date of occurrence.
     */
    public Events(String taskName, String at) {
        super(taskName);
        this.atWhen = at;
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atWhen + ")";
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String storageText () {
        return "E" + super.storageText() + "|" + atWhen;
    }
}
