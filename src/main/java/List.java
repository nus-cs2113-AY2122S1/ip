public class List extends Command {
    private Tasks tasks;
    private Ui ui;

    /**
     * Constructor for command to print the list of tasks to the terminal
     *
     * @param tasks list of all existing tasks
     * @param ui Handles interaction with the user
     */
    public List(Tasks tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() {
        ui.printList(tasks);
    }
}
