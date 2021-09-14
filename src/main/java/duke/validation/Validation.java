package duke.validation;

public class Validation {

    public static boolean isValidTodo(String[] inputWords) {
        return (inputWords.length > 1);
    }

    public static boolean isValidDeadline(String input, String[] inputWords) {
        return (input.contains("/by") && !inputWords[1].equals("/by"));
    }

    public static boolean isValidEndDate(String input) {
        return (!input.endsWith("/by"));
    }

    public static boolean isValidEvent(String input, String[] inputWords) {
        return (input.contains("/at") && !inputWords[1].equals("/at"));
    }

    public static boolean isValidDuration(String input) {
        return (!input.endsWith("/at"));
    }

    public static boolean isValidCrossOff(String[] inputWords) {
        return (inputWords.length == 2);
    }

    public static boolean isValidDeleteTask(String[] inputWords) {
        return (inputWords.length == 2);
    }

    public static boolean isValidTaskIndex(int taskIndex, int taskCount) {
        return (taskIndex >= 0) && (taskIndex < taskCount);
    }

}
