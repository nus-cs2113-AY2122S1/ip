package duke.system;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.exception.WrongFormat;
import duke.exception.UnknownError;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A system component of the system in charge of interpreting user inputs
 */
public class Parser {

    /**
     * Interprets user inputs and identify commands given by the user
     * @param fullCommand raw user input
     * @return commands to be executed
     * @throws DukeException if there are problems with the input
     */
    public Command parse(String fullCommand) throws DukeException {
        try {
            String commandType = getCommandType(fullCommand);
            switch (commandType) {
            case "list":
                return createListAllCommand(fullCommand);
            case "done":
                return createDoneTaskCommand(fullCommand);
            case "deadline":
                return createAddDeadlineCommand(fullCommand);
            case "event":
                return createAddEventCommand(fullCommand);
            case "todo":
                return createAddTodoCommand(fullCommand);
            case "delete":
                return createDeleteTaskCommand(fullCommand);
            case "exit":
                return createExitCommand();
            case "find":
                return createFindTasksCommand(fullCommand);
            default:
                throw new InvalidInput();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormat();
        } catch (Exception e) {
            throw new UnknownError();
        }
    }

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
        String rawDeadlineDateTime = fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
        String deadlineTime = rawDeadlineDateTime.split(" ")[1];
        return deadlineTime;
    }

    private LocalDate getDeadlineDate(String fullCommand) {
        String rawDeadlineDateTime = fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
        String rawDeadlineDate = rawDeadlineDateTime.split(" ")[0];
        LocalDate deadlineDate = LocalDate.parse(rawDeadlineDate);
        return deadlineDate;
    }

    private String getEventName(String fullCommand) {
        return fullCommand.substring("event ".length(), fullCommand.indexOf("/at "));
    }

    private LocalDate[] getEventDates(String fullCommand) {
        String rawEventDatesTimes = fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
        String rawEventStartDateTime = rawEventDatesTimes.split(" to ")[0];
        String rawEventEndDateTime = rawEventDatesTimes.split(" to ")[1];
        String rawEventStartDate = rawEventStartDateTime.split(" ")[0];
        String rawEventEndDate = rawEventEndDateTime.split(" ")[0];
        LocalDate eventStartDate = LocalDate.parse(rawEventStartDate);
        LocalDate eventEndDate = LocalDate.parse(rawEventEndDate);
        return new LocalDate[] {eventStartDate, eventEndDate};
    }

    private String[] getEventTimes(String fullCommand) {
        String rawEventDatesTimes = fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
        String rawEventStartDateTime = rawEventDatesTimes.split(" to ")[0];
        String rawEventEndDateTime = rawEventDatesTimes.split(" to ")[1];
        String eventStartTime = rawEventStartDateTime.split(" ")[1];
        String eventEndTime = rawEventEndDateTime.split(" ")[1];
        return new String[] {eventStartTime, eventEndTime};
    }

    private String getTodoName(String fullCommand) {
        return fullCommand.substring("todo ".length());
    }

    private boolean isCompleteEventCommand(String fullCommand) {
        if (!fullCommand.contains("/at ")) {
            return false;
        }
        try {
            int hoursStartIndex = 0;
            int hoursEndIndex = 2;
            int minutesStartIndex = 2;
            LocalDate[] EventDates = getEventDates(fullCommand);
            String[] EventTimes = getEventTimes(fullCommand);
            for (String i: EventTimes) {
                int hours = Integer.parseInt(i.substring(hoursStartIndex, hoursEndIndex));
                int minutes = Integer.parseInt(i.substring(minutesStartIndex));
                if (hours > 23 || hours < 0) {
                    return false;
                }
                if (minutes > 59 || minutes < 0) {
                    return false;
                }
            }
            return true;
        } catch (DateTimeParseException | NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
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
        try {
            int hoursStartIndex = 0;
            int hoursEndIndex = 2;
            int minutesStartIndex = 2;
            LocalDate DeadlineDate = getDeadlineDate(fullCommand);
            String DeadlineTime = getDeadlineTime(fullCommand);
            int hours = Integer.parseInt(DeadlineTime.substring(hoursStartIndex, hoursEndIndex));
            int minutes = Integer.parseInt(DeadlineTime.substring(minutesStartIndex));
            if (hours > 23 || hours < 0) {
                return false;
            }
            if (minutes > 59 || minutes < 0) {
                return false;
            }
            return true;
        } catch (DateTimeParseException | NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
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

    private Command createListAllCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteListCommand(fullCommand)) {
            throw new WrongFormat();
        }
        return new ListAllCommand();
    }

    private Command createDoneTaskCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteDoneCommand(fullCommand)) {
            throw new WrongFormat();
        }
        try {
            int currentTaskIndex = getCurrentTaskIndex(fullCommand);
            return new DoneTaskCommand(currentTaskIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new WrongFormat();
        }
    }

    private Command createAddDeadlineCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteDeadlineCommand(fullCommand)) {
            throw new WrongFormat();
        } else {
            String deadlineName = getDeadlineName(fullCommand);
            LocalDate deadlineDate = getDeadlineDate(fullCommand);
            String deadlineTime = getDeadlineTime(fullCommand);
            return new AddDeadlineCommand(deadlineName, deadlineDate, deadlineTime);
        }
    }

    private Command createAddEventCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteEventCommand(fullCommand)) {
            throw new WrongFormat();
        } else {
            String eventName = getEventName(fullCommand);
            LocalDate eventStartDate = getEventDates(fullCommand)[0];
            String eventStartTime = getEventTimes(fullCommand)[0];
            LocalDate eventEndDate = getEventDates(fullCommand)[1];
            String eventEndTime = getEventTimes(fullCommand)[1];
            return new AddEventCommand(eventName, eventStartDate, eventStartTime,
                    eventEndDate, eventEndTime);
        }
    }

    private Command createAddTodoCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteTodoCommand(fullCommand)) {
            throw new WrongFormat();
        } else {
            String todoName = getTodoName(fullCommand);
            return new AddTodoCommand(todoName);
        }
    }

    private Command createDeleteTaskCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteDeleteCommand(fullCommand)) {
            throw new WrongFormat();
        }
        try {
            int selectedTaskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteTaskCommand(selectedTaskIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new WrongFormat();
        }
    }

    private Command createExitCommand() {
        return new ExitCommand();
    }

    private Command createFindTasksCommand(String fullCommand) throws WrongFormat {
        if (!isCompleteFindCommand(fullCommand)) {
            throw new WrongFormat();
        } else {
            String keywords = getKeywords(fullCommand);
            return new FindTasksCommand(keywords);
        }
    }
}
