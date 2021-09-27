import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static File save;
    private Storage storage;
    private Tasks tasks = new Tasks();
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        File directory = new File ("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() throws IOException {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, tasks, ui, storage);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
