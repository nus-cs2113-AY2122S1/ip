package herrekt.taskmanager;

public class Event extends Task implements Timetable {
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
        String spacer = " / ";
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "E" + spacer + done + spacer + this.description + spacer + this.date;
    }

    @Override
    public String getDescription() {
        return super.description + " (at: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}
