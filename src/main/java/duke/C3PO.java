package duke;

import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The entire program begins here. All class objects are created here.
 */
public class C3PO {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * To create new Ui, Storage, TaskList and Parser object, and load data from the text file.
     *
     * @param filePath the name of the text file
     */
    public C3PO(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(),storage);
            parser = new Parser(tasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundMessage();
            tasks = new TaskList(storage);
            parser = new Parser(tasks);
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }

    /**
     * Allows the user to send his/her commands to C3PO until the user says "bye".
     */
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
                Ui.saySorry();
            } catch (IOException e) {
                Ui.printIOExceptionMessage();
            } catch (DateTimeParseException e) {
                Ui.printDateTimeExceptionMessage();
            }
        }
        Ui.sayBye();
    }

    public static void main(String[] args) {
        new C3PO("Tasks.txt").sendCommand();
    }
}