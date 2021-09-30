import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Duke is a friendly note-taking bot.
 * Duke keeps track of all your tasks in a reader-friendly format,
 * even after you close the application
 *
 * @author  Yap Joon Siong
 * @version 1.0
 * Cool @since   2021-09-28
 */
public class Duke {
    private static File save;
    private Storage storage;
    private Tasks tasks = new Tasks();
    private Ui ui;

    private Duke(String filePath) throws IOException {
        ui = new Ui();
        File directory = new File ("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    private void run() throws IOException {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks, ui, storage);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
