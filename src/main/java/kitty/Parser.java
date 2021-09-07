package kitty;

public class Parser {
    // Todo
    public static boolean isTodoInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1);
        if (taskName.equals("todo")) {
            return false;
        } else {
            // Remove all white-spaces
            taskName = taskName.replaceAll("\\s", "");
            return !taskName.equals("");
        }
    }
    public static String getTodoTaskName(String line) throws KittyException {
        if (!isTodoInputValid(line)) {
            throw new KittyException("Description of Todo cannot be empty!");
        } else {
            return line.substring(line.indexOf(" ") + 1);
        }
    }

    // Deadline
    public static boolean isDeadlineInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        // Remove all white-spaces
        taskName = taskName.replaceAll("\\s", "");
        return !taskName.equals("");
    }
    public static String getDeadlineTaskName(String line) throws KittyException {
        if (!isDeadlineInputValid(line)) {
            throw new KittyException("Description of Deadline cannot be empty!");
        } else {
            return line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        }
    }
    public static String getDeadlineDate(String line) {
        return line.substring(line.indexOf("/by ") + 4);
    }
    public static boolean hasDeadline(String line) {
        return line.contains(" /by ");
    }

    // Event
    public static boolean isEventInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
        // Remove all white-spaces
        taskName = taskName.replaceAll("\\s", "");
        return !taskName.equals("");
    }
    public static String getEventTaskName(String line) throws KittyException {
        if (!isEventInputValid(line)) {
            throw new KittyException("Description of Event cannot be empty!");
        } else {
            return line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
        }
    }
    public static String getEventDate(String line) {
        return line.substring(line.indexOf("/at ") + 4);
    }
    public static boolean hasEventDate(String line) {
        return line.contains(" /at ");
    }
}
