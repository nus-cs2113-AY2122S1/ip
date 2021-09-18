package duke.storage;

import duke.message.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>UserData</h1>
 * This class contains methods that are involved with the storage of user data.
 * These include creating data files for new users and writing/saving user data.
 */
public class UserData {

    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIRECTORY = "data/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Writes text into user data file.
     *
     * @param textToAdd The text to be written stored as a String.
     * @throws IOException Failed or interrupted Input/Output operation.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * Creates directory and data file for the user if either are not found.
     * Prints out an error message if something went wrong during file creation.
     */
    public static void initDataOnStartUp() {
        File dataFile = new File(FILE_PATH);
        File directory = new File(DIRECTORY);
        // if directory already exists, nothing happens
        directory.mkdir();
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println(Message.FAILED_TO_CREATE_DATA_FILE_MESSAGE);
            }
        }
    }

    /**
     * Saves user data where the task list in a String format is written into the user data file.
     *
     * @param textToAdd The text to be written stored as a String.
     */
    public static void saveData(String textToAdd) {
        try {
            writeToFile(textToAdd);
        } catch (IOException ioe) {
            System.out.println(Message.SAVE_DATA_ERROR_MESSAGE);
        }
    }
}
