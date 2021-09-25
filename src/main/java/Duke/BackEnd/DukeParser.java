package Duke.BackEnd;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeParseException;

import static Duke.UI.DukeConstants.*;

public class DukeParser {
    public static String getCommandType(String inWord) {
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0].trim().toLowerCase();
        return instructionType;
    }

    public static String[] parseDoneInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }

    public static String[] parseTodoInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static String[] parseEventInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static Event parseEventDescription (String[] commands) throws DateTimeParseException{
        try {
            String[] details = commands[1].split(EVENT_KEYWORD, 2);
            String description = details[0].trim();
            String at = details[1].trim();

            LocalDateTime eventAt = parseDateTime(at, FORMAT_DATE_TIME_INPUT);

            return new Event(description, eventAt);
        } catch (DateTimeParseException dtpException) {
            DukeException.dateTimeParseException(dtpException);
            return null;
        }
    }

    public static String[] parseDeadlineInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static Deadline parseDeadlineDescription (String[] commands) throws DateTimeParseException {
        try {
            String[] details = commands[1].split(DEADLINE_KEYWORD, 2);
            String description = details[0].trim();
            String by = details[1].trim();

            LocalDateTime deadlineBy = parseDateTime(by, FORMAT_DATE_TIME_INPUT);

            return new Deadline(description, deadlineBy);
        } catch (DateTimeParseException dtpException) {
            DukeException.dateTimeParseException(dtpException);
            return null;
        }
    }

    public static String[] parseDeleteInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }


    public static String parseFindInstruction (String inWord) throws DukeException {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2) {
            throw new DukeException();
        }
        return commands[1].trim();
    }

    public static LocalDateTime parseDateTime (String dateTime, String dateTimeFormat) throws DateTimeParseException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
        return LocalDateTime.parse(dateTime, format);
    }

    public static String dateTimetoStringConverter (LocalDateTime dateTime, String dateTimeFormat) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
        return dateTime.format(format);

    }
}
