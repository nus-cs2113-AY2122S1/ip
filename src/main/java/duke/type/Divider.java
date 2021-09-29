package duke.type;

/**
 * Divider to differentiate deadlines, events, todo in user input
 */
public enum Divider {
    E("/at"), D("/by"), PRINT_BLOCK("|");

    protected String divisor;
    Divider(String divisor) {
        this.divisor = divisor;
    }

    public String getDivisor() {
        return divisor;
    }
}
