package duke;

public class Duke {
    private Ui ui;

    public Duke(TaskManager taskManager) {
        ui = new Ui();

        ui.printWelcomeMessage();
        ui.readUserInputUntilBye(taskManager);
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.loadData();

        new Duke(taskManager);
//        printWelcomeMessage();
//        readUserInputUntilBye(taskManager);
//        printExitMessage();
    }
}
