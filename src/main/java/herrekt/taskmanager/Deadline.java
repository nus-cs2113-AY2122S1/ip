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
        String spacer = " / ";
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "D" + spacer + done + spacer + this.description + spacer + this.date;
    }

    @Override
    public String getDescription() {
        return super.description + " (by: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
