public class ToDo extends Task {

    private static final String SYMBOL = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }
}
