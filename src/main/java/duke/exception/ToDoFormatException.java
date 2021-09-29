package duke.exception;

/**
 * Represents an exception where the user's ToDo command does not comply with the correct format.
 * ToDo command correct format: todo [task description].
 */
public class ToDoFormatException extends Exception {

    private final String TODO_INCORRECT_FORMAT_MSG = "Yikes, your todo command is wrong!"
            + " Please follow the format:\n"
            + "   [?] 4. Add Todos -> todo [description]";

    @Override
    public String toString() {
        return TODO_INCORRECT_FORMAT_MSG;
    }

}
