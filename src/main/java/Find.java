public class Find extends Command {
    private Tasks tasks;
    private Ui ui;
    private String keyword;
    public Find(Tasks tasks, Ui ui, String keyword) {
        this.tasks = tasks;
        this.ui = ui;
        this.keyword = keyword;
    }
    public void execute() {
        ui.printMatching(tasks, keyword);
    }
}
