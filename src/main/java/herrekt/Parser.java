package herrekt;

import herrekt.taskmanager.Deadline;
import herrekt.taskmanager.Event;
import herrekt.taskmanager.Task;
import herrekt.taskmanager.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String DATE_AND_TIME_REGEX = "(\\d+-\\d+-\\d+\\s\\d+)";
    private static final String DATE_REGEX = "(\\d+-\\d+-\\d+)";


    public int parseDoneInputToInt(String phrase) {
        phrase = phrase.replace("done ", "");
        return Integer.parseInt(phrase);
    }

    public int parseDeleteInputToInt(String phrase) {
        phrase = phrase.replace("delete ", "");
        return Integer.parseInt(phrase);
    }

    public Task parsePhraseToTask(String phrase) {
        Task task = null;
        if (phrase.contains("todo")) {
            task = new Todo(phrase.replace("todo ", ""));
        } else if (phrase.contains("deadline")) {
            phrase = phrase.replace("deadline ", "");
            String[] taskAndTime = phrase.split(" /by ");
            Object date = dateConverter(taskAndTime[1]);
            task = new Deadline<>(taskAndTime[0], date);
        } else if (phrase.contains("event")) {
            phrase = phrase.replace("event ", "");
            String[] taskAndTime = phrase.split(" /at ");
            task = new Event(taskAndTime[0], taskAndTime[1]);
        }
        return task;
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
        return Pattern.compile(DATE_AND_TIME_REGEX).matcher(phrase).find();
    }

}
