package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private final String dirPath = "data/";
    private final String filePath = "data/duke.txt";
    private final File dir = new File(dirPath);
    private final File file = new File(filePath);

    public boolean isFolderCreated() {
        return dir.mkdir();
    }

    public boolean isFileCreated() throws IOException {
        return file.createNewFile();
    }

    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> fileLines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            fileLines.add(scanner.nextLine());
        }
        return fileLines;
    }

    public void writeFile(ArrayList<String> fileLines) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (String line : fileLines) {
            writer.append(line);
        }
        writer.close();
    }
}
