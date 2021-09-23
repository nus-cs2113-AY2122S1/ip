package duke;

import command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Parser {

    private static final int DESCRIPTION_PARAMETERS = 2;
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;
    private static final int START_DATE = 0;
    private static final int END_DATE = 1;
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_SORT = "sort";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String INPUT_DELIMITER = " ";
    public static final String EVENT_DELIMITER = " /by ";
    public static final String DEADLINE_DELIMITER = " /at ";
    private static final int FIRST_TIME_INPUT = 0;
    private static final int SECOND_TIME_INPUT = 1;

    /**
     * Execute commands based on the command word given
     *
     * @param words String input by user.
     */
    public static Command checkCommand(String[] words, String input) throws
            UnknownCommandException, DukeException {
        String[] descriptionInput = parseInput(words, input);
        switch (words[FIRST_ARRAY_PARAMETER]) {
        case COMMAND_BYE:
            return new CommandBye();
        case COMMAND_LIST:
            return new CommandList();
        case COMMAND_DONE:
            return new CommandDone(words,descriptionInput);
        case COMMAND_DELETE:
            return new CommandDelete(words, descriptionInput);
        case COMMAND_TODO:
            return new CommandTodo(words[FIRST_ARRAY_PARAMETER],descriptionInput);
        case COMMAND_DEADLINE:
            return new CommandDeadline(words[FIRST_ARRAY_PARAMETER],descriptionInput);
        case COMMAND_EVENT:
            return new CommandEvent(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        case COMMAND_SORT:
            return new CommandSort(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        case COMMAND_FIND:
            return new CommandFind(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        case COMMAND_HELP:
            return new CommandHelp();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Throws different exception based on the command type
     * @param command is the first word of user input
     * @param descriptionInput is the string that followed after the command
     * @throws DukeException if the subsequent words after the command is empty
     */
    public static void checkDescription(String command, String[] descriptionInput) throws DukeException {
        if (descriptionInput[FIRST_ARRAY_PARAMETER].equals("")) {
            switch (command) {
            case COMMAND_TODO:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_TODO);
            case COMMAND_DEADLINE:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_DEADLINE);
            case COMMAND_EVENT:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_EVENT);
            default:
                throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_COMMAND);
            }
        }
    }

    /**
     * Throws input if necessary commands requires additional input
     * @param descriptionInput, line of text after the command word
     * @throws DukeException when there are missing parameters given the valid command word
     */
    public static void checkTimeframe(String[] descriptionInput) throws DukeException {
        boolean emptyInput = descriptionInput[SECOND_ARRAY_PARAMETER].equals("");
        if (emptyInput) {
            throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_KEYWORD);
        }
    }

    /**
     * Takes description and split based on command accordingly
     * @param words array of words which include the command word
     * @param input the input given by the user
     * @return parsed output
     */
    private static String[] parseInput(String[] words, String input) {
        String[] output = new String[DESCRIPTION_PARAMETERS];
        output[0] = "";
        output[1] = "";
        if (words.length < 2) {
            return output;
        }
        String[] newWord = input.split(INPUT_DELIMITER, 2);
        if (words[0].equals(COMMAND_DEADLINE) && newWord[1].contains(EVENT_DELIMITER)) {
            output = newWord[1].split(EVENT_DELIMITER, 2);
        } else if (words[0].equals(COMMAND_EVENT) && newWord[1].contains(DEADLINE_DELIMITER)) {
            output = newWord[1].split(DEADLINE_DELIMITER, 2);
        } else {
            output[0] = newWord[1];
        }
        return output;
    }

    /**
     * Change datetime string into datetime object for deadline
     * if parser is YYYY-MM-DD, time will automatically be 0000
     * else if parser YYYY-MM-DD HHMM, time will follow based on user input
     * @param parser is the datetime part of input
     * @return datetime object
     * @throws DukeException if input format of time is incorrect or invalid numbers for time
     */
    public static LocalDateTime parseDeadlineDate (String parser) throws DukeException {
        String[] stringDate = parser.split(INPUT_DELIMITER);
        LocalDateTime date;
        DateTimeFormatter formatter;
        if (stringDate.length == 1) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            date = LocalDateTime.parse(parser + " 0000", formatter);
        } else if (stringDate.length == 2) {
            // store both date and time
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            date = LocalDateTime.parse(parser, formatter);
        } else {
            //this will need to throw an exception
            throw new DukeException(ErrorMessage.EXCEPTION_INCORRECT_TIME_FORMAT);
        }
        return date;
    }

    /**
     * Change datetime string into datetime object for event
     * if parser is YYYY-MM-DD to YYYY-MM-DD, time will be set to 0000
     * if parser is YYYY-MM-DD HHMM to HHMM, the second time will follow the date given,
     * assuming that the event start and end on the same day
     * if parser is YYYY-MM-DD to YYYY-MM-DD HHMM, the time will be based on user input
     *
     * Types of input for event time accepted: (date = YYYY-MM-DD, time = HHMM)
     * date to date
     * date to time
     * date to date time
     * date time to time
     * date time to dead time
     * @param parser is the datetime part of input
     * @return datetime object
     * @throws DukeException if input format of time is incorrect
     */
    public static LocalDateTime[] parseEventDate (String parser) throws DukeException {
        String[] stringDates = parser.split(INPUT_DELIMITER);
        LocalDateTime[] dates;
        if (stringDates.length == 3 && stringDates[1].equals("to")
                && stringDates[2].length() == 4) {
            //date to time
            String startDate = stringDates[0] + " 0000";
            String endDate = stringDates[0] + ' ' + stringDates[2];
            dates = formatTime(startDate, endDate);
        } else if (stringDates.length == 3 && stringDates[1].equals("to")) {
            //date to date
            String startDate = stringDates[0] + " 0000";
            String endDate = stringDates[2] + " 0000";
            dates = formatTime(startDate, endDate);
        } else if (stringDates.length == 4 && stringDates[2].equals("to")) {
            //date time to date
            String startDate = stringDates[0] + ' ' + stringDates[1];
            String endDate = stringDates[0] + ' ' + stringDates[3];
            dates = formatTime(startDate, endDate);
        }else if (stringDates.length == 4 && stringDates[1].equals("to")) {
            //date to date time
            String startDate = stringDates[0] + ' ' + "0000";
            String endDate = stringDates[2] + ' ' + stringDates[3];
            dates = formatTime(startDate, endDate);
        }else if (stringDates.length == 5 && stringDates[2].equals("to")) {
            //date time to date time
            String startDate = stringDates[0] + ' ' + stringDates[1];
            String endDate = stringDates[3] + ' ' + stringDates[4];
            dates = formatTime(startDate, endDate);
        } else {
            throw new DukeException(ErrorMessage.EXCEPTION_INCORRECT_TIME_FORMAT);
        }
        return arrangeEventDate(dates);
    }

    /**
     * Convert String of start_date and end_date to datetime
     * @param startDate string for start_date
     * @param endDate string for end_date
     * @return an array of 2 datetime for start_date and end_date
     */
    private static LocalDateTime[] formatTime(String startDate, String endDate) {
        DateTimeFormatter formatter;
        LocalDateTime[] dates = new LocalDateTime[2];
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        dates[START_DATE] = LocalDateTime.parse(startDate, formatter);
        dates[END_DATE] = LocalDateTime.parse(endDate, formatter);
        return dates;
    }

    /**
     * Swap the dates around if end_date is before start_date
     * @param date an array of start_date, end_date
     * @return swapped dates if above condition is met. else dates remains in the same order
     */
    private static LocalDateTime[] arrangeEventDate(LocalDateTime[] date){
        if (date[FIRST_TIME_INPUT].isAfter(date[SECOND_TIME_INPUT])){
            LocalDateTime temp = date[FIRST_TIME_INPUT];
            date[FIRST_TIME_INPUT] = date[SECOND_TIME_INPUT];
            date[SECOND_TIME_INPUT] = temp;
        }
        return date;
    }

}

