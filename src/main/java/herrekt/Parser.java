package herrekt;

import herrekt.exceptions.InvalidInputException;
import herrekt.taskmanager.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String DATE_AND_TIME_REGEX = "(\\d+-\\d+-\\d+\\s\\d+)";
    private static final String DATE_REGEX = "(\\d+-\\d+-\\d+)";


    public int parseDoneInputToInt(String phrase) throws NumberFormatException, StringIndexOutOfBoundsException {
        phrase = phrase.substring(5);
        return Integer.parseInt(phrase);
    }

    public int parseDeleteInputToInt(String phrase) throws NumberFormatException, StringIndexOutOfBoundsException{
        phrase = phrase.substring(7);
        return Integer.parseInt(phrase);
    }

    public Task parsePhraseToTask(String phrase) throws InvalidInputException {
        if (phrase.contains("todo")) {
            return new Todo(phrase.substring(5));
        } else if (phrase.contains("deadline")) {
            phrase = phrase.substring(9);
            String[] taskAndTime = phrase.split(" /by ");
            Object date = dateConverter(taskAndTime[1]);
            return new Deadline<>(taskAndTime[0], date);
        } else if (phrase.contains("event")) {
            phrase = phrase.substring(6);
            String[] taskAndTime = phrase.split(" /at ");
            return new Event(taskAndTime[0], taskAndTime[1]);
        } else {
            throw new InvalidInputException("Invalid Task Format");
        }
    }

    public String parseSearchInputToString(String phrase) {
        return phrase.substring(5);
    }

    public static boolean containsDate(String phrase) {
        Matcher matcher = Pattern.compile(DATE_REGEX).matcher(phrase);
        return matcher.find();
    }

    public static Object dateConverter(String phrase) {
        Matcher matcher = Pattern.compile(DATE_REGEX).matcher(phrase);
        String dateAsString = phrase;
        if (matcher.find()) {
            dateAsString = matcher.group(1);
            return LocalDate.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            return dateAsString;
        }
    }

    public boolean containsDateAndTime(String phrase) {
        Matcher matcher = Pattern.compile(DATE_AND_TIME_REGEX).matcher(phrase);
        return matcher.find();
    }
}
