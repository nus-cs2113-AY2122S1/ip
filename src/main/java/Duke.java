import duke.*;


public class Duke {

    private Storage storage;
    private TaskHandler taskHandler;
    private Ui ui;

    public Duke(String filePath) {
        Storage storage = new Storage();
        taskHandler = new TaskHandler(storage);
        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.sayHello();
        String command = "";
        Parser parser = new Parser();

        while (!parser.inputIsBye(command.toLowerCase())) {
            try {
                command = ui.readCommand();
                //Command command = ui.readCommand(). Use Parser to do it in Ui
                //
                Formatter.printFormattedOutput(taskHandler.handleTasks(command));
            } catch (IllegalArgumentException e) {
                Formatter.printFormattedOutput(e.getMessage());
            } catch (DukeException e) {
                Formatter.printFormattedOutput(e.getMessage());
            }
        }

        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
