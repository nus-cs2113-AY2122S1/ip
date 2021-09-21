public class Duke {

    private Storage storage;
    private TaskList tasksList = new TaskList();
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasksList = storage.load();
        } catch (Storage.DukeException e) {
            ui.showMessages(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("./src/Duke.txt").run();
    }

    public void run() {
        startDuke();
        runCommand();
        exitDuke();
    }

    private void startDuke() {
        this.ui = new Ui();
        ui.showWelcomeMessage();
    }

    private void exitDuke() {
        ui.showGoodbyeMessage();
        try {
            storage.write(tasksList);
        } catch (Storage.DukeException e) {
            ui.showMessages(e.getMessage());
        }
        System.exit(0);
    }

    private void runCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        command.setData(tasksList);
        CommandResult result = command.execute();
        return result;
    }

}

