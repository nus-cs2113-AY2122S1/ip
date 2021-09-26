package duke.system;

import duke.command.*;
import duke.exception.*;

public class Parser {

    private String getCommandType(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private int getCurrentTaskIndex(String fullCommand) {
        return Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    }

    private String getDeadlineName(String fullCommand) {
        return fullCommand.substring("deadline ".length(), fullCommand.indexOf("/by "));
    }


    private String getDeadlineTime(String fullCommand) {
        return fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
    }

    private String getEventName(String fullCommand) {
        return fullCommand.substring("event ".length(), fullCommand.indexOf("/at "));
    }

    private boolean isCompleteEventCommand(String fullCommand) {
        if (!fullCommand.contains("/at ")) {
            return false;
        }
        return true;
    }

    private boolean isCompleteTodoCommand(String fullCommand) {
        int lengthOfHeader = "todo ".length();
        if (fullCommand.length() <= lengthOfHeader) {
            return false;
        }
        return true;
    }

    private boolean isCompleteListCommand(String fullCommand) {
        int lengthOfHeader = "list".length();
        if (fullCommand.length() != lengthOfHeader) {
            return false;
        }
        return true;
    }

    private boolean isCompleteDoneCommand(String fullCommand) {
        int lengthOfHeader = "done ".length();
        int requiredArguments = 2;
        String[] arguments = fullCommand.split(" ");
        if (fullCommand.length() <= lengthOfHeader) {
            return false;
        }
        if (arguments.length > requiredArguments) {
            return false;
        }
        return true;
    }

    private boolean isCompleteDeleteCommand(String fullCommand) {
        int lengthOfHeader = "delete ".length();
        int requiredArguments = 2;
        String[] arguments = fullCommand.split(" ");
        if (fullCommand.length() <= lengthOfHeader) {
            return false;
        }
        if (arguments.length > requiredArguments) {
            return false;
        }
        return true;
    }


    private boolean isCompleteDeadlineCommand(String fullCommand) {
        if (!fullCommand.contains("/by ")) {
            return false;
        }
        return true;
    }

    private String getEventTime(String fullCommand) {
        return fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
    }

    private boolean isCompleteFindCommand(String fullCommand) {
        if (fullCommand.length() <= "find ".length()) {
            return false;
        }
        return true;
    }
    private String getKeywords(String fullCommand) {
        return fullCommand.substring("find ".length());
    }

    private String getTodoTime(String fullCommand) {
        return fullCommand.substring("todo ".length());
    }

    public Command parse(String fullCommand) throws DukeException {
        String commandType = getCommandType(fullCommand);
        switch (commandType) {
        case "list":
            if (!isCompleteListCommand(fullCommand)) {
                throw new WrongFormat();
            }
            return new ListAllCommand();
        case "done":
            if (!isCompleteDoneCommand(fullCommand)) {
                throw new WrongFormat();
            }
            try {
                int currentTaskIndex = getCurrentTaskIndex(fullCommand);
                return new DoneTaskCommand(currentTaskIndex);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new WrongFormat();
            }
        case "deadline":
            if (!isCompleteDeadlineCommand(fullCommand)) {
                throw new WrongFormat();
            } else {
                String deadlineName = getDeadlineName(fullCommand);
                String deadlineTime = getDeadlineTime(fullCommand);
                return new AddDeadlineCommand(deadlineName, deadlineTime);
            }
        case "event":
            if (!isCompleteEventCommand(fullCommand)) {
                throw new WrongFormat();
            } else {
                String eventName = getEventName(fullCommand);
                String eventTime = getEventTime(fullCommand);
                return new AddEventCommand(eventName, eventTime);
            }
        case "todo":
            if (!isCompleteTodoCommand(fullCommand)) {
                throw new WrongFormat();
            } else {
                String todoName = getTodoTime(fullCommand);
                return new AddTodoCommand(todoName);
            }
        case "delete":
            if (!isCompleteDeleteCommand(fullCommand)) {
                throw new WrongFormat();
            }
            try {
                int selectedTaskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new DeleteTaskCommand(selectedTaskIndex);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new WrongFormat();
            }
        case "exit":
            return new ExitCommand();
        case "find":
            if (!isCompleteFindCommand(fullCommand)) {
                throw new WrongFormat();
            } else {
                String keywords = getKeywords(fullCommand);
                return new FindTasksCommand(keywords);
            }
        default:
            throw new InvalidInput();
        }
    }
}
