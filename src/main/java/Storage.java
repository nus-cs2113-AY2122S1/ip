package ip.src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Storage class. A <code>Storage</code> Class contains methods regarding the
 * storage of data into a pre-defined file path e.g., Read, Write
 */
public class Storage {

    private static String file = "ip/src/main/java/duke.txt";

    /**
     * Prints the file contents if any.
     * @throws FileNotFoundException If file is not found
     */
    static void printFileContents() throws FileNotFoundException {
        File f = new File(file); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes the file contents if any.
     * @throws IOException If failed or interrupted I/O operations
     */
    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the file contents if any.
     */
    static void saveFile(String text) {
        try {
            writeToFile(file, text);
            writeToFile(file, System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
