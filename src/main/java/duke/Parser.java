package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    protected String command;
    protected String description;
    protected String timeField;
    protected String formattedTimeField;
    protected LocalDate localDate;
    protected int taskIndex;
    protected boolean done = false;
    protected String fileFormat;

    public Parser(String input, int type) throws EmptyTimeFieldException, EmptyDescriptionException {
        if (type == 1) {
            handleRawInputs(input);
        } else {
            handleFileInputs(input);
        }
    }

    public Parser(Task task) {
        if (task instanceof Todo) {
            fileFormat = task.getTaskIcon().concat(" | ")
                    .concat(task.getStatusIcon()).concat(" | ")
                    .concat(task.getDescription()).concat(System.lineSeparator());
        } else {
            fileFormat = task.getTaskIcon().concat(" | ")
                    .concat(task.getStatusIcon()).concat(" | ")
                    .concat(task.getDescription()).concat(" | ")
                    .concat(task.getDue()).concat(System.lineSeparator());

        }
    }

    private void handleFileInputs(String input) {
        String[] inputSubstrings = input.split("\\|");

        String type = inputSubstrings[0].strip();
        switch (type) {
        case "T":
            command = "todo";
            break;
        case "D":
            command = "deadline";
            formattedTimeField = inputSubstrings[3].strip();
            break;
        case "E":
            command = "event";
            formattedTimeField = inputSubstrings[3].strip();
            break;
        }
        done = inputSubstrings[1].strip().equals("X");
        description = inputSubstrings[2].strip();
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

    public String getFormattedTimeField() {
        return formattedTimeField;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isDone() {
        return done;
    }

    public String getFileFormat() {
       return fileFormat;
    }
}
