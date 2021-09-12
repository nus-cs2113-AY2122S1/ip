package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private final String filePath = "duke.txt";
    private final File file = new File(filePath);

    public void createFile() throws IOException {
        file.createNewFile();
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
