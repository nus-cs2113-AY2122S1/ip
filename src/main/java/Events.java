public class Events extends Task {

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Task parse(String taskInfo){
        int i = taskInfo.indexOf(" /at ");
        String description = taskInfo.substring(0, i);
        String at = taskInfo.substring(i + 5);
        return new Events(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
