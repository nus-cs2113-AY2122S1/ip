package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    final static private String filePath = Storage.getFilePath();
    private final Ui ui;
    private final Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        storage.createDirectory();
        storage.createFile();
        tasks.listOperations();
        storage.writeFile(tasks);
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke(filePath).run();
    }
}
