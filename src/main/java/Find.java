public class Find extends Command {
    private Tasks tasks;
    private Ui ui;
    private String keyword;

    /**
     * Constructor for command when the user wants to search for a tasks containing a certain keyword.
     * @param tasks List of all existing tasks.
     * @param ui Handles interaction with user.
     * @param keyword keyword to be searched in all existing tasks
     */
    public Find(Tasks tasks, Ui ui, String keyword) {
        this.tasks = tasks;
        this.ui = ui;
        this.keyword = keyword;
    }
    public void execute() {
        ui.printMatching(tasks, keyword);
    }
}
