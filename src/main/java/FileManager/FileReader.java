package FileManager;

import InputHandle.Tasks.TaskList;

import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    private Path filePath;

    public FileReader(String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get(System.getProperty("user.dir"), "UserStatus", fileName);
    }

    public FileReader() {
        String fileName = "default.txt";
        filePath = Paths.get(System.getProperty("user.dir"),"UserStatus", fileName);
    }

    public TaskList restore() {
        try {
            FileInputStream fileStream = new FileInputStream(filePath.toString());
            ObjectInputStream os = new ObjectInputStream(fileStream);
            TaskList tasks = (TaskList) os.readObject();
            os.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean fileExists() {
        return filePath.toFile().exists();
    }
}
