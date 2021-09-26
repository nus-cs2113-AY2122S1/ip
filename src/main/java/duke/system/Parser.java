package duke.system;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.DoneTaskCommand;
import duke.command.ExitCommand;
import duke.command.ListAllCommand;
import duke.exception.*;

import java.time.LocalDate;
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

    private String getTodoTime(String fullCommand) {
        return fullCommand.substring("todo ".length());
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
                LocalDate deadlineDate = getDeadlineDate(fullCommand);
                String deadlineTime = getDeadlineTime(fullCommand);
                return new AddDeadlineCommand(deadlineName, deadlineDate, deadlineTime);
            }
        case "event":
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
        default:
            throw new InvalidInput();
        }
    }
}
