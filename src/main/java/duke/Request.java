package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

public abstract class Request {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    public static final int TIME_INFO_START_INDEX = 1;

    //assumes spelling is correct
    public static boolean isBye(String request) {
        return !request.trim().equals(BYE);
    }

    public static boolean isList(String request) {
        return request.trim().equals(LIST);
    }

    public static boolean isDone(String request) {
        return request.startsWith(DONE);
    }

    private static boolean isTodo(String request) {
        return request.startsWith(TODO);
    }

    private static boolean isDeadline(String request) {
        return request.startsWith(DEADLINE);
    }

    private static boolean isEvent(String request) {
        return request.startsWith(EVENT);
    }

    private static boolean isSpecialTask(String request) {
        return isDeadline(request) || isEvent(request);
    }

    public static int getTaskIndex(String request) {
        return Integer.parseInt(request.substring(Task.TASK_INDEX)) - 1;
    }

    public static Task getTask(String request) throws Exception {
        if (Request.isTodo(request)) {
            return buildTodo(request.trim());
        } else if (Request.isEvent(request)) {
            return buildEvent(request.trim());
        } else if (Request.isDeadline(request)){
            return buildDeadline(request.trim());
        }
        throw new InvalidRequestException();
    }

    private static Task buildTodo(String request) throws Exception{
        try {
            String description = getDescription(request);
            return new Task(description);
        } catch (Exception ex) {
            throw new IncompleteInformationException(TODO,"description");
        }
    }

    private static Task buildEvent(String request) throws Exception {
        try {
            String at = getTime(request);
            String description = getDescription(request);
            return new Event(description, at);
        } catch (Exception ex) {
            String errorType = ex instanceof EmptyTimeException ? "time" : "description";
            throw new IncompleteInformationException(EVENT,errorType);
        }
    }

    private static Task buildDeadline(String request) throws Exception {
        try {
            String by = getTime(request);
            String description = getDescription(request);
            return new Deadline(description, by);
        } catch (Exception ex) {
            String errorType = ex instanceof EmptyTimeException? "time" : "description";
            throw new IncompleteInformationException(EVENT,errorType);
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
            int descriptionEndIndex = Request.isSpecialTask(request) ? request.indexOf("/") : request.length();
            return request.substring(descriptionStartIndex,descriptionEndIndex).trim();
        } catch (Exception e) {
            throw new EmptyDescriptionException();
        }
    }


}
