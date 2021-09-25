package duke.file;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles dealing with the file and directory used to save current tasks.
 */
public class FileManager {
    private final Ui ui;

    public String filePath;
    public String directoryPath;


    /**
     * Constructs a FileManager with the file path and directory path.
     *
     * @param filePath      The path of the file.
     * @param directoryPath The path of the directory.
     */
    public FileManager(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.ui = new Ui();
    }

    /**
     * Creates a new file at the filePath.
     *
     * @throws IOException If an I/O exception has occurred.
     */
    // @@author brendanlsz-reused
    // Reused from https://www.tutorialspoint.com/java/io/file_createnewfile.htm
    // with modifications
    public void createFile() throws IOException {
        File file = new File(filePath);
        boolean hasCreatedFile = file.createNewFile();
        if (hasCreatedFile) {
            ui.println("File created at " + file.getCanonicalPath());
        } else {
            ui.println("File already exists at " + file.getCanonicalPath());
        }
    }

    /**
     * Creates a directory at directoryPath if it does not exist.
     */
    // @@author brendanlsz-reused
    // Reused from https://www.tutorialspoint.com/java/io/file_mkdir.htm
    // with modifications
    public void createDirectory() {
        File directory = new File(directoryPath);
        boolean hasCreatedDirectory = directory.mkdir();
        if (hasCreatedDirectory) {
            ui.println("New directory created at " + directoryPath);
        }
    }

    /**
     * Initialises Duke status by preloading the saved tasks from the file at filePath.
     *
     * @param taskManager Used to preload the saved tasks from the file.
     * @throws IOException If an I/O exception has occurred.
     */
    // @@author brendanlsz-reused
    // Reused from https://www.techiedelight.com/how-to-read-a-file-using-bufferedreader-in-java/
    // with modifications
    public void initialiseDukeStatus(TaskManager taskManager) throws IOException {
        FileInputStream stream;
        stream = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        taskManager.preloadTasks(reader);
        reader.close();
    }

    /**
     * Saves all the current tasks to the file at filePath.
     *
     * @param taskManager Used to convert current tasks to a String.
     * @throws IOException If an I/O exception has occurred.
     */
    // @@author brendanlsz-reused
    // Reused from https://www.javatpoint.com/java-filewriter-class
    // with modifications
    public void saveDukeStatus(TaskManager taskManager) throws IOException {
        File file = new File(filePath);
        FileWriter fw;
        String currentTasks;
        try {
            currentTasks = taskManager.convertCurrentTasksToString();
        } catch (DukeException e) {
            ui.println(e.getMessage());
            return;
        }
        fw = new FileWriter(file);
        fw.write(currentTasks);
        fw.close();
    }

    /**
     * Calls the saveDukeStatus method in order to save the current tasks.
     * Then, print to the user a success message if successful, or an error
     * message otherwise.
     *
     * @param taskManager Passed to the saveDukeStatus method.
     * @param ui          Used to print success message if current tasks were successfully saved.
     */
    public void saveDuke(TaskManager taskManager, Ui ui) {
        try {
            saveDukeStatus(taskManager);
        } catch (IOException e) {
            ui.printFileError();
            return;
        }
        ui.printSuccessfullySavedTasks(filePath);
    }

    /**
     * Initialises Duke by creating a directory and file if they do not exist, then
     * calls the initialiseDukeStatus method to initialise the status of Duke.
     *
     * @param taskManager Passed to the initialiseDukeStatus method.
     */
    public void initialiseDuke(TaskManager taskManager) {
        createDirectory();
        try {
            createFile();
        } catch (IOException e) {
            ui.printFileError();
        }
        try {
            initialiseDukeStatus(taskManager);
        } catch (FileNotFoundException e) {
            ui.println("File not found!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }
}
