public class List extends Command {
    private Tasks tasks;
    private Ui ui;
    public List(Tasks tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() {
        ui.printList(tasks);
    }
}
