package duke;

import duke.command.Command;

public class Duke {
    public static void main(String[] args) {
        interactWithUser();
    }

    /**
     * Starts Duke, repeatedly waits for and executes user commands until exit command is received.
     */
    public static void interactWithUser() {
        boolean isInteracting = true;
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = null;
        ui.printStartMessage();
        try {
            storage = new Storage();
            storage.loadData(taskList);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }

        while (isInteracting) {
            String userInput = ui.readInput().stripLeading();
            try {
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isInteracting = !command.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        ui.printExitMessage();
    }
}
