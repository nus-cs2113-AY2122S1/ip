public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        //return the status of deadline task
        return " [D]" + super.toString() + " (by: " + date + ")";
    }
}
