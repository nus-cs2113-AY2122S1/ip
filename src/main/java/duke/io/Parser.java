package duke.io;

/**
 * Parses the user input to recognise commands, statuses and command details.
 */
public class Parser {
    /** Error messages */
    public final String COMMAND_UNKNOWN_ERROR_MESSAGE = "I cannot comprehend, my liege.";
    private final String COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE = "My liege, there is no description!";
    private final String COMMAND_MISSING_BY_ERROR_MESSAGE = "By when, my liege?";
    private final String COMMAND_MISSING_AT_ERROR_MESSAGE = "When or where is this event, my liege?";
    private final String COMMAND_MISSING_VALUE_ERROR_MESSAGE = "My liege, there is no value!";

    /**
     * Reads the user input and returns the type of Command it is.
     * @param line the line of user input
     * @return the type of Command
     */
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

    /** Returns true if input is the exit command "bye". */
    public boolean inputIsBye(String line) {
        return line.equalsIgnoreCase("bye");
    }

    /** Returns true if command is "todo". */
    public boolean inputIsTodo(String line) {
        return line.toLowerCase().startsWith("todo");
    }

    /** Returns true if command is "find". */
    public boolean inputIsFind(String line) {
        return line.toLowerCase().startsWith("find");
    }

    /** Returns true if command is "delete". */
    public boolean inputIsDelete(String line) {
        return line.toLowerCase().startsWith("delete");
    }

    /** Returns true if command is "deadline". */
    public boolean inputIsDeadline(String line) {
        return line.toLowerCase().startsWith("deadline");
    }

    /** Returns true if the deadline command contains by. */
    public boolean deadlineContainsBy(String line) {
        return line.toLowerCase().contains("/by");
    }

    /** Returns true if command is "event". */
    public boolean inputIsEvent(String line) {
        return line.toLowerCase().startsWith("event");
    }

    /** Returns true if the event command is contains at. */
    public boolean eventContainsAt(String line) {
        return line.toLowerCase().contains("/at");
    }

    /** Returns true if command is "done", or do task. */
    public boolean inputIsDone(String line) {
        return line.toLowerCase().startsWith("done");
    }

    /** Returns true if command is "clear". */
    public boolean inputIsClear(String line) {
        return line.toLowerCase().startsWith("clear");
    }

    /** Returns true if command is "list". */
    public boolean inputIsList(String line) {
        return line.equalsIgnoreCase("list");
    }

    /**
     * Parses a todo command to extract its description.
     * @param line the line containing the command
     * @return the description of the Todo
     * @throws IllegalArgumentException if there is no description
     */
    public String parseTodo(String line) throws IllegalArgumentException {
        if (line.length() <= 5) {
            throw new IllegalArgumentException(COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE);
        }
        String description = line.substring(5).trim();
        return description;
    }

    /**
     * Parses a deadline command to extract its description and by.
     * @param line the line containing the command
     * @return a String[] containing the description and by
     * @throws IllegalArgumentException if there is no description or by
     */
    public String[] parseDeadline(String line) throws IllegalArgumentException {
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

    /**
     * Parses an event command to extract its description and at.
     * @param line the line containing the command
     * @return a String[] containing the description and at
     * @throws IllegalArgumentException if there is no description or at
     */
    public String[] parseEvent(String line) throws IllegalArgumentException {
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
