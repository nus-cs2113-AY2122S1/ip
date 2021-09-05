abstract class UserCommand {
    TaskList tasks;

    UserCommand(){}

    UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public void execute () throws TaskEmptyException, TimeMissingException;

}
