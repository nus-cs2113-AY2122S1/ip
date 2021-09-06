public class DeadLineCommandError extends DukeException{
    public DeadLineCommandError() {
        this.errorMessage = "OH NO! The \"deadline\" command should be in this format: \ndeadline <task name> /by <date>";
    }
}
