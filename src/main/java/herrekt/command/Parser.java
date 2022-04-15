package herrekt.command;

import herrekt.exceptions.InvalidFindException;
import herrekt.exceptions.InvalidTaskException;
import herrekt.taskmanager.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String DATE_REGEX = "(\\d+-\\d+-\\d+)";
    private static final String DATE_AND_TIME_REGEX = "(\\d+-\\d+-\\d+\\s\\d+)";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns an integer which is the number of the task to be marked done.
     *
     * @param phrase A string starting with 'done'.
     * @return Task number
     * @throws NumberFormatException If the character after done is not an integer.
     * @throws StringIndexOutOfBoundsException If the task number is bigger than the number of current tasks.
     */
    public int parseDoneInputToInt(String phrase) throws NumberFormatException, StringIndexOutOfBoundsException {
        phrase = phrase.substring(5);
        return Integer.parseInt(phrase);
    }

    /**
     * Returns an integer which is the number of the task to be deleted.
     *
     * @param phrase A string starting with 'delete'.
     * @return Task number
     * @throws NumberFormatException If the character after delete is not an integer.
     * @throws StringIndexOutOfBoundsException If the task number is bigger than the number of current tasks.
     */
    public int parseDeleteInputToInt(String phrase) throws NumberFormatException, StringIndexOutOfBoundsException{
        phrase = phrase.substring(7);
        return Integer.parseInt(phrase);
    }

    /**
     * Returns a task of either Todo, Deadline, or Event.
     *
     * @param phrase A string starting with either 'todo', 'deadline', or 'event'.
     * @return A task of the specified child class Todo/Deadline/Event.
     * @throws InvalidTaskException If the input does not follow the correct format.
     */
    public Task parsePhraseToTask(String phrase) throws InvalidTaskException {
        if (phrase.startsWith("todo ")) {
            return new Todo(phrase.substring(5));
        } else if (phrase.startsWith("deadline ")) {
            phrase = phrase.substring(9);
            String[] taskAndTime = phrase.split(" /by ");
            return deadlineDateConverter(taskAndTime[0], taskAndTime[1]);
        } else if (phrase.startsWith("event ")) {
            phrase = phrase.substring(6);
            String[] taskAndTime = phrase.split(" /at ");
            return eventDateConverter(taskAndTime[0], taskAndTime[1]);
        } else {
            throw new InvalidTaskException("Invalid Task Format");
        }
    }

    /**
     * Returns the string to be searched for in a task's description
     *
     * @param phrase A string starting with 'search'.
     * @return Phrase to be searched
     */
    public String parseSearchInputToString(String phrase) throws InvalidFindException, StringIndexOutOfBoundsException {
        String parameter = phrase.substring(5).toLowerCase();
        if (parameter.equals("")) {
            throw new InvalidFindException("no search parameter after find");
        } else {
            return parameter;
        }
    }

    /**
     * Returns a deadline in with the date in LocalDate or LocalDateTime format if possible.
     *
     * @param description Description of the deadline.
     * @param date Date the deadline is due.
     * @return A deadline .
     */
    public static Task deadlineDateConverter(String description, String date) {
        Matcher dateMatcher = Pattern.compile(DATE_REGEX).matcher(date);
        Matcher dateAndTimeMatcher = Pattern.compile(DATE_AND_TIME_REGEX).matcher(date);
        String dateAsString;
        String dateAndTimeAsString;
        if (dateAndTimeMatcher.find()) {
            dateAndTimeAsString = dateAndTimeMatcher.group(1);
            LocalDateTime localDateTime = LocalDateTime.parse(dateAndTimeAsString, formatter);
            return new Deadline<>(description, localDateTime);
        } else if (dateMatcher.find()) {
            dateAsString = dateMatcher.group(1);
            LocalDate localDate = LocalDate.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE);
            return new Deadline<>(description, localDate);
        } else {
            return new Deadline<>(description, date);
        }
    }

    /**
     * Returns an event in with the date in LocalDate or LocalDateTime format if possible.
     *
     * @param description Description of the event.
     * @param date Date the event occurs.
     * @return An event.
     */
    public static Task eventDateConverter(String description, String date) {
        Matcher dateMatcher = Pattern.compile(DATE_REGEX).matcher(date);
        Matcher dateAndTimeMatcher = Pattern.compile(DATE_AND_TIME_REGEX).matcher(date);
        String dateAsString;
        String dateAndTimeAsString;
        if (dateAndTimeMatcher.find()) {
            dateAndTimeAsString = dateAndTimeMatcher.group(1);
            LocalDateTime localDateTime = LocalDateTime.parse(dateAndTimeAsString, formatter);
            return new Event<>(description, localDateTime);
        } else if (dateMatcher.find()) {
            dateAsString = dateMatcher.group(1);
            LocalDate localDate = LocalDate.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE);
            return new Event<>(description, localDate);
        } else {
            return new Event<>(description, date);
        }
    }
}
