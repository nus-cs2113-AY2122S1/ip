package herrekt.command;

import herrekt.exceptions.InvalidFindException;
import herrekt.exceptions.InvalidTaskException;
import herrekt.taskmanager.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String DATE_REGEX = "(\\d+-\\d+-\\d+)";

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
            return dateConverter(taskAndTime[0], taskAndTime[1]);
        } else if (phrase.startsWith("event ")) {
            phrase = phrase.substring(6);
            String[] taskAndTime = phrase.split(" /at ");
            return new Event(taskAndTime[0], taskAndTime[1]);
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
     * Returns a deadline in with the date in LocalDate format if possible.
     *
     * @param description Description of the deadline.
     * @param date Date the deadline is due.
     * @return A deadline .
     */
    public static Task dateConverter(String description, String date) {
        Matcher matcher = Pattern.compile(DATE_REGEX).matcher(date);
        String dateAsString;
        if (matcher.find()) {
            dateAsString = matcher.group(1);
            LocalDate localDate = LocalDate.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE);
            return new Deadline<>(description, localDate);
        } else {
            return new Deadline<>(description, date);
        }
    }
}
