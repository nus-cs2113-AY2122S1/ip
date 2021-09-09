public class Events extends Task {

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Task parse(String taskInfo) throws DukeException {
        if (taskInfo.isBlank()) {
            // the string is empty or contains only white space
            throw new DukeException("The description of a event cannot be empty :-(");
        }

        int i = taskInfo.indexOf(" /at ");
        if ( i == -1) {
            throw new DukeException("There should be a \"/at\" in the event :-(");
        }

        String description = taskInfo.substring(0, i);
        String at = taskInfo.substring(i + 5);
        return new Events(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
