package duke.type;

public class Event extends Task {
    final String atWhen;

    public Event(String description, String atWhen) {
        this.description = description;
        this.atWhen = atWhen;
    }

    public String getAtWhen() {
        return atWhen;
    }

    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return Character.toString(this.getType()) + Divider.PRINT_BLOCK
                + this.description.trim() + Divider.PRINT_BLOCK
                + this.atWhen.trim() + Divider.PRINT_BLOCK
                + this.isDone();
    }
}
