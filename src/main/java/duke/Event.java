package duke;

public class Event extends Task{

    protected final static char LETTER = 'E';
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return description;
    }

    public char getLetter() {
        return LETTER;
    }

    public String getDate() {
        return "(at: " + date + ")";
    }

    public String getDateOnly() { return date; }
}
