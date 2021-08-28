public class ToDo extends Task {

    private final String symbol = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + symbol + "]" + super.toString();
    }
}
