package kitty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class <code>Parser</code> takes in raw data of String type and extracts relevant information.
 */
public class Parser {

    public static final String EMPTY_TODO_ERROR = "Description of Todo cannot be empty!";
    public static final String EMPTY_DEADLINE_ERROR = "Description of Deadline cannot be empty!";
    public static final String EMPTY_EVENT_ERROR = "Description of Event cannot be empty!";

    // Todo
    /**
     * Returns whether the input given by user follows the proper format for adding a Task of type Todo.
     * @param line line is the String that the user inputs.
     * @return True if input given follows proper formatting. False otherwise.
     */
    public static boolean isTodoInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1);
        if (taskName.equals("todo")) {
            return false;
        } else {
            // Checks if taskName is empty
            return !taskName.isBlank();
        }
    }

    /**
     * Returns the Task Description of Todo task from user input.
     * @param line line is the String that the user inputs.
     * @return Todo Task Description
     * @throws KittyException If the Task Description is empty.
     */
    public static String getTodoTaskName(String line) throws KittyException {
        if (!isTodoInputValid(line)) {
            throw new KittyException(EMPTY_TODO_ERROR);
        } else {
            return line.substring(line.indexOf(" ") + 1);
        }
    }

    // Deadline
    /**
     * Returns whether the input given by user follows the proper format for adding a Task of type Deadline.
     * @param line line is the String that the user inputs.
     * @return True if input given follows proper formatting. False otherwise.
     */
    public static boolean isDeadlineInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        // Checks if taskName is empty
        return !taskName.isBlank();
    }

    /**
     * Returns the Task Description of Deadline task from user input.
     * @param line line is the String that the user inputs.
     * @return Deadline Task Description
     * @throws KittyException If the Task Description is empty.
     */
    public static String getDeadlineTaskName(String line) throws KittyException {
        if (!isDeadlineInputValid(line)) {
            throw new KittyException(EMPTY_DEADLINE_ERROR);
        } else {
            return line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
        }
    }

    /**
     * Returns the Deadline Date of Deadline task in String type from user input.
     * @param line line is the String that the user inputs.
     * @return Deadline Date in String
     */
    public static String getDeadlineDateString(String line) {
        return line.substring(line.indexOf("/by ") + 4);
    }

    /**
     * Returns whether the user input contains a deadline
     * @param line line is the String that the user inputs.
     * @return True if line contains a deadline. False otherwise.
     */
    public static boolean hasDeadline(String line) {
        return line.contains(" /by ");
    }

    // Event
    /**
     * Returns whether the input given by user follows the proper format for adding a Task of type Deadline.
     * @param line line is the String that the user inputs.
     * @return True if input given follows proper formatting. False otherwise.
     */
    public static boolean isEventInputValid(String line) {
        String taskName = line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
        // Checks if taskName is empty
        return !taskName.isBlank();
    }

    /**
     * Returns the Task Description of Event task from user input.
     * @param line line is the String that the user inputs.
     * @return Event Task Description
     * @throws KittyException If the Task Description is empty.
     */
    public static String getEventTaskName(String line) throws KittyException {
        if (!isEventInputValid(line)) {
            throw new KittyException(EMPTY_EVENT_ERROR);
        } else {
            return line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
        }
    }

    /**
     * Returns the Event Date of Deadline task in String type from user input.
     * @param line line is the String that the user inputs.
     * @return Event Date in String
     */
    public static String getEventDateString(String line) {
        return line.substring(line.indexOf("/at ") + 4);
    }

    /**
     * Returns whether the user input contains an event date.
     * @param line line is the String that the user inputs.
     * @return True if line contains an event date. False otherwise.
     */
    public static boolean hasEventDate(String line) {
        return line.contains(" /at ");
    }

    // Find
    /**
     * Returns the keyword to find from user input.
     * @param line line is the String that the user inputs.
     * @return Returns the keyword to find from the user input as a String.
     * @throws KittyException If keyword is empty.
     */
    public static String getKeyword(String line) throws KittyException {
        String keyword = line.substring(line.indexOf(" ") + 1);
        if (keyword.equals("find") || keyword.isBlank()) {
            throw new KittyException("Keyword cannot be empty!");
        } else {
            return keyword;
        }
    }

    // Date
    /**
     * Returns the date of type String to a date of type LocalDate.
     * @param dateString date in type String.
     * @return Returns date in type LocalDate.
     * @throws KittyException If dateString is of the wrong format.
     */
    public static LocalDate getTaskDate(String dateString) throws KittyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KittyException("Date formatting is incorrect! (Use format: DD/MM/YYYY)");
        }
    }
}
