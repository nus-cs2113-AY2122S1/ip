public class InputParser {

    private static final String DEADLINE_PREFIX = " /by ";
    private static final String EVENT_PREFIX = " /at ";

    public String[] separateCommand(String input) {
        return input.split(" ", 2);
    }

    public String[] separateDeadline(String input) {
        return input.split(DEADLINE_PREFIX, 2);
    }

    public String[] separateEvent(String input) {
        return input.split(EVENT_PREFIX, 2);
    }
}
