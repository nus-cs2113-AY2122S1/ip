import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class Parser {

    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private final static int DESCRIPTION = 0;
    private final static int DATETIME = 1;

    public Parser() {
    }

    /**
     * This function parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
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
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * This function check if done statement and index in done statement is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the done input.
     */
    private static boolean isValidDoneInput(String input) {
        return Pattern.matches("^done \\d+$", input.toLowerCase());
    }

    /**
     * This function check if done statement and index in delete statement is valid.
     *
     * @param input input given by the user.
     * @return returns the validity of the delete input.
     */
    private static boolean isValidDeleteInput(String input) {
        return Pattern.matches("^delete \\d+$", input.toLowerCase());
    }

    /**
     * This function retrieves the index given in done/delete statement.
     *
     * @param input input given by the user.
     * @return returns the index given in done/delete statement
     */
    private static int getIndex(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * This function uses regex to check if to do statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the to do statement.
     */
    private static boolean isValidTodoInput(String input) {
        return Pattern.matches("todo [:a-z0-9\\s]+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if deadline statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the deadline statement.
     */
    private static boolean isValidDeadlineInput(String input) {
        return Pattern.matches("deadline [a-z0-9\\s]+\\b /by .+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if event statement is valid.
     *
     * @param input input given by the user.
     * @return returns validity of the event statement.
     */
    private static boolean isValidEventInput(String input) {
        return Pattern.matches("event [a-z0-9\\s]+\\b /at .+", input.toLowerCase());
    }

    /**
     * This function gets the parameters for the Task subclasses by slicing input.
     *
     * @param parameters Array of string of fixed size 2 to store parameters for Task subclasses.
     * @param input      User's input into command line.
     * @param taskType   String that is pre-identified (DEADLINE/EVENT/TODO)
     */
    private static void getParameters(String[] parameters, String input, String taskType) throws InvalidTaskException {
        switch (taskType) {
        case TODO:
            String[] todoParts = input.split("(?i)todo ");
            parameters[DESCRIPTION] = todoParts[1];
            break;
        case DEADLINE:
            String[] initDeadlineParts = input.split("(?i)deadline ");
            String[] deadlineParts = initDeadlineParts[1].split(" /by ");
            if (deadlineParts.length != 2) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = deadlineParts[0];
            parameters[DATETIME] = deadlineParts[1];
            break;
        case EVENT:
            String[] initEventParts = input.split("(?i)event ");
            String[] eventParts = initEventParts[1].split(" /at ");
            if (eventParts.length != 2) {
                throw new InvalidTaskException();
            }
            parameters[DESCRIPTION] = eventParts[0];
            parameters[DATETIME] = eventParts[1];
            break;
        }
        if (Objects.equals(parameters[DESCRIPTION], "")) {
            throw new InvalidTaskException();
        }
    }

    /**
     * This function handles the done input by marking task as done.
     *
     * @param input input given by the user.
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
     * This function handles the delete input by deleting it.
     *
     * @param input input given by the user.
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
     * This function adds the input description into list accordingly, based on its task type.
     *
     * @param input    input given by the user.
     * @param taskType task type identified beforehand (DEADLINE/EVENT/TODO).
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

    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString);
    }

}
