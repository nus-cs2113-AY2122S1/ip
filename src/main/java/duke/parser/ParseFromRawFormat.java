package duke.parser;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parse text from user input to Task format.
 */
public class ParseFromRawFormat extends Parser {
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String PREFIX_DEADLINE = "/by";
    private static final String PREFIX_EVENT = "/at";

    /**
     * Creates a ParseFromRawFormat object.
     *
     * @param input The raw user input.
     * @throws EmptyDescriptionException Indicates that the user did not give a task description.
     * @throws EmptyTimeFieldException Indicated that the user did not give a time field for event or deadline tasks.
     */
    public ParseFromRawFormat(String input) throws EmptyDescriptionException, EmptyTimeFieldException {
        handleRawInputs(input);
    }

    /**
     * Converts the input from raw user input to Task format.
     *
     * @param input The raw user input.
     * @throws EmptyDescriptionException Indicates that the user did not give a task description.
     * @throws EmptyTimeFieldException Indicated that the user did not give a time field for event or deadline tasks.
     */
    private void handleRawInputs(String input) throws EmptyDescriptionException, EmptyTimeFieldException {
        String[] inputSubstrings = input.strip().split(" ", 2);

        command = inputSubstrings[0];
        switch (command) {
        case COMMAND_FIND:
        case COMMAND_TODO:
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            description = inputSubstrings[1];
            break;
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            if (!inputSubstrings[1].contains(PREFIX_DEADLINE) && !inputSubstrings[1].contains(PREFIX_EVENT)) {
                throw new EmptyTimeFieldException();
            }
            description = inputSubstrings[1]
                    .substring(0, inputSubstrings[1].indexOf("/"))
                    .strip();
            if (description.isBlank()) {
                throw new EmptyDescriptionException();
            }
            timeField = inputSubstrings[1]
                    .substring((inputSubstrings[1].indexOf("/") + 3))
                    .strip();
            localDate = formatTimeField(timeField);
            break;
        case COMMAND_DONE:
        case COMMAND_DELETE:
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            taskIndex = Integer.parseInt(inputSubstrings[1].replaceAll("[^0-9]", ""));
            break;
        }
    }

    /**
     * Format the date field.
     *
     * @param timeField The date provided by the user.
     * @return The formatted date.
     * @throws DateTimeException Indicated that the user gave an invalid date format.
     */
    private LocalDate formatTimeField(String timeField) throws DateTimeException {
        LocalDate localDate = LocalDate.parse(timeField);
        if (localDate == null) {
            throw new DateTimeException("invalid time");
        }
        formattedTimeField = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return localDate;
    }
}
