package duke.program;

import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FOLDER_PATH = "./data";

    private Storage storage;
    private TaskList tasks;
    private LizUi ui;
    private Parser parser;

    public Duke(String filePath, String folderPath) {
        storage = new Storage(filePath, folderPath);
        ui = new LizUi();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile(ui));
        } catch (IOException e) {
            ui.printFileErrorMessage();
        }
    }
    public static void main(String[] args) {
        new Duke(FILE_PATH, FOLDER_PATH).run();
    }

    public void run() {

        Scanner in = new Scanner(System.in);

        ui.printGreetingMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String line = ui.readCommand(in);
                ui.printLine();
                Command c = parser.parseUserInput(line);
                c.executeCommand(tasks, ui);
                isExit = c.isExit();
                storage.saveFile(tasks.getTaskList(), ui);
            } catch (IOException e) {
                ui.printFileErrorMessage();
            } finally {
                ui.printLine();
            }
        }
    }

}
