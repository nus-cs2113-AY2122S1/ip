package kitty;

public class Parser {
    // Task
    public static String getTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }

    // Deadline
    public static String getDeadlineTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
    }
    public static String getDeadlineDate(String line) {
        return line.substring(line.indexOf("/by ") + 4);
    }
    public static boolean hasDeadline(String line) {
        return line.contains("/by");
    }

    // Event
    public static String getEventTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
    }
    public static String getEventDate(String line) {
        return line.substring(line.indexOf("/at ") + 4);
    }
    public static boolean hasEventDate(String line) {
        return line.contains("/at");
    }
}
