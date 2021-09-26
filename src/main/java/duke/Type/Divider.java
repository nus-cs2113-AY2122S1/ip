package duke.Type;

/**
 * Divider to differentiate deadlines, events, todo in user input
 */
public enum Divider {
    E("/at"), D("/by");

    protected String divisor;
    Divider(String divisor) {
        this.divisor = divisor;
    }

    public String getDivisor() {
        return divisor;
    }
}
