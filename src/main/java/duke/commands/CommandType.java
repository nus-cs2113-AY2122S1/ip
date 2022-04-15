package duke.commands;

/**
 * This class is used to identify the command that users input into the command line
 */
public abstract class CommandType {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String FIND = "find";

    /**
     * Checks if the input command is the exit command
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isBye(String request) {
        return request.trim().equals(BYE);
    }

    /**
     * Checks if the input command is the delete command
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isDelete(String request) {
        return request.trim().startsWith(DELETE);
    }

    /**
     * Checks if the input command is the list command
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isList(String request) {
        return request.trim().equals(LIST);
    }

    /**
     * Checks if the input command is the done command
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isDone(String request) {
        return request.startsWith(DONE);
    }

    /**
     * Checks if the input command is the add command for a todo task type
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isTodo(String request) {
        return request.startsWith(TODO);
    }

    /**
     * Checks if the input command is the add command for a deadline task type
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isDeadline(String request) {
        return request.startsWith(DEADLINE);
    }

    /**
     * Checks if the input command is the add command for a event task type
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isEvent(String request) {
        return request.startsWith(EVENT);
    }

    /**
     * Checks if the input command is the add command for either a deadline or event task type
     *
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isSpecialTask(String request) {
        return isDeadline(request) || isEvent(request);
    }

    /**
     * Checks if the input command is the find command
     * @param request it is the input string from the user
     * @return true if the string matches the command else return false
     */
    public static boolean isFind(String request) {
        return request.startsWith(FIND);
    }

    /**
     * Returns the string associated to the command type
     *
     * @param request it is the input string from the user
     * @return the string that corresponds to the command
     */
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
