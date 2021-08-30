abstract class UserCommand {
    TaskList tasks;

    UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    UserCommand() {
    }

    abstract public void execute ();
}
