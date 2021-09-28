public class TaskHandler {

    protected static final String DONE_INDICATOR = "1";
    protected static final String DEADLINE_SEPARATOR = "/by";
    protected static final String EVENT_SEPARATOR = "/on";

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
                Parser.hasNoDescription(line, DEADLINE_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_DEADLINE_DESCRIPTION);
        }
        if (Parser.hasNoDate(line, DEADLINE_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_DEADLINE_DATE);
        }
        TaskList.addDeadline(Parser.parseDescription(line, DEADLINE_SEPARATOR), Parser.parseDate(line, DEADLINE_SEPARATOR));
        Ui.printAddedTask();
    }

    public static void addEvent(String line) throws DukeException {
        if (Parser.hasNoBody(line) ||
                Parser.hasNoDescription(line, EVENT_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_EVENT_DESCRIPTION);
        }
        if (Parser.hasNoDate(line, EVENT_SEPARATOR)) {
            throw new DukeException(DukeException.ERROR_NO_EVENT_DATE);
        }
        TaskList.addEvent(Parser.parseDescription(line, EVENT_SEPARATOR), Parser.parseDate(line, EVENT_SEPARATOR));
        Ui.printAddedTask();
    }

    public static void addTodo(String line) throws DukeException {
        if (Parser.hasNoTodoDescription(line)) {
            throw new DukeException(DukeException.ERROR_NO_TODO_DESCRIPTION);
        }
        TaskList.addTodo(Parser.parseBody(line));
        Ui.printAddedTask();
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
        Ui.printDeletedTask(taskIndex - 1);
        TaskList.removeTask(taskIndex - 1);
    }

    public static void handleWrongCommand() throws DukeException{
        throw new DukeException(DukeException.ERROR_INVALID_COMMAND);
    }
}
