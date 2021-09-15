package duke;

public abstract class CommandType {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static boolean isNotBye(String request) {
        return !request.trim().equals(CommandType.BYE);
    }

    public static boolean isBye(String request) {
        return request.trim().equals(CommandType.BYE);
    }

    public static boolean isDelete(String request) {
        return request.trim().startsWith(CommandType.DELETE);
    }

    public static boolean isList(String request) {
        return request.trim().equals(CommandType.LIST);
    }

    public static boolean isDone(String request) {
        return request.startsWith(CommandType.DONE);
    }

    public static boolean isTodo(String request) {
        return request.startsWith(CommandType.TODO);
    }

    public static boolean isDeadline(String request) {
        return request.startsWith(CommandType.DEADLINE);
    }

    public static boolean isEvent(String request) {
        return request.startsWith(CommandType.EVENT);
    }

    public static boolean isSpecialTask(String request) {
        return isDeadline(request) || isEvent(request);
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
        }
        return null;
    }
}
