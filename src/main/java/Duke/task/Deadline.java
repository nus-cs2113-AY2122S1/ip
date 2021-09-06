package Duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        //get the preposition used
        int spaceIndex = by.indexOf(' ');
        String preposition = by.substring(0, spaceIndex);
        String dueDate = by.substring(spaceIndex+1);

        //output message
        return "[D]" + super.getStatusIcon() + super.toString() + " (" + preposition + ": " + dueDate + ")";    }
}
