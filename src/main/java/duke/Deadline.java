package duke;

public class Deadline extends Task{

    protected final static char LETTER = 'D';
    protected String date;

    public Deadline(String description, String date) {
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
        return "(by: " + date + ")";
    }

}
