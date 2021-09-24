package duke.storage;

import duke.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File class that handles any reading/writing to file operations.
 */
public class FileHandler {

    private String fileDirectory;

    public FileHandler(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Method to place the given contents back into the specified file, it will overwrite all data in that specified
     * file.
     *
     * @param fileName File to be processed.
     * @param contents All contents from current task list during the execution of duke.
     * @throws DukeException Error regarding writing to file issues.
     */
    public void writeToFile(String fileName, String contents) throws DukeException {
        File directory = new File(fileDirectory);
        directory.mkdir();
        try {
            FileWriter fw = new FileWriter(new File(fileDirectory, fileName));
            fw.write(contents);
            fw.close();
        } catch (IOException e) {
            String message = String.format("Error: An error has occurred when writing to file %s.", fileName);
            throw new DukeException(message);
        }
    }

    /**
     * Method to load all contents from the specified file.
     *
     * @param fileName File to be processed.
     * @return All contents from the given filename.
     * @throws DukeException Error regarding file not found.
     */
    public ArrayList<String> load(String fileName) throws DukeException {
        ArrayList<String> contents = new ArrayList<String>();
        File f = new File(fileDirectory, fileName);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                contents.add(s.nextLine());
            }
            return contents;
        } catch (FileNotFoundException e) {
            String message = String.format("Notice: File %s not found.", fileName);
            throw new DukeException(message);
        }
    }

}
