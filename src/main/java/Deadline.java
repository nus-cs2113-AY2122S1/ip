public class Deadline extends Task{
    private final String DEADLINE_TASK_SIGN = "[D]";
    private String deadlineDate;

    public Deadline (String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * @return deadline date
     * */
    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        String str = DEADLINE_TASK_SIGN + super.toString() +
                "(by:" + this.deadlineDate + ")";
        return str;
    }
}
