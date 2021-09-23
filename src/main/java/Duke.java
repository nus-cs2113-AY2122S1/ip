import command.Command;
import command.ExitCommand;
import exception.AustinException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showIOError(e);
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.printWelcomeMessage();
        ui.printCurrentStatus(taskList.tasksCount());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printActivatedMessage();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c instanceof ExitCommand;
            } catch (AustinException e) {
                ui.showError(e);
            } catch (IOException e) {
                ui.showIOError(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingTaskIndexError();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showNumberFormatError();
            } finally {
                ui.showFinishingLine(isExit);
            }
        }
    }
}