package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataDetective {

    public String fileLocation = Paths.get(System.getProperty("user.dir"), "data/duke.txt").normalize().toString();

    public void saveData() {
        try {
            Path filePath = Paths.get("data/duke.txt");
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void loadData() {
        try {
            checkExist("data/duke.txt");
            loadFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void checkExist(String fileLocation) throws IOException {
        File f = new File(fileLocation);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    public void loadFile(String fileLocation) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String next = s.nextLine();
            String words[] = next.split("\\\\|");
        }
    }

    public void saveFile() {
    }
}
