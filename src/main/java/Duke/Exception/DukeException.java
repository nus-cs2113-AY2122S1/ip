package Duke.Exception;

import Duke.UI.UserInterface;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Class of exceptions in Duke Program
 */
public class DukeException extends Exception{

    /**
     * Executes exception message if user task list is empty
     */
    public static void emptyTodoException() {
        UserInterface.emptyTodoMessage();
    }

    /**
     * Executes exception message if deadline instruction format is invalid
     */
    public static void invalidDeadlineException() {
        UserInterface.invalidDeadlineMessage();
    }

    /**
     * Executes exception message if event instruction format is invalid
     */
    public static void invalidEventException() {
        UserInterface.invalidEventMessage();
    }

    public static void invalidDoneException() {
        UserInterface.invalidDoneMessage();
    }

    /**
     * Executes exception message if delete instruction format is invalid
     */
    public static void invalidDeleteException() {
        UserInterface.invalidDeleteMessage();
    }

    /**
     * Executes exception message if task is empty
     */
    public static void emptyTaskException() {
        UserInterface.printEmptyTaskMessage();
    }

    /**
     * Executes exception message if save file is invalid
     */
    public static void invalidSaveFileException() {
        UserInterface.printInvalidSaveFileMessage();
    }
    /**
     * Executes exception message if IO exception occurs when creating new file
     */
    public static void createIOException(IOException ioException) {
        UserInterface.createIOExceptionMessage(ioException);
    }

    /**
     * Executes exception message if IO exception occurs when saving file
     */
    public static void SaveIOException(IOException ioException) {
        UserInterface.printSaveIOExceptionMessage(ioException);
    }

    /**
     * Executes exception message if find instruction format is invalid
     */
    public static void invalidFindException() {
        UserInterface.printInvalidFindMessage();
    }

    /**
     * Executes exception message if date-time format is invalid
     */
    public static void dateTimeParseException(DateTimeParseException dtpException) {
        UserInterface.createDtpExceptionMessage(dtpException);
    }
}
