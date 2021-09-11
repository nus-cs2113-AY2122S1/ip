package FileManager;

import InputHandle.Tasks.TaskList;

import java.io.FileOutputStream;
import java.io.IOException;
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
        try {
            FileOutputStream fileStream = new FileOutputStream(filePath.toString());
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(tasks);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
