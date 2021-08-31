package shikabot.parser;

import shikabot.command.AddCommand;
import shikabot.command.Command;
import shikabot.command.DeleteCommand;
import shikabot.command.DoneCommand;
import shikabot.command.ExitCommand;
import shikabot.command.FailedCommand;
import shikabot.command.FindCommand;
import shikabot.command.InvalidCommand;
import shikabot.command.ListCommand;
import shikabot.task.Task;

public class Parser {

    public static final int INVALID_DEADLINE_SYNTAX = 1;
    public static final int INVALID_EVENT_SYNTAX = 2;
    public static final int NUMBER_FORMAT_ERROR = 3;
    public static final int NEGATIVE_INDEX_ERROR = 4;
    public static final int INVALID_TASK = 5;

    public boolean isAddCommand(String text) {
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    public Command parseCommand(String text) {
        Command command;
        text = text.trim();
        if (text.equals("bye")) {
            command = new ExitCommand();
        } else if (text.equals("list")) {
            command = new ListCommand();
        } else if (text.startsWith("done")) {
            command = parseDoneCommand(text);
        } else if (text.startsWith("delete")) {
            command = parseDeleteCommand(text);
        } else if (text.startsWith("find")) {
            command = parseFindCommand(text);
        } else if (isAddCommand(text)) {
            command = parseAddCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    private Command parseDoneCommand(String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        int index;
        try {
            index = Integer.parseInt(str) - 1;
        } catch (NumberFormatException e) {
            return new FailedCommand(NUMBER_FORMAT_ERROR);
        }
        if (index < 0) {
            return new FailedCommand(NEGATIVE_INDEX_ERROR);
        }
        try {
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(INVALID_TASK);
        }
    }

    private Command parseDeleteCommand(String text) {
        String str = text.substring(text.indexOf("delete") + 6).trim();
        int index;
        try {
            index = Integer.parseInt(str) - 1;
        } catch (NumberFormatException e) {
            return new FailedCommand(NUMBER_FORMAT_ERROR);
        }
        if (index < 0) {
            return new FailedCommand(NEGATIVE_INDEX_ERROR);
        }
        try {
            return new DeleteCommand(index);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(INVALID_TASK);
        }
    }

    private Command parseFindCommand(String text) {
        String str = text.substring(text.indexOf("find") + 4).trim();
        return new FindCommand(str);
    }

    private Command parseAddCommand(String text) {
        Command command;
        if (text.startsWith("todo")) {
            command = parseAddTodoCommand(text);
        } else if (text.startsWith("deadline")) {
            try {
                command = parseAddDeadlineCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_DEADLINE_SYNTAX);
            }
        } else {
            try {
                command = parseAddEventCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_EVENT_SYNTAX);
            }
        }
        return command;
    }

    private Command parseAddTodoCommand(String text) {
        String name = text.substring(text.indexOf("todo") + 4).trim();
        return new AddCommand('T', name, "");
    }

    private Command parseAddDeadlineCommand(String text) throws Task.InvalidTaskException {
        if (!text.contains("/by")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        return new AddCommand('D', name, by);
    }

    private Command parseAddEventCommand(String text) throws Task.InvalidTaskException {
        if (!text.contains("/at")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        return new AddCommand('E', name, at);
    }

}
