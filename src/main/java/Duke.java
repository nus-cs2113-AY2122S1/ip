import duke.*;


public class Duke {

    private Storage storage;
    private TaskHandler taskHandler;
    private Ui ui;

    public Duke() {
        this.storage = new Storage();
        this.taskHandler = new TaskHandler(this.storage);
        this.ui = new Ui();
    }

    public void run() {
        String userInput = "";
        boolean isExit = false;

        ui.sayHello();

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
        new Duke().run();
    }
}
