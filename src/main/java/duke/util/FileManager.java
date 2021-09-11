package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private final String filePath = "data/duke.txt";
    private final File file = new File(filePath);

    public ArrayList<String> readFile() {
        ArrayList<String> contentArray = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                contentArray.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        return contentArray;
    }

    public void writeFile(String fileContent, boolean append) {
        try {
            FileWriter writer = new FileWriter(filePath, append);
            writer.write(fileContent);
            writer.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
