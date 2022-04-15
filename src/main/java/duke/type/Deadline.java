package duke.type;

public class Deadline extends Task {
    private final String byWhen;

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
        return this.getType() + Divider.PRINT_BLOCK.getDivisor()
                + this.description.trim() + Divider.PRINT_BLOCK.getDivisor()
                + this.byWhen.trim() + Divider.PRINT_BLOCK.getDivisor()
                + this.isDone();
    }
}
