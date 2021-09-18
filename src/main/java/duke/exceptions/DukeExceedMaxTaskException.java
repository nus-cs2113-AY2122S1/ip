package duke.exceptions;

public class DukeExceedMaxTaskException extends DukeException{
    public DukeExceedMaxTaskException() {
        errorMessage = "Exceeded maximum task limit, please delete a task to continue";
    }
}
