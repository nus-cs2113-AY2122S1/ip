package com.file;

import com.task.Task;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a file manager that read from or write to a file, fileName representing the name of the storage file
 * filePath representing the path to the storage file.
 */
public class Storage {
    private final static int FILE_MAX_LINES = 100;
    String fileName;
    String filePath;

    /**
     * Constructor of Storage, set the fileName and filePath
     *
     * @param fileName the name of the storage file
     */
    public Storage(String fileName){
        this.fileName = fileName;
        this.filePath = "data/"+ this.fileName;
    }


    /**
     * Check if the path exist, and create one if the path does not exist.
     *
     * @throws IOException If the filepath cannot be created.
     */
    public void checkPath() throws IOException {
        File directory = new File("data");
        directory.mkdir();
        File f = new File(filePath);
        f.createNewFile();
    }


    /**
     * read from the file and return ArrayList<String> lines object that stores information
     *
     * @return ArrayList<String> lines object that stores information
     * @throws IOException If the filepath cannot be created or cannot be found.
     */
    public ArrayList<String> readFile() throws IOException {
        checkPath();
        ArrayList<String> lines = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        return lines;
    }

    /**
     * write the ArrayList<Task> lines into the files
     *
     * @param lines representing the list of tasks information
     * @throws IOException If the file cannot be created or cannot be found
     */
    public void rewriteFile(ArrayList<Task> lines) throws IOException {
        checkPath();
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        for(Task line : lines) {
            fw.append(line.toString()).append("\n");
        }
        fw.close();
    }

}
