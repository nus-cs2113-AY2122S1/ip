package filemanager;

import task.Task;
import task.TaskManager;
import ui.Display;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class manages the storage file that stores saved tasks.
 */
public class Storage {

    /** Default path and file name. */
    public static final String FILE_PATH = "data/savedTasks.txt";

    private final File taskFile;
    private final Path path;

    /**
     * Creates the Storage and set the file details.
     *
     * @throws IOException If there is an error in the file path.
     */
    public Storage() throws IOException {
        taskFile = new File(FILE_PATH);
        path = Paths.get(FILE_PATH);
    }

    /**
     * Loads the saved task data from the storage file into TaskManager.
     * If the savedTasks.txt file does not exist, it creates one.
     *
     * @param taskManager TaskManager that stores the saved task data.
     * @throws IOException If there is an error in the creation or reading of file.
     */
    public void loadFileData(TaskManager taskManager) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            Display.displayCreateFile();
            taskFile.getParentFile().mkdir();
            taskFile.createNewFile();
            return;
        }
        Display.displayLoadingFile();
        TaskDecoder.parseFile(Files.readAllLines(path), taskManager);
    }

    /**
     * Overwrites the storage file with the latest task data from the TaskManager.
     *
     * @param allTasks All tasks objects managed by the TaskManager.
     * @throws IOException If there is an error in writing to the file.
     */
    public void updateFileData(ArrayList<Task> allTasks) throws IOException {
        List<String> encodedTaskList = TaskEncoder.encodeTaskList(allTasks);
        Files.write(path, encodedTaskList);
    }
}
