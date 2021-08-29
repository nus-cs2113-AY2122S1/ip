public class Event extends Task {

    final private static String FLAG_TYPE = "[E]";

    private String startDate;

    public Event(String description, String startDate) {
        super(description);
        this.startDate = startDate.trim();
    }

    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    /**
     * Get all information of the task.
     *
     * @return String containing all information of the task.
     */
    @Override
    public String getTaskInfo() {
        return getDescription() + " (at: " + startDate + ")";
    }
}
