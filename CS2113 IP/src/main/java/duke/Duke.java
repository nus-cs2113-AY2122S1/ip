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

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage();
        storage.createDirectory();
        storage.createFile();
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        tasks.listOperations();
        storage.writeFile(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke(filePath).run();
    }
}
