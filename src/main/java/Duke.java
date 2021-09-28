public class Duke {
    private TaskList taskslist;
    private Ui ui;

    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        taskslist = storage.getUpdatedTasks();
        ui = new Ui(storage);
    }

    public void run() {
        ui.printLogo();
        ui.greet();
        if (ui.getMode() == ui.ECHO_MODE) {
            ui.printModeEntered(ui.ECHO_MODE);
            ui.runEchoMode();
        } else if (ui.getMode() == ui.TASK_MODE) {
            ui.printModeEntered(ui.TASK_MODE);
            ui.runTaskMode(taskslist);
        }

        if (ui.getMode() != 0) {
            ui.exit();
            ui.printExitResponse(true);
        }
    }

    public static void main(String[] args) {
        new Duke("lennox.txt").run();
    }

}
