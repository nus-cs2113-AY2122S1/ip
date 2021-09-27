package karlett;

import karlett.commands.Command;
import karlett.parser.Parser;
import karlett.storage.StorageFile;
import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private TextUi ui;
    private StorageFile storageFile;
    private TaskList tasks;


    public Duke(String filePath) throws IOException, InterruptedException {
        ui = new TextUi();
        ui.greet();
        storageFile = new StorageFile(filePath);
        try {
            tasks = new TaskList(storageFile.loadData(), filePath);
            ui.printFinishLoadingDataMessage(tasks);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage(filePath);
            tasks = new TaskList(filePath);
        }
    }

    public void run() throws IOException {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks);
            c.execute(tasks, ui, storageFile);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Duke("karlett.txt").run();
    }
}
