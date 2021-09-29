import java.util.ArrayList;
/**
 * This class deals with operations on Tasks.
 */
public class TaskHandler {

    protected static final String DONE_INDICATOR = "1";
    protected static final String DEADLINE_SEPARATOR = "/by";
    protected static final String EVENT_SEPARATOR = "/on";

    /**
     * Marks a Task specified by the user as done.
     *
     * @param line String of user input
     * @throws DukeException If there is no index of the task or the index is out of bound
     * @throws NumberFormatException If the index is not a number
     */
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

    /**
     * Adds a Deadline task specified by the user.
     *
     * @param line String of user input
     * @throws DukeException If there is no description or no date
     */
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

    /**
     * Adds an Event task specified by the user.
     *
     * @param line String of user input
     * @throws DukeException If there is no description or no date
     */
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

    /**
     * Adds a Todo task specified by the user.
     *
     * @param line String of user input
     * @throws DukeException If there is no description
     */
    public static void addTodo(String line) throws DukeException {
        if (Parser.hasNoTodoDescription(line)) {
            throw new DukeException(DukeException.ERROR_NO_TODO_DESCRIPTION);
        }
        TaskList.addTodo(Parser.parseBody(line));
        Ui.printAddedTask();
    }

    /**
     * Deletes a task specified by the user.
     *
     * @param line String of user input
     * @throws DukeException If there is no index of the task or the index is out of bound
     * @throws NumberFormatException If the index is not a number
     */
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

    /**
     * Search for task that contains the keyword in its description.
     *
     * @param line String of user input
     * @throws DukeException If there is no keyword input
     */
    public static void findTask(String line) throws DukeException{
        if (Parser.hasNoBody(line)) {
            throw new DukeException(DukeException.ERROR_NO_KEYWORD);
        }
        String keyword = Parser.parseBody(line);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : TaskList.tasks) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        printSearchedTasks(foundTasks);
    }

    /**
     * Print the search results for tasks.
     *
     * @param foundTasks List of tasks that contain the keyword in their descriptions
     */
    public static void printSearchedTasks(ArrayList<Task> foundTasks) {
        System.out.println(Ui.INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + "." + foundTasks.get(i));
        }
    }

    /**
     * Throw exception when the user input has wrong command format.
     *
     * @throws DukeException If the command has wrong format
     */
    public static void handleWrongCommand() throws DukeException{
        throw new DukeException(DukeException.ERROR_INVALID_COMMAND);
    }
}
