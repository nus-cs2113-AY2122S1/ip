public class Duke {
    private TaskList taskslist;
    private Ui ui;

    /**
     * Executes skeleton for the whole program.
     */
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

        // If mode is selected properly and user indeed wants to exit.
        if (ui.getMode() != 0) {
            ui.exit();
            ui.printExitResponse(true);
        }
    }

    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        taskslist = storage.getUpdatedTasks();
        ui = new Ui(storage);
    }

    public static void main(String[] args) {
        // All tasks created or deleted during program runtime will be updated in lennox.txt local file.
        new Duke("lennox.txt").run();
    }

}
