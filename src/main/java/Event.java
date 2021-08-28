public class Event extends Task {


    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getAdditionalDescription() {
        return at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
