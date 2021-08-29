public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.taskType = 'D';
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        String date = by.split(" ", 2)[1];
        return super.getTaskInfo() + "(by: " + date + ")";
    }
}
