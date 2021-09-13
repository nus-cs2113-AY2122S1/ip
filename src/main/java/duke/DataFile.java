package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataFile {
    private static final String directoryPath = "data";
    private static final String filePath = "tasks.txt";

    public static void write(String inputToWrite) {
        try {
            File newDirectory = new File(directoryPath);
            newDirectory.mkdirs();
            FileWriter writer = new FileWriter(directoryPath + File.separator + filePath, true);
            writer.write(inputToWrite + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to access file");
        }
    }
}
