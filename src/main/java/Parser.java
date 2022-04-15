public class Parser {

    /**
     * This method parses the input given by user and calls method based on the appropriate commands.
     *
     * @param list the TaskList
     * @param line the input typed in by the user
     * @return a boolean value to determine when the program terminates
     */
    public static boolean parse(TaskList list, String line) {
        if (line.equals("list")) {
            Command.handleListCommand(list);
        } else if (line.startsWith("done")) {
            Command.handleDoneCommand(list, line);
        } else if (line.startsWith("todo ")) {
            Command.handleTodoCommand(list, line);
        } else if (line.startsWith("deadline ")) {
            Command.handleDeadlineCommand(list, line);
        } else if (line.startsWith("event")) {
            Command.handleEventCommand(list, line);
        } else if (line.startsWith("delete")) {
            Command.handleDeleteCommand(list, line);
        } else if (line.equals("bye")) {
            return false;
        } else if (line.startsWith("find")) {
            Command.handleFindCommand(list, line);
        } else {
            Command.handleInvalidCommand();
        }
        return true;
    }

}
