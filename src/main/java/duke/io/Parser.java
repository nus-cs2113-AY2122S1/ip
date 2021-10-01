package duke.io;

public class Parser {
    /** Error messages */
    private final String COMMAND_UNKNOWN_ERROR_MESSAGE = "I cannot comprehend, my liege.";
    private final String COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE = "My liege, there is no description!";
    private final String COMMAND_MISSING_BY_ERROR_MESSAGE = "By when, my liege?";
    private final String COMMAND_MISSING_AT_ERROR_MESSAGE = "When or where is this event, my liege?";
    private final String COMMAND_MISSING_VALUE_ERROR_MESSAGE = "My liege, there is no value!";

    public Command returnCommand(String line) {
        Command command;
        if (inputIsBye(line)) {
            command = Command.EXIT;
        } else if (inputIsList(line)) {
            command = Command.LIST;
        } else if (inputIsFind(line)) {
            command = Command.FIND;
        } else if (inputIsClear(line)) {
            command = Command.CLEAR;
        } else if (inputIsTodo(line)) {
            command = Command.ADD_TODO;
        } else if (inputIsDeadline(line)) {
            command = Command.ADD_DEADLINE;
        } else if (inputIsEvent(line)) {
            command = Command.ADD_EVENT;
        } else if (inputIsDone(line)) {
            command = Command.DO_TASK;
        } else if (inputIsDelete(line)) {
            command = Command.DELETE;
        } else {
            command = Command.INVALID;
        }
        return command;
    }

    public boolean inputIsBye(String line) {
        return line.equalsIgnoreCase("bye");
    }

    public boolean inputIsTodo(String line) {
        return line.toLowerCase().startsWith("todo");
    }

    public boolean inputIsFind(String line) {
        return line.toLowerCase().startsWith("find");
    }

    public boolean inputIsDelete(String line) {
        return line.toLowerCase().startsWith("delete");
    }

    public boolean inputIsDeadline(String line) {
        return line.toLowerCase().startsWith("deadline");
    }

    public boolean deadlineContainsBy(String line) {
        return line.toLowerCase().contains("/by");
    }

    public boolean inputIsEvent(String line) {
        return line.toLowerCase().startsWith("event");
    }

    public boolean eventContainsAt(String line) {
        return line.toLowerCase().contains("/at");
    }

    public boolean inputIsDone(String line) {
        return line.toLowerCase().startsWith("done");
    }

    public boolean inputIsClear(String line) {
        return line.toLowerCase().startsWith("clear");
    }

    public boolean inputIsList(String line) {
        return line.equalsIgnoreCase("list");
    }

    public String parseTodo(String line) throws IllegalArgumentException {
        if (line.length() <= 5) {
            throw new IllegalArgumentException(COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE);
        }
        String description = line.substring(5).trim();
        return description;
    }

    public String[] parseDeadline(String line) {
        if (!deadlineContainsBy(line)) {
            throw new IllegalArgumentException(COMMAND_MISSING_BY_ERROR_MESSAGE);
        }

        int dividerPosition = line.toLowerCase().indexOf("/by");
        String description = line.substring(9, dividerPosition).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException(COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE);
        }

        String by = line.substring(dividerPosition + 3).trim();
        if (by.isEmpty()) {
            throw new IllegalArgumentException(COMMAND_MISSING_BY_ERROR_MESSAGE);
        }

        String[] taskComponents = new String[2];
        taskComponents[0] = description;
        taskComponents[1] = by;
        return taskComponents;
    }

    public String[] parseEvent(String line) {
        if (!eventContainsAt(line)) {
            throw new IllegalArgumentException(COMMAND_MISSING_AT_ERROR_MESSAGE);
        }

        int dividerPosition = line.toLowerCase().indexOf("/at");
        String description = line.substring(6, dividerPosition).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException(COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE);
        }

        String at = line.substring(dividerPosition + 3).trim();
        if (at.isEmpty()) {
            throw new IllegalArgumentException(COMMAND_MISSING_AT_ERROR_MESSAGE);
        }

        String[] taskComponents = new String[2];
        taskComponents[0] = description;
        taskComponents[1] = at;
        return taskComponents;
    }

    /**
     * Parses a do task command to extract the ID of the Task.
     * @param line the line containing the command
     * @return the Task's ID
     * @throws IllegalArgumentException if there is no ID found
     */
    public int parseDoTask(String line) throws IllegalArgumentException {
        if (line.length() <= 5) {
            throw new IllegalArgumentException(COMMAND_MISSING_VALUE_ERROR_MESSAGE);
        }
        String inputNumStr = line.toLowerCase().replace("done", "").trim();
        int inputNum = Integer.parseInt(inputNumStr);
        return inputNum;
    }

    /**
     * Parses a delete task command to extract the ID of the Task.
     * @param line the line containing the command
     * @return the Task's ID
     * @throws IllegalArgumentException if there is no ID found
     */
    public int parseDeleteTask(String line) throws IllegalArgumentException {
        if (line.length() <= 7) {
            throw new IllegalArgumentException(COMMAND_MISSING_VALUE_ERROR_MESSAGE);
        }
        String inputNumStr = line.toLowerCase().replace("delete", "").trim();
        int inputNum = Integer.parseInt(inputNumStr);
        return inputNum;
    }

    /**
     * Parses a find command to extract the search term.
     * @param line the line containing the command
     * @return the search term
     * @throws IllegalArgumentException if there is no search term found
     */
    public String parseFind(String line) throws IllegalArgumentException {
        if (line.length() <= 5) {
            throw new IllegalArgumentException(COMMAND_MISSING_VALUE_ERROR_MESSAGE);
        }
        String search = line.toLowerCase().replace("find", "").trim();
        return search;
    }
}
