package duke.parser;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseFromRawFormat extends Parser {
    public ParseFromRawFormat(String input) throws EmptyDescriptionException, EmptyTimeFieldException {
        handleRawInputs(input);
    }

    private void handleRawInputs(String input) throws EmptyDescriptionException, EmptyTimeFieldException {
        String[] inputSubstrings = input.strip().split(" ", 2);

        command = inputSubstrings[0];
        switch (command) {
        case "find":
        case "todo":
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            description = inputSubstrings[1];
            break;
        case "deadline":
        case "event":
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            if (!inputSubstrings[1].contains("/by") && !inputSubstrings[1].contains("/at")) {
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
        case "done":
        case "delete":
            if (inputSubstrings.length < 2) {
                throw new EmptyDescriptionException();
            }
            taskIndex = Integer.parseInt(inputSubstrings[1].replaceAll("[^0-9]", ""));
            break;
        }
    }

    private LocalDate formatTimeField(String timeField) throws DateTimeException {
        LocalDate localDate = LocalDate.parse(timeField);
        if (localDate == null) {
            throw new DateTimeException("invalid time");
        }
        formattedTimeField = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return localDate;
    }
}
