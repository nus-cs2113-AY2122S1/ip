package duke.Storage;

import duke.ErrorHandling.ErrorStaticString;
import java.io.IOException;

/**
 * Handles writing string to file
 */
public class FileWrite extends Storage{

    /**
     * Append new string to file or
     * Overwrite existing file with new string
     *
     * @param s String to write to file
     * @param isAppend Boolean to indicate whether to overwrite file with new content or append to current file
     */
    public void writeToFile(String s, boolean isAppend){
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file, isAppend);
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(ErrorStaticString.ERROR_FILE_MESSAGE_WRITING);
        }
    }
}
