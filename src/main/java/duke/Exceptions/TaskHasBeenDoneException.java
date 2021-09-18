package duke.Exceptions;


public class TaskHasBeenDoneException extends DukeException{
    public TaskHasBeenDoneException (int taskIndex) {
        message = "\tTask " + (taskIndex+1) + " has already been marked as done.";
    }
}
