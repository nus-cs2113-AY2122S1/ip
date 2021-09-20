package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;

public class Parser {
    private final String command;
    private String description;
    private String timeField;
    private int taskIndex;

    public Parser(String input) throws EmptyTimeFieldException, EmptyDescriptionException {
        String[] inputSubstrings = input.strip().split(" ", 2);
        command = inputSubstrings[0];


        switch (command) {
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

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeField() {
        return timeField;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
