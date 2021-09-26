package duke;

import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(), storage);
            parser = new Parser(tasks);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessage();
        }
    }

    public void sendCommand() {
        String line;
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.promptUser();
                line = ui.sendInput();
                ui.printLine();
                parser.checkCommand(line);
                isExit = parser.isBye;
            } catch (NumberFormatException e) {
                ui.printNumberFormatExceptionMessage();
            } catch (DukeException e) {
                //Catching DukeException errors
            } catch (IOException e) {
                ui.printIOExceptionMessage();
            } catch (DateTimeParseException e) {
                ui.printDateTimeExceptionMessage();
            }
        }
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("Tasks.txt").sendCommand();
    }
}