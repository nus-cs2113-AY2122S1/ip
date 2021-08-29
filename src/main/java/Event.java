public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.taskType = 'E';
        this.at = at;
    }

    @Override
    public String getTaskInfo() {
        String date = at.split(" ", 2)[1];
        return super.getTaskInfo() + "(at: " + date + ")";
    }
}
