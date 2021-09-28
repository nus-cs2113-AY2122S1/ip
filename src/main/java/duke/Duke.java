package duke;

import duke.commands.Command;
import duke.common.Messages;
import duke.data.TaskList;
import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;



public class Duke {

    public static final String NUMBER_DONE = "1";

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    public Duke() {
        tasks = new TaskList();
        ui = new TextUi();
        storage = new Storage(tasks);
        storage.initTaskList();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
            } catch (InvalidException e) {
                ui.showToUser(Messages.MESSAGE_INVALID_COMMAND);
            } catch (EmptyTaskException e) {
                ui.showToUser(Messages.MESSAGE_EMPTY_TASK_DESCRIPTION);
            } catch (IndexOutOfBoundsException e) {
                ui.showToUser(Messages.MESSAGE_INVALID_TASK_INDEX);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
