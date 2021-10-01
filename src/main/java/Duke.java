import duke.*;


public class Duke {

    private Storage storage;
    private TaskHandler taskHandler;
    private Ui ui;

    public Duke(String filePath) {
        Storage storage = new Storage();
        taskHandler = new TaskHandler(storage);
        ui = new Ui();
    }

    public void run() {
        ui.sayHello();
        String userInput = "";
        boolean isExit = false;

        while (!isExit) {
            try {
                userInput = ui.readCommand();
                isExit = ui.isExit(userInput);
                if (!isExit) {
                    ui.handleUserInput(taskHandler, userInput);
                }
            } catch (IllegalArgumentException | DukeException e) {
                ui.show(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
