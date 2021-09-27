public class Bye extends Command {
    private Ui ui;

    /**
     * Constructor when user wants to close the application.
     * @param ui Handles interaction with the user.
     */
    public Bye(Ui ui) {
        this.ui = ui;
    }
    public boolean isExit() {
        ui.exit();
        return true;
    }
}
