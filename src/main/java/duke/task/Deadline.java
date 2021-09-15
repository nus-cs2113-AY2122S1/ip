package duke.task;

/**
 * Represents a Deadline task and has a type of D. It is defined by a description String and a date String.
 */
public class Deadline extends Todo {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + date + ")";
    }

    @Override
    public String saveString() {
        return super.saveString() + " | " + date;
    }
}
