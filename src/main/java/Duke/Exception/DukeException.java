package Duke.Exception;

import Duke.UI.UserInterface;

import java.io.IOException;
import java.time.format.DateTimeParseException;


public class DukeException extends Exception{
    public static void emptyTodoException() {
        UserInterface.emptyTodoMessage();
    }

    public static void invalidDeadlineException() {
        UserInterface.invalidDeadlineMessage();
    }

    public static void invalidEventException() {
        UserInterface.invalidEventMessage();
    }

    public static void invalidDoneException() {
        UserInterface.invalidDoneMessage();
    }

    public static void invalidDeleteException() {
        UserInterface.invalidDeleteMessage();
    }

    public static void emptyTaskException() {
        UserInterface.emptyTaskMessage();
    }

    public static void invalidSaveFileException() {
        UserInterface.invalidSaveFileMessage();
    }

    public static void createIOException(IOException ioException) {
        UserInterface.createIOExceptionMessage(ioException);
    }

    public static void SaveIOException(IOException ioException) {
        UserInterface.SaveIOExceptionMessage(ioException);
    }

    public static void invalidFindException() {
        UserInterface.invalidFindMessage();
    }
      
    public static void dateTimeParseException(DateTimeParseException dtpException) {
        UserInterface.createdtpExceptionMessaeg(dtpException);
    }
}
