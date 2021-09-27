public class Bye extends Command {
    private Ui ui;
    public Bye(Ui ui) {
        this.ui = ui;
    }
    public boolean isExit() {
        ui.exit();
        return true;
    }
}
