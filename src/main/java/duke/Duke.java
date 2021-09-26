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
            Ui.printFileNotFoundMessage();
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }

    public void sendCommand() {
        String line;
        Ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                Ui.promptUser();
                line = ui.sendInput();
                Ui.printLine();
                parser.checkCommand(line);
                isExit = parser.isBye;
            } catch (NumberFormatException e) {
                Ui.printNumberFormatExceptionMessage();
            } catch (DukeException e) {
                //Catching DukeException errors
            } catch (IOException e) {
                Ui.printIOExceptionMessage();
            } catch (DateTimeParseException e) {
                Ui.printDateTimeExceptionMessage();
            }
        }
        Ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("Tasks.txt").sendCommand();
    }
}