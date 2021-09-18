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

public class Storage {

    public final File taskFile;
    public final Path path;

    public static final String FILE_PATH = "data/savedTasks.txt";

    public Storage() throws IOException {
        taskFile = new File(FILE_PATH);
        path = Paths.get(FILE_PATH);
    }

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

    public void updateFileData(ArrayList<Task> allTasks) throws IOException {
        List<String> encodedTaskList = TaskEncoder.encodeTaskList(allTasks);
        Files.write(path, encodedTaskList);
    }
}
