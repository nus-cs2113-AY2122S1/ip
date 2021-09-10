package duke.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserData {

    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIRECTORY = "data/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    public static void initDataOnStartUp () {
        File dataFile = new File(FILE_PATH);
        File directory = new File(DIRECTORY);

        // if directory already exists, nothing happens
        directory.mkdir();

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Failed to create new user data file!");
            }
        }
    }

    public static void saveData (String textToAdd) {
        try {
            writeToFile(textToAdd);
        } catch (IOException ioe) {
            System.out.println("Failed to write into user data file!");
        }
    }

}
