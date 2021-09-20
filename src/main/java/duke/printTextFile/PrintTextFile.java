package duke.printTextFile;

/**
 * This class allows printing of a text file in the specified directory. It creates
 * objects based on their fileDirectory.
 *
 * @author YEOWEIHNGWHYELAB
 */

import java.io.FileReader;
import java.io.IOException;

public class PrintTextFile {
    private String fileDirectory;

    /**
     * The constructor for PrintTextFile to create text printing objects.
     *
     * @param fileDirectory .
     */
    public PrintTextFile(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Reads a .txt file in ASCII format and then print every single character (converted
     * ASCII index to characters) inside that .txt file onto the terminal.
     *
     * @throws IOException Trying to read the .txt file which user do not have permission or a
     *                     .txt file that does not exist.
     */
    public void printText() {
        try {
            /**
             * Note that the text file must be placed at the base java directory (when
             * directory is not clearly specified), and the name of the text file is
             * placed here!
             */
            FileReader readTextFile = new FileReader(this.fileDirectory);

            int singleCharacters;

            while ((singleCharacters = readTextFile.read()) != -1) {
                // Print each character individually.
                System.out.print((char) singleCharacters);
            }
            readTextFile.close();
        } catch (IOException except) {
            System.out.println("IO EXCEPTION!");
            System.out.println("Ensure that you have files in correct directory and named correctly!");
            except.printStackTrace();
        }

        System.out.println("");
    }
}
