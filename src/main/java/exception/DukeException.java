package exception;

import ui.Ui;

public class DukeException extends Exception {
    /**
     * Error Messages
     */
    public static final String INVALID_TASK_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "That is invalid... Please use the syntax - "
            + "[taskType]" + Ui.SPACE_PREFIX + "[taskName] ([/by dateTime] or [/at dateTime] depending on taskType)"
            + Ui.LINE_BREAK + Ui.CONSOLE_LINE_PREFIX;
    public static final String UNKNOWN_COMMAND_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Hey, I don't quite understand this command. Please install a new CPU for me ;D"
            + Ui.LINE_BREAK + Ui.SPACE_PREFIX + "Just kidding, it's too expensive, just try again..." + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;
    public static final String MISSING_INDEX_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Excuse me Sir/Madam, which task number? Where is it? Under the Sea?" + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;
    public static final String NO_TASK_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Woah woah, you can't just mark something when your list of tasks is empty"
            + Ui.LINE_BREAK + Ui.CONSOLE_LINE_PREFIX;
    public static final String TODO_EMPTY_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Excuse you? The description for todo can NEVER be empty!" + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;

    /**
     * Class constructor with specified error message.
     *
     * @param errorMessage The error message of the encountered error/exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
