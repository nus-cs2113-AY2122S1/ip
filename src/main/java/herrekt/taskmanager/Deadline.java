package herrekt.taskmanager;

public class Deadline extends Task implements Timetable {
    protected String date;

    public Deadline(String description, String date) {
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
        return "D" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.date;
    }

    public String getDescription() {
        return super.description + " (by: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
