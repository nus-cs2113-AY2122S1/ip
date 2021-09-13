package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTaskType() {
        return "E";
    }

    public String printOk() {
         return super.printOkStart() + "  [E][" + getStatusIcon() + "] "
                 + description + " (at: " + at + ")\n" + super.printOkEnd();
    }

    public String printTask() {
        return "." + "[" + getTaskType() + "]" + "["
                + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    public String printDone() {
        return horizontalLine + " Nice! I've marked this task as done:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + " (at: " + at + ")" + horizontalLine;
    }

    public String printDelete() {
        return horizontalLine + " Noted. I've removed this task:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    public String fileContent() {
        return "E [" + getStatusIcon() + "] " + description + " at: " + at;
    }

}
