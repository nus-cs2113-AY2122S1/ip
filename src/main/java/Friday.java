import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Friday {
    private static void createDataDirectory(Path filepath) throws IOException {
        Files.createDirectories(filepath.getParent());
        try {
            Files.createFile(filepath);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        MessagePrinter.greetUser();
        try {
            TaskManager.loadData();
        } catch (FileNotFoundException e1) {
            // Create directory and file
            Path path = Paths.get("/data/friday.txt");
            try {
                createDataDirectory(path);
            } catch (IOException e2) {
                System.err.println(e2.getMessage());
            }
        }
        // Scan in user input of tasks
        TaskManager.manageTasks();
        MessagePrinter.exitMessage();
    }
}
