package duke;

import java.io.IOException;

public class Duke {
    final static private String filePath = Storage.getFilePath();

    private Ui ui;
    private Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        tasks.listOperations();
        storage.writeFile(filePath, tasks);
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke(filePath).run();
    }
}
