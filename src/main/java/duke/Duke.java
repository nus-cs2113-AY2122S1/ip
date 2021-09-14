package duke;

import duke.command.Command;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList(ui);
        try {
            storage = new Storage();
            storage.loadData(taskList);
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    public void run() {
        ui.printHelloMessage();
        processUserInput();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void processUserInput() {
        boolean isProcessing = true;
        Scanner input = new Scanner(System.in);
        while (isProcessing) {
            String userInput = input.nextLine().stripLeading();
            try {
                Command command = Parser.processCommand(userInput);
                command.executeCommand(taskList, storage);
                isProcessing = !Command.getIsExit();
            } catch (DukeException e) {
               ui.printErrorMessage(e);
            }
        }
        ui.printByeMessage();
    }
}
