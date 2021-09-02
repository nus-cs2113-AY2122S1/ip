public class Todo extends Task {
    private static String SYMBOL = "T";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }
}
