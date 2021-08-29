public class Deadline extends Task{
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    public String listTask() {
        return "[D]" + super.listTask() + " (by: " + endDate + ")";
    }
}
