package duke.file;

import duke.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private String fileDirectory;

    public FileHandler(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

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
