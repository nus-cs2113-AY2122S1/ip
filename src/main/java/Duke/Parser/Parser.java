package Duke.Parser;

import Duke.Command.*;
import Duke.DukeException.DukeException;

public class Parser {
    public static final int TODO_POS = 5;
    public static final int BY_POS = 4;
    public static final int DEADLINE_POS = 9;
    public static final int AT_POS = 4;
    public static final int EVENT_POS = 6;
    public static final int FIND_POS = 5;

    public Parser() {
    }

    /**
     * Parse the input and get the command with required variables.
     *
     * @param input Input string from the user.
     * @return Command Command class parsed from the input.
     */
    public static Command parse(String input) throws DukeException {
        String findCommand = input.split(" ")[0];
        if (input.trim().equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.trim().equals("clear")) {
            return new ClearCommand();
        } else if (input.trim().equals("help")) {
            return new HelpCommand();
        } else if (findCommand.equals("done")) {
            return prepareDone(input);
        } else if (findCommand.equals("delete")) {
            return prepareDelete(input);
        } else if (findCommand.equals("find")) {
            return prepareFind(input);
        } else if (findCommand.equals("todo")) {
            return prepareTodo(input);
        } else if (findCommand.equals("deadline")) {
            return prepareDeadline(input);
        } else if (findCommand.equals("event")) {
            return prepareEvent(input);
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Prepare the done command by extracting the index.
     *
     * @param input Input string from the user.
     * @return DoneCommand DoneCommand got from the input.
     * @throws DukeException if the input is in an incorrect format
     */
    public static DoneCommand prepareDone(String input) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException n) {
            throw new DukeException("Please give a numerical index for 'done' command :-(");
        }

        return new DoneCommand(index);
    }

    /**
     * Prepare the delete command by extracting the index.
     *
     * @param input Input string from the user.
     * @return DeleteCommand DeleteCommand got from the input.
     */
    public static DeleteCommand prepareDelete(String input) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException n) {
            throw new DukeException("Please give a numerical index for 'delete' command :-(");
        }

        return new DeleteCommand(index);
    }

    /**
     * Prepare the find command by extracting the description.
     *
     * @param input Input string from the user.
     * @return FindCommand FindCommand got from the input.
     * @throws DukeException if the input is in an incorrect format
     */
    public static FindCommand prepareFind(String input) throws DukeException {
        String description;
        try {
            description = input.substring(FIND_POS);
        } catch (NumberFormatException n) {
            throw new DukeException("Please give a numerical index for 'done' command :-(");
        }

        return new FindCommand(description);
    }

    /**
     * Prepare the add todo command by extracting the description.
     *
     * @param input Input string from the user.
     * @return AddTodoCommand AddTodoCommand got from the input.
     * @throws DukeException if the input is in an incorrect format
     */
    public static AddTodoCommand prepareTodo(String input) throws DukeException {
        String description;
        try {
            description = input.substring(TODO_POS);
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("Please give me a description the task :-(");
        }

        return new AddTodoCommand(description);
    }

    /**
     * Prepare the add deadline command by extracting the description and by time.
     *
     * @param input Input string from the user.
     * @return AddDeadlineCommand AddDeadlineCommand got from the input.
     * @throws DukeException if the input is in an incorrect format
     */
    public static AddDeadlineCommand prepareDeadline(String input) throws DukeException {
        int indexOfBy = input.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DukeException("Please tell me when the deadline is by :-(");
        }

        String by;
        try {
            by = input.substring(indexOfBy + BY_POS);
        } catch (IndexOutOfBoundsException i){
            throw new DukeException("Please give me a time for the deadline :-(");
        }

        String description;
        try {
            description = input.substring(DEADLINE_POS, indexOfBy - 1);
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("Please give me a description for the deadline :-(");
        }

        return new AddDeadlineCommand(description, by);
    }

    /**
     * Prepare the add event command by extracting the description and by time.
     *
     * @param input Input string from the user.
     * @return AddEventCommand AddEventCommand got from the input.
     * @throws DukeException if the input is in an incorrect format
     */
    public static AddEventCommand prepareEvent(String input) throws DukeException {
        int indexOfAt = input.indexOf("/at");
        if (indexOfAt == -1) {
            throw new DukeException("Please tell me when the event is at :-(");
        }

        String at;
        try {
            at = input.substring(indexOfAt + AT_POS);
        } catch (IndexOutOfBoundsException i){
            throw new DukeException("Please give me a time for the event :-(");
        }

        String description;
        try {
            description = input.substring(EVENT_POS, indexOfAt - 1);
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("Please give me a description for the event :-(");
        }

        return new AddEventCommand(description, at);
    }
}
