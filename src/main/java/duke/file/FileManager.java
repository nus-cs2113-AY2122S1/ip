package duke.file;

import duke.DukeException;
import duke.task.TaskManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {
    public String filePath;
    public String directoryPath;


    public FileManager(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    // @@author brendanlsz-reused
    // Reused from https://www.tutorialspoint.com/java/io/file_createnewfile.htm
    // with modifications
    public void createFile() throws IOException {
        File file = new File(filePath);
        boolean hasCreatedFile = file.createNewFile();
        if (hasCreatedFile) {
            System.out.println("File created at " + file.getCanonicalPath());
        } else {
            System.out.println("File already exists at " + file.getCanonicalPath());
        }
    }

    // @@author brendanlsz-reused
    // Reused from https://www.tutorialspoint.com/java/io/file_mkdir.htm
    // with modifications
    public void createDirectory() {
        File directory = new File(directoryPath);
        boolean hasCreatedDirectory = directory.mkdir();
        if (hasCreatedDirectory) {
            System.out.println("New directory created at " + directoryPath);
        }
    }


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

    // @@author brendanlsz-reused
    // Reused from https://www.javatpoint.com/java-filewriter-class
    // with modifications
    public void saveDukeStatus(TaskManager taskManager) throws IOException {
        File file = new File(filePath);
        FileWriter fw;
        String currentTasks;
        try {
            currentTasks = taskManager.currentTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
        fw = new FileWriter(file);
        fw.write(currentTasks);
        fw.close();
    }
}
