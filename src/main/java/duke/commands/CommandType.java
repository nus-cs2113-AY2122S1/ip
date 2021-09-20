package duke.commands;

public abstract class CommandType {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String FIND = "find";

    public static boolean isBye(String request) {
        return request.trim().equals(BYE);
    }

    public static boolean isDelete(String request) {
        return request.trim().startsWith(DELETE);
    }

    public static boolean isList(String request) {
        return request.trim().equals(LIST);
    }

    public static boolean isDone(String request) {
        return request.startsWith(DONE);
    }

    public static boolean isTodo(String request) {
        return request.startsWith(TODO);
    }

    public static boolean isDeadline(String request) {
        return request.startsWith(DEADLINE);
    }

    public static boolean isEvent(String request) {
        return request.startsWith(EVENT);
    }

    public static boolean isSpecialTask(String request) {
        return isDeadline(request) || isEvent(request);
    }

    public static boolean isFind(String request) {
        return request.startsWith(FIND);
    }

    public static String getCommand(String request) {
        if (CommandType.isBye(request)) {
            return CommandType.BYE;
        } else if (CommandType.isDone(request)) {
            return CommandType.DONE;
        } else if (CommandType.isList(request)) {
            return CommandType.LIST;
        } else if (CommandType.isTodo(request)) {
            return CommandType.TODO;
        } else if (CommandType.isEvent(request)) {
            return CommandType.EVENT;
        } else if (CommandType.isDeadline(request)) {
            return CommandType.DEADLINE;
        } else if (CommandType.isDelete(request)) {
            return CommandType.DELETE;
        } else if (CommandType.isFind(request)) {
            return CommandType.FIND;
        }
        return null;
    }


}
