public class Deadline extends Task{
    private String deadlineDate;
    public Deadline (String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        String str = "[D]" + super.toString() +
                "(by:" + this.deadlineDate + ")";
        return str;
    }
}
