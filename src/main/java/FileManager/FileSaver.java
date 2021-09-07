package FileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver {
    Path filePath;
    String fileName;

    public FileSaver (String userName) {
        this.fileName = userName + ".txt";
         filePath = Paths.get("..", "..", "..", "..", "UserStatus", fileName);
    }

    public void save() {
        try {
            FileOutputStream fileStream = new FileOutputStream(filePath.toString());
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.write(123);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
