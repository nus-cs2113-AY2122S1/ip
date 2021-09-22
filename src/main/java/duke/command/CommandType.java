package duke.command;

public enum CommandType {
    DEADLINE,
    DELETE,
    DONE,
    EVENT,
    EXIT,
    INVALID,
    LIST,
    TODO;

    /**
     * Determines the correct command type from the input parameter.
     *
     * @param param The first parameter of a user response.
     * @return The command type of the input parameter.
     */
    public static CommandType of(String param) {
        for (CommandType commandType : CommandType.values()) {
            if (param.equalsIgnoreCase(commandType.toString())) {
                return commandType;
            }
        }
        return INVALID;
    }
}
