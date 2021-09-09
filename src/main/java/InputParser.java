public abstract class InputParser {
    // constant flags
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DONE = "done";
    private static final String DEADLINE = "deadline";
    private static final String BY= "/by";
    private static final String AT = "/at";

    // isolate out task name from user input
    public static String getTaskName(String input) {
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/")).trim();
    }

    // get date for deadline / event from user input
    public static String getDate(String input) {
        return input.substring(input.indexOf("/") + 3).trim();
    }

    // get index of task in tasks array to mark as done
    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    // command parser handling all commands; returns enum of commands
    public static Commands getCommand(String input) {
        // checks if it contains list or bye
        if (input.equals(LIST)) {
            return Commands.LIST;
        }

        if (input.equals(BYE)) {
            return Commands.BYE;
        }

        if (input.startsWith(TODO)) {
            return Commands.TODO;
        }

        if (input.startsWith(DEADLINE)) {
            return Commands.DEADLINE;
        }

        if (input.startsWith(EVENT)) {
            return Commands.EVENT;
        }

        if (input.startsWith(DONE)) {
            return Commands.DONE;
        }

        return Commands.INVALID;
    }
}
