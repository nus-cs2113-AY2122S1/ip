package FileManager;

import InputHandle.Tasks.TaskList;

import java.io.FileOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileSaver {
    private Path filePath;

    public FileSaver (String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get("UserStatus", fileName);
    }

    public FileSaver () {
        String fileName = "default.txt";
        filePath = Paths.get("UserStatus", fileName);
    }



    public void save(TaskList tasks) {
        File directory = new File("UserStatus");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileOutputStream fileStream = new FileOutputStream(filePath.toString());
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(tasks);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
