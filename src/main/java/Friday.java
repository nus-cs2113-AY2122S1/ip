import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Friday {
    public static void main(String[] args) {
        MessagePrinter.greetUser();
        try {
            TaskManager.loadData();
        } catch (FileNotFoundException e1) {
            try {
                Path path = Paths.get("data/friday.txt");
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        TaskManager.manageTasks();
        MessagePrinter.exitMessage();
    }
}
