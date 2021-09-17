package FileManager;

import tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileSaver {
    private Path filePath;

    public FileSaver (String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get("UserStatus", fileName);
    }

    public void save (TaskList tasks) {
        File directory = new File("UserStatus");
        if (!directory.exists()) {
            directory.mkdir();
        }


        try {
            FileWriter writer = new FileWriter(filePath.toString());
            writer.write(tasks.save());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
