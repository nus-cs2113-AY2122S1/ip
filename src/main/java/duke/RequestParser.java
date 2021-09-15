package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

public abstract class RequestParser {
    public static final int TIME_INFO_START_INDEX = 1;

    public static int getTaskIndex(String request) {
        return Integer.parseInt(request.split(" ")[1]) - 1;
    }

    public static Task getTask(String request) throws Exception {
        if (CommandType.isTodo(request)) {
            return buildTodo(request.trim());
        } else if (CommandType.isSpecialTask(request)) {
            return buildSpecialTask(request.trim());
        }
        throw new InvalidRequestException();
    }

    private static Task buildTodo(String request) throws Exception{
        try {
            String description = getDescription(request);
            return new Task(description);
        } catch (Exception ex) {
            throw new IncompleteInformationException(CommandType.TODO,"description");
        }
    }

    private static Task buildSpecialTask(String request) throws Exception {
        try {
            String time = getTime(request);
            String description = getDescription(request);

            return CommandType.isEvent(request) ? new Event(description, time) : new Deadline(description, time);
        } catch (Exception ex) {
            String errorType = ex instanceof EmptyTimeException ? "time" : "description";
            throw new IncompleteInformationException(CommandType.getCommand(request),errorType);
        }
    }

    private static String getTime(String request) throws EmptyTimeException{
        try {
            int timeIndex = request.indexOf("/");
            return request.substring(timeIndex + TIME_INFO_START_INDEX);
        } catch (Exception e) {
            throw new EmptyTimeException();
        }
    }

    private static String getDescription(String request) throws EmptyDescriptionException {
        try {
            int descriptionStartIndex = request.indexOf(" ");
            int descriptionEndIndex = CommandType.isSpecialTask(request) ? request.indexOf("/") : request.length();
            return request.substring(descriptionStartIndex,descriptionEndIndex).trim();
        } catch (Exception e) {
            throw new EmptyDescriptionException();
        }
    }


}
