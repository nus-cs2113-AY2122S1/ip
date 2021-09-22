package Type;

public class Deadline extends Task{
    private String byWhen;
    public Deadline(String description, String byWhen) {
        this.description = description;
        this.byWhen = byWhen;
    }

    public String getByWhen() {
        return byWhen;
    }

    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return Character.toString(this.getType()) + '|'
                + this.description.trim() + '|'
                + this.byWhen.trim() + '|'
                + this.isDone();
    }
}
