public class Todo extends Task {
    private static String symbol = "T";

    public Todo(String name) {
        super(name);
    }

    public static String getSymbol() {
        return symbol;
    }
}
