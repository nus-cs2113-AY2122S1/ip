public class Events extends Task {
    private String time;
    static String ddlCmd = " /at ";

    public Events(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Events parseEvent(String commandDescription) {
        return new Events(commandDescription.split(ddlCmd)[0], commandDescription.split(ddlCmd)[1]);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (at: %s)", this.getClassIndicator(), this.getStatusIndicator(), this.getTaskDescription(), time);
    }
}
