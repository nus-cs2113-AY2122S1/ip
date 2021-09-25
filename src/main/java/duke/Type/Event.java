package duke.Type;

public class Event extends Task {
    String atWhen;
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
        return Character.toString(this.getType()) + '|'
                + this.description.trim() + '|'
                + this.atWhen.trim() + '|'
                + this.isDone();
    }
}
