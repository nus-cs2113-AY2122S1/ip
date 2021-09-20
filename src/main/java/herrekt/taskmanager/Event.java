package herrekt.taskmanager;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "E" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.date;
    }

    public String getDescription() {
        return super.description + " (at: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}
