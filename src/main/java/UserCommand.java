abstract public class UserCommand {
    TaskList tasks;

    public UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public void execute ();
}
