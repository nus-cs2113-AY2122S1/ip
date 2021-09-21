import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parser {

    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private final static int DESCRIPTION = 0;
    private final static int DATETIME = 1;
    private final static int REMOVE_TASK_WORD = 1;
    private final static int VALID_TASK = 2;

    public Parser() {
    }

    /**
     * Parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
     * @param tasks contains all the current tasks in the program.
     */
    public static void parseInput(String input, TaskList tasks) throws InvalidInputException {
        if (input.equalsIgnoreCase("Bye")) {
            Ui.sayGoodbye();
            Duke.setDukeDone();
        } else if (input.equalsIgnoreCase("List")) {
            Ui.printList(tasks);
        } else if (isValidDoneInput(input)) {
            int index = getIndex(input);
            handleDoneInput(index, tasks);
        } else if (isValidTodoInput(input)) {
            addToList(input, TODO, tasks);
        } else if (isValidDeadlineInput(input)) {
            addToList(input, DEADLINE, tasks);
        } else if (isValidEventInput(input)) {
            addToList(input, EVENT, tasks);
        } else if (isValidDeleteInput(input)) {
            int index = getIndex(input);
            handleDeleteInput(index, tasks);
        } else if (isValidFindInput(input)) {
            handleFindInput(input, tasks);
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns whether the find input is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the find input.
     */
    private static boolean isValidFindInput(String input) {
        return Pattern.matches("^find [a-z0-9\\s\\-]+$", input.toLowerCase());
    }

    /**
     * Finds whether the input is contained in any of the tasks.
     *
     * @param input input given by the user.
     * @param tasks the arraylist of all tasks.
     */
    private static void handleFindInput(String input, TaskList tasks) {
        String findText = input.substring(5).toLowerCase();
        TaskList tempTasks = new TaskList();
        for (Task task : tasks.getList()) {
            if (task.isInTask(findText)) {
                tempTasks.addTask(task);
            }
        }
        if (tempTasks.getLength() == 0) {
            Ui.printCannotFind();
        } else {
            Ui.printFind(tempTasks);
        }
    }

    /**
     * Returns whether the done input is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the done input.
     */
    private static boolean isValidDoneInput(String input) {
        return Pattern.matches("^done \\d+$", input.toLowerCase());
    }

    /**
     * Returns whether the delete input is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the delete input.
     */
    private static boolean isValidDeleteInput(String input) {
        return Pattern.matches("^delete \\d+$", input.toLowerCase());
    }

    /**
     * Retrieves the index given in done/delete statement.
     *
     * @param input input given by the user.
     * @return returns the index given in done/delete statement.
     */
    private static int getIndex(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Returns whether the to do input is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the to do statement.
     */
    private static boolean isValidTodoInput(String input) {
        return Pattern.matches("todo [:a-z0-9\\s]+", input.toLowerCase());
    }

    /**
     * Returns whether the deadline input is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the deadline statement.
     */
    private static boolean isValidDeadlineInput(String input) {
        return Pattern.matches("deadline [a-z0-9\\s]+\\b /by .+", input.toLowerCase());
    }

    /**
     * Returns whether the event input is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the event statement.
     */
    private static boolean isValidEventInput(String input) {
        return Pattern.matches("event [a-z0-9\\s]+\\b /at .+", input.toLowerCase());
    }

    /**
     * Gets the parameters for the Task subclasses by slicing input.
     *
     * @param parameters Array of string of fixed size 2 to store parameters for Task subclasses.
     * @param input      User's input into command line.
     * @param taskType   String that is pre-identified (DEADLINE/EVENT/TODO)
     */
    private static void getParameters(String[] parameters, String input, String taskType) throws InvalidTaskException {
        switch (taskType) {
        case TODO:
            String[] todoParts = input.split("(?i)todo ");
            parameters[DESCRIPTION] = todoParts[REMOVE_TASK_WORD];
            break;
        case DEADLINE:
            String[] initDeadlineParts = input.split("(?i)deadline ");
            String[] deadlineParts = initDeadlineParts[REMOVE_TASK_WORD].split(" /by ");
            if (deadlineParts.length != VALID_TASK) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = deadlineParts[DESCRIPTION];
            parameters[DATETIME] = deadlineParts[DATETIME];
            break;
        case EVENT:
            String[] initEventParts = input.split("(?i)event ");
            String[] eventParts = initEventParts[REMOVE_TASK_WORD].split(" /at ");
            if (eventParts.length != VALID_TASK) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = eventParts[DESCRIPTION];
            parameters[DATETIME] = eventParts[DATETIME];
            break;
        }
        if (Objects.equals(parameters[DESCRIPTION], "")) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Marks task as done.
     *
     * @param index index of the task to be marked done in tasks.
     * @param tasks arraylist of all the tasks.
     */
    public static void handleDoneInput(int index, TaskList tasks) {
        try {
            tasks.getItemFromIndex(index).markAsDone();
            Ui.printDoneTask(tasks.getItemFromIndex(index));
            Storage.refreshFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidDoneStatement();
        }
    }

    /**
     * Deletes task.
     *
     * @param index index of the task to be marked done in tasks.
     * @param tasks arraylist of all the tasks.
     */
    public static void handleDeleteInput(int index, TaskList tasks) {
        try {
            Task item = tasks.getItemFromIndex(index);
            tasks.deleteItemFromIndex(index);
            Ui.printDeleteTask(item, tasks.getLength());
            Storage.refreshFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidDeleteStatement();
        }
    }

    /**
     * Adds the input description into tasks accordingly, based on its task type.
     *
     * @param input    input given by the user.
     * @param taskType task type identified beforehand (DEADLINE/EVENT/TODO).
     * @param tasks    arraylist of all the tasks.
     */
    public static void addToList(String input, String taskType, TaskList tasks) {
        String[] parameters = new String[2];
        try {
            switch (taskType) {
            case TODO:
                getParameters(parameters, input, TODO);
                Todo todo = new Todo(parameters[DESCRIPTION]);
                tasks.addTask(todo);
                Storage.saveTaskInFile(todo);
                break;
            case DEADLINE:
                getParameters(parameters, input, DEADLINE);
                LocalDate deadlineDate = parseDate(parameters[DATETIME]);
                Deadline deadline = new Deadline(parameters[DESCRIPTION], deadlineDate);
                tasks.addTask(deadline);
                Storage.saveTaskInFile(deadline);
                break;
            case EVENT:
                getParameters(parameters, input, EVENT);
                LocalDate eventDate = parseDate(parameters[DATETIME]);
                Event event = new Event(parameters[DESCRIPTION], eventDate);
                tasks.addTask(event);
                Storage.saveTaskInFile(event);
                break;
            }
            Ui.printAddedMessage(tasks.getLastTask(), tasks.getLength());
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidTaskStatement();
        } catch (InvalidTaskException e) {
            Ui.printInvalidTaskStatement();
        } catch (DateTimeParseException e) {
            Ui.printInvalidDateStatement();
        }
    }

    /**
     * Parses the date given by user for deadline/event tasks, if possible.
     *
     * @param dateString The date string input by user.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString);
    }

}
