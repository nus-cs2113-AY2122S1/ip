import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves tasks in the target file
 */
public class Storage {
    /**
     * Writes Task description and details into the file
     *
     * @param filePath Location of the target file to be written to with respect to the C-drive
     * @param textToAdd Message to be written to the target file
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
}
