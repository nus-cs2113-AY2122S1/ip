package duke.exception;

public class ToDoCommandError extends DukeException{
    public ToDoCommandError() {
        this.errorMessage = "OH NO! The \"todo\" command should be in this format: \ntodo <task name>";
    }
}
