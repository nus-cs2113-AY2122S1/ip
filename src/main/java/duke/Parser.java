package duke;

import duke.command.*;
import duke.exception.*;

import java.time.LocalDate;

public class Parser {
    public Parser() {
    }

    public Command parseCommand(String input) throws DukeException {
        if (input.equals("bye") == true) {
            return new ExitCommand();
        } else if (input.equals("list") == true) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else {
            return new InvalidCommand();
        }
    }

    public Command parseTodoCommand(String input) throws DukeException {
        String description;
        try {
            description = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("missing description");
        }

        return new AddTodoCommand(description, input);
    }

    public Command parseDeadlineCommand(String input) throws DukeException {
        int indexOfBy = input.indexOf("/by");
        String byString;
        LocalDate by;
        String description;

        if (indexOfBy == -1) {
            throw new DukeException("invalid command");
        }

        try {
            description = input.substring(9, indexOfBy - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("missing description");
        }

        try {
            byString = input.substring(indexOfBy + 4);
            by = LocalDate.parse(byString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("missing timing");
        }

        return new AddDeadlineCommand(description, by, input);
    }

    public Command parseEventCommand(String input) throws DukeException {
        int indexOfAt = input.indexOf("/at");
        String atString;
        LocalDate at;
        String description;

        if (indexOfAt == -1) {
            throw new DukeException("invalid command");
        }

        try {
            description = input.substring(6, indexOfAt - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("missing description");
        }

        try {
            atString = input.substring(indexOfAt + 4);
            at = LocalDate.parse(atString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("missing timing");
        }

        return new AddEventCommand(description, at, input);
    }

    public Command parseDoneCommand(String input) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input.substring(5)) - 1;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("no task selected");
        }

        return new DoneCommand(taskIndex);
    }

    public Command parseDeleteCommand(String input) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input.substring(7)) - 1;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("no task selected");
        }

        return new DeleteCommand(taskIndex);
    }
}
