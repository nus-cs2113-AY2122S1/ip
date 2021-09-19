package kitty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static final String EMPTY_TODO_ERROR = "Description of Todo cannot be empty!";
    public static final String EMPTY_DEADLINE_ERROR = "Description of Deadline cannot be empty!";
    public static final String EMPTY_EVENT_ERROR = "Description of Event cannot be empty!";

    // Todo
    public static boolean isTodoInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1);
        if (taskName.equals("todo")) {
            return false;
        } else {
            // Checks if taskName is empty
            return !taskName.isBlank();
        }
    }
    public static String getTodoTaskName(String line) throws KittyException {
        if (!isTodoInputValid(line)) {
            throw new KittyException(EMPTY_TODO_ERROR);
        } else {
            return line.substring(line.indexOf(" ") + 1);
        }
    }

    // Deadline
    public static boolean isDeadlineInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        // Checks if taskName is empty
        return !taskName.isBlank();
    }
    public static String getDeadlineTaskName(String line) throws KittyException {
        if (!isDeadlineInputValid(line)) {
            throw new KittyException(EMPTY_DEADLINE_ERROR);
        } else {
            return line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        }
    }
    public static String getDeadlineDateString(String line) {
        return line.substring(line.indexOf("/by ") + 4);
    }
    public static boolean hasDeadline(String line) {
        return line.contains(" /by ");
    }

    // Event
    public static boolean isEventInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
        // Checks if taskName is empty
        return !taskName.isBlank();
    }
    public static String getEventTaskName(String line) throws KittyException {
        if (!isEventInputValid(line)) {
            throw new KittyException(EMPTY_EVENT_ERROR);
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

    // Date
    public static LocalDate getTaskDate(String dateString) throws KittyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KittyException("Date formatting is incorrect! (Use format: DD/MM/YYYY)");
        }
    }
}
