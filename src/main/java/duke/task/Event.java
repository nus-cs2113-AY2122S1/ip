package duke.task;

public class Event extends Task {
    private String startAndEndTime;
    private String type;

    public Event(String description, String startAndEndTime) {
        super(description);
        this.startAndEndTime = startAndEndTime;
        this.type = "E";
    }

    @Override
    public String getStartAndEndTime() {
        return startAndEndTime;
    }

    @Override
    public String getType() {
        return type;
    }

}
