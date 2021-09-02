class DoneCommand extends UserCommand {
    private int taskIndex;

    public DoneCommand (int taskIndex, TaskList tasks) {
        super(tasks);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute () {
        System.out.println("     Nice! I've marked this task as done: ");
        this.tasks.markAsDone(taskIndex - 1);
    }
}
