package karlett;

import karlett.commands.Command;
import karlett.parser.Parser;
import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private TextUi ui;
    private StorageFile storageFile;
    private TaskList tasks;

    /**
     * Return a Duke object that can load the file data into a task
     * list. If no file can be found through the file path, users have
     * the option to create a new file to store the task list.
     *
     * @param filePath file path to the storage file
     * @throws IOException An input or output exception
     */
    public Duke(String filePath) throws IOException {
        ui = new TextUi();
        ui.greet();
        storageFile = new StorageFile(filePath);
        try {
            tasks = new TaskList(storageFile.loadData(), filePath);
            ui.printFinishLoadingDataMessage(tasks);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage(filePath);
            tasks = new TaskList(filePath);
        } catch (Exception e) {
            ui.printIncorrectFileFormatMessage(filePath);
        }
    }

    /**
     * Takes in and process a user input until an exit command.
     *
     * @throws IOException An input or output exception.
     */
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
