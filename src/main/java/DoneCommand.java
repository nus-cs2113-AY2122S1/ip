public class DoneCommand extends UserCommand {
    private int index;

    public DoneCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public void execute () {
        System.out.println("    Nice! I've marked this task as done:");
        this.tasks.markAsDone(index);
    }
}
