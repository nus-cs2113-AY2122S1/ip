package duke;

import duke.commands.*;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

public abstract class Parser {
    public static final int TIME_INFO_START_INDEX = 1;

    public static Command parse(String request) throws Exception {
        if (CommandType.isList(request)) {
            return new ListCommand(CommandType.LIST);
        } else if (CommandType.isDone(request)) {
            int taskIndex = getTaskIndex(request);
            return new DoneCommand(CommandType.DONE, taskIndex);
        } else if (CommandType.isDelete(request)) {
            int taskIndex = getTaskIndex(request);
            return new DeleteCommand(CommandType.DELETE, taskIndex);
        } else if (CommandType.isBye(request)) {
            return new ExitCommand(CommandType.BYE);
        } else {
            return getTask(request);
        }
    }

    public static int getTaskIndex(String request) throws Exception{
        try {
            return Integer.parseInt(request.split(" ")[1]) - 1;
        } catch (Exception e){
            throw new NumberFormatException("Sorry that's not a integer I can read!");
        }
    }

    public static Command getTask(String request) throws Exception {
        if (CommandType.isTodo(request)) {
            return buildTodo(request.trim());
        } else if (CommandType.isSpecialTask(request)) {
            return buildSpecialTask(request.trim());
        }
        throw new InvalidRequestException();
    }

    private static Command buildTodo(String request) throws Exception{
        try {
            String description = getDescription(request);
            Task task = new Task(description);
            return new AddCommand(CommandType.TODO,task);
        } catch (Exception ex) {
            throw new IncompleteInformationException(CommandType.TODO);
        }
    }

    private static Command buildSpecialTask(String request) throws Exception {
        try {
            String commandType = CommandType.isEvent(request) ? CommandType.EVENT : CommandType.DEADLINE;
            String description = getDescription(request);
            String time = getTime(request);
            Task task = CommandType.isEvent(request) ? new Event(description, time) : new Deadline(description, time);
            return new AddCommand(commandType, task);
        } catch (Exception ex) {
            throw new IncompleteInformationException(CommandType.getCommand(request));
        }
    }

    private static String getTime(String request) throws EmptyTimeException{
            int timeIndex = request.indexOf("/");
            if (timeIndex < 0) {
                throw new EmptyTimeException();
            }
            return request.substring(timeIndex + TIME_INFO_START_INDEX);
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
