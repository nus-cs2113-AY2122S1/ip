package duke.parse;

import duke.DukeException;
import duke.commands.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String INVALID_COMMAND = "invalid command";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final int COMMAND_INDEX = 0;

    public static Command parse(String line, TaskList taskList) throws DukeException {
        String[] words = line.split(" ");

        switch (words[COMMAND_INDEX]) {
        case (COMMAND_HELP) :
            return parseHelpCommand(line);
        case (COMMAND_LIST):
            return parseListCommand(line);
            // Fallthrough
        case (COMMAND_TODO):
            return parseTodoCommand(line);
        case (COMMAND_DEADLINE):
            return parseDeadlineCommand(line, words);
        case (COMMAND_EVENT):
            return parseEventCommand(line, words);
        case (COMMAND_DONE):
            return parseDoneCommand(line, words, taskList);
        case (COMMAND_DELETE):
            return parseDeleteCommand(line, words, taskList);
        case (COMMAND_BYE):
            return parseByeCommand(line);
        default:
            throw new DukeException(INVALID_COMMAND);
        }
    }

    public static Command parseHelpCommand(String line) throws DukeException {
        final String HELP_ERROR = "help does not take in additional parameters";

        if (!line.equals(COMMAND_HELP)) {
            throw new DukeException(HELP_ERROR);
        }

        return new HelpCommand();
    }

    public static Command parseListCommand(String line) throws DukeException {
        final String LIST_ERROR = "list does not take in additional parameters";

        if (!line.equals(COMMAND_LIST)) {
            throw new DukeException(LIST_ERROR);
        }

        return new ListCommand();
    }

    public static Command parseTodoCommand(String line) throws DukeException {
        final String TODO_ERROR = "todo description missing";
        final int START_INDEX = 5;

        if (line.equals(COMMAND_TODO)) {
            throw new DukeException(TODO_ERROR);
        }

        return new AddCommand(new Todo(line.substring(START_INDEX).trim()));
    }

    public static Command parseDeadlineCommand(String line, String[] words) throws DukeException {
        final String BY_DELIMITER = "/by";
        final String DEADLINE_ERROR_1 = "specify task and date/time";
        final String DEADLINE_ERROR_2 = "deadlines need to contain \"/by\"";
        final String DEADLINE_ERROR_3 = "deadline description missing";
        final String DEADLINE_ERROR_4 = "date/time missing";
        final String DEADLINE_ERROR_5 = "too many parameters for date/time";
        final String DEADLINE_ERROR_6 = "missing date or time";
        final String DEADLINE_ERROR_7 = "incorrect date or time format";
        final int MAX_LENGTH = 2;
        final int DESCRIPTION_INDEX_ERROR = 1;
        final int DESCRIPTION_INDEX = 0;
        final int BY_INDEX = 1;
        final int DATE_INDEX = 0;
        final int TIME_INDEX = 1;
        final int START_INDEX = 9;

        LocalDate date;
        String newDate;
        LocalTime time;
        String newTime;

        if (line.equals(COMMAND_DEADLINE)) {
            throw new DukeException(DEADLINE_ERROR_1);
        }

        if (!line.contains(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_2);
        }

        if (words[DESCRIPTION_INDEX_ERROR].equals(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_3);
        }

        if (line.endsWith(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_4);
        }

        String[] deadlineInputs = line.substring(START_INDEX).split(BY_DELIMITER);
        String[] dateAndTime = deadlineInputs[BY_INDEX].trim().split(" ");

        if (dateAndTime.length > MAX_LENGTH) {
            throw new DukeException(DEADLINE_ERROR_5);
        }

        if (dateAndTime.length != MAX_LENGTH) {
            throw new DukeException(DEADLINE_ERROR_6);
        }

        try {
            date = LocalDate.parse(dateAndTime[DATE_INDEX].trim());
            newDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            time = LocalTime.parse(dateAndTime[TIME_INDEX].trim());
            newTime = time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new DukeException(DEADLINE_ERROR_7);
        }

        return new AddCommand(new Deadline(deadlineInputs[DESCRIPTION_INDEX].trim(),
                newDate,
                newTime));
    }

    public static Command parseEventCommand(String line, String[] words) throws DukeException {
        final String AT_DELIMITER = "/at";
        final String EVENT_ERROR_1 = "specify task and date/time";
        final String EVENT_ERROR_2 = "events need to contain \"/at\"";
        final String EVENT_ERROR_3 = "event description missing";
        final String EVENT_ERROR_4 = "date/time missing";
        final String EVENT_ERROR_5 = "too many parameters for date/time";
        final String EVENT_ERROR_6 = "missing date or time";
        final String EVENT_ERROR_7 = "incorrect date or time format";
        final int MAX_LENGTH = 3;
        final int DESCRIPTION_INDEX_ERROR = 1;
        final int DESCRIPTION_INDEX = 0;
        final int AT_INDEX = 1;
        final int DATE_INDEX = 0;
        final int TIME_INDEX_1 = 1;
        final int TIME_INDEX_2 = 2;
        final int START_INDEX = 6;

        LocalDate date;
        String newDate;
        LocalTime time1;
        String newTime1;
        LocalTime time2;
        String newTime2;

        if (line.equals(COMMAND_EVENT)) {
            throw new DukeException(EVENT_ERROR_1);
        }

        if (!line.contains(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_2);
        }

        if (words[DESCRIPTION_INDEX_ERROR].equals(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_3);
        }

        if (line.endsWith(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_4);
        }

        String[] eventInputs = line.substring(START_INDEX).split(AT_DELIMITER);
        String[] dateAndTime = eventInputs[AT_INDEX].trim().split(" ");

        if (dateAndTime.length > MAX_LENGTH) {
            throw new DukeException(EVENT_ERROR_5);
        }

        if (dateAndTime.length < MAX_LENGTH) {
            throw new DukeException(EVENT_ERROR_6);
        }

        try {
            date = LocalDate.parse(dateAndTime[DATE_INDEX].trim());
            newDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            time1 = LocalTime.parse(dateAndTime[TIME_INDEX_1].trim());
            newTime1 = time1.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            time2 = LocalTime.parse(dateAndTime[TIME_INDEX_2].trim());
            newTime2 = time2.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new DukeException(EVENT_ERROR_7);
        }

        return new AddCommand(new Event(eventInputs[DESCRIPTION_INDEX].trim(),
                newDate,
                newTime1,
                newTime2));
    }

    public static Command parseDoneCommand(String line, String[] words, TaskList taskList) throws DukeException {
        final String DONE_ERROR_1 = "missing index of task done";
        final String DONE_ERROR_2 = "extra parameters found";
        final String DONE_ERROR_3 = "non-integer value for index of task done";
        final String DONE_ERROR_4 = "index out of list";
        final int MAX_LENGTH = 2;
        final int INDEX_DONE = 1;

        int index;

        if (line.equals(COMMAND_DONE)) {
            throw new DukeException(DONE_ERROR_1);
        }

        if (words.length > MAX_LENGTH) {
            throw new DukeException(DONE_ERROR_2);
        }

        try {
            index = Integer.parseInt(words[INDEX_DONE]);
        } catch (NumberFormatException e) {
            throw new DukeException(DONE_ERROR_3);
        }

        boolean isOutOfList = (index > taskList.getListSize()) || (index < 0);

        if (isOutOfList) {
            throw new DukeException(DONE_ERROR_4);
        }

        return new DoneCommand(index);
    }

    public static Command parseDeleteCommand(String line, String[] words, TaskList taskList) throws DukeException {
        final String DELETE_ERROR_1 = "missing index of task to delete";
        final String DELETE_ERROR_2 = "extra parameters found";
        final String DELETE_ERROR_3 = "non-integer value for index of task to delete";
        final String DELETE_ERROR_4 = "index of out list";
        final int MAX_LENGTH = 2;
        final int INDEX_DELETE = 1;
        int index;

        if (line.equals(COMMAND_DELETE)) {
            throw new DukeException(DELETE_ERROR_1);
        }

        if (words.length > MAX_LENGTH) {
            throw new DukeException(DELETE_ERROR_2);
        }

        try {
            index = Integer.parseInt(words[INDEX_DELETE]);
        } catch (NumberFormatException e) {
            throw new DukeException(DELETE_ERROR_3);
        }

        boolean isOutOfList = (index > taskList.getListSize()) || (index < 0);

        if (isOutOfList) {
            throw new DukeException(DELETE_ERROR_4);
        }

        return new DeleteCommand(index);
    }

    public static Command parseByeCommand(String line) throws DukeException {
        final String BYE_ERROR = "bye does not take in additional parameters";

        if (!line.equals(COMMAND_BYE)) {
            throw new DukeException(BYE_ERROR);
        }

        return new ExitCommand();
    }
}
