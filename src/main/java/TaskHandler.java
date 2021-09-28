public class TaskHandler {

    protected static final String DONE_INDICATOR = "1";

    public static void markTaskAsDone(String line) throws DukeException, NumberFormatException{
        if (Parser.hasNoDoneIndex(line)) {
            throw new DukeException(DukeException.ERROR_NO_INDEX);
        }
        try {
            Integer.parseInt(Parser.parseDoneIndex(line));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(DukeException.ERROR_INDEX_NOT_NUMBER);
        }
        int taskIndex = Integer.parseInt(Parser.parseDoneIndex(line));
        if (taskIndex > TaskList.tasks.size() || taskIndex < 1) {
            throw new DukeException(DukeException.ERROR_INDEX_OUT_OF_BOUND);

        }
        TaskList.getTask(taskIndex - 1).markAsDone();
    }

    public static void addDeadline(String line) throws DukeException {
        if (Parser.hasNoBody(line) ||
                Parser.hasNoDescription(line, Parser.DEADLINE_DATE_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_DEADLINE_DESCRIPTION);
        }
        if (Parser.hasNoDeadlineDate(line)) {
            throw new DukeException(DukeException.ERROR_NO_DEADLINE_DATE);
        }
        TaskList.addDeadline(Parser.parseDeadlineDescription(line), Parser.parseDeadlineDate(line));
        TaskList.printAddedTask();
    }

    public static void addEvent(String line) throws DukeException {
        if (Parser.hasNoBody(line) ||
                Parser.hasNoDescription(line, Parser.EVENT_DATE_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_EVENT_DESCRIPTION);
        }
        if (Parser.hasNoEventDate(line)) {
            throw new DukeException(DukeException.ERROR_NO_EVENT_DATE);
        }
        TaskList.addEvent(Parser.parseEventDescription(line), Parser.parseEventDate(line));
        TaskList.printAddedTask();
    }

    public static void addTodo(String line) throws DukeException {
        if (Parser.hasNoTodoDescription(line)) {
            throw new DukeException(DukeException.ERROR_NO_TODO_DESCRIPTION);
        }
        TaskList.addTodo(Parser.parseBody(line));
        TaskList.printAddedTask();
    }

    public static void deleteTask(String line) throws DukeException, NumberFormatException{
        if (Parser.hasNoDoneIndex(line)) {
            throw new DukeException(DukeException.ERROR_NO_INDEX);
        }
        try {
            Integer.parseInt(Parser.parseDoneIndex(line));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(DukeException.ERROR_INDEX_NOT_NUMBER);
        }
        int taskIndex = Integer.parseInt(Parser.parseDoneIndex(line));
        if (taskIndex > TaskList.tasks.size() || taskIndex < 1) {
            throw new DukeException(DukeException.ERROR_INDEX_OUT_OF_BOUND);

        }
        TaskList.printDeletedTask(taskIndex - 1);
        TaskList.removeTask(taskIndex - 1);
    }

    public static void handleWrongCommand() throws DukeException{
        throw new DukeException(DukeException.ERROR_INVALID_COMMAND);
    }
}
