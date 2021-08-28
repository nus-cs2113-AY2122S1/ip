public class Deadline extends Task implements Timetable {
    private String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
