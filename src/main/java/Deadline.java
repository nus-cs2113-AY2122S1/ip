public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + type + "][X] " + description + " (" + date + ")";
        } else {
            return "[" + type + "][ ] " + description + " (" + date + ")";
        }
    }
}
