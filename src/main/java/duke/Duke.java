package duke;

import duke.commands.Command;
import java.io.FileNotFoundException;

/**
 * Duke is a task-taking bot.
 * Duke keeps track of all your tasks in a reader-friendly format, even after you close the application.
 *
 * @author  Yip Wayne
 * @version 1.0
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            }
            catch (NullPointerException e) {
                System.out.println("Try another input!");
            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Wayne/NUS/Y2S1/CS2113/iP/data/duke.txt").run();
    }

}
