public class InputParser {

    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_PREFIX = "/at";

    private String[] trimInput(String[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = inputArray[i].trim();
        }
        return inputArray;
    }

    public String[] separateCommand(String input) {
        return trimInput(input.split(" ", 2));
    }

    public String[] separateDeadline(String input) {
        return trimInput(input.split(DEADLINE_PREFIX, 2));
    }

    public String[] separateEvent(String input) {
        return trimInput(input.split(EVENT_PREFIX, 2));
    }
}
