abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage);

    public abstract boolean exit();

}
