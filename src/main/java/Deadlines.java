public class Deadlines extends Task {
    private String time;
    static String ddlCmd = " /by ";

    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Deadlines parseDeadlines(String commandDescription) {
        return new Deadlines(commandDescription.split(ddlCmd)[0], commandDescription.split(ddlCmd)[1]);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", this.getClassIndicator(), this.getStatusIndicator(), this.getTaskDescription(), time);
    }
}
