package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        list = new TaskList(ui);
        storage = new Storage(list);
        storage.initList();
    }

    /**
     * Takes in user command and executes the relevant command. Terminates when isExit is true
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(list, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printUnknownCommandMessage();
            } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.printWrongCommandFormatMessage();
            }
        }
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
