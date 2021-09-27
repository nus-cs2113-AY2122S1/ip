package hal.storage;
import hal.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles saving and loading data from local storage.
 */
public class UserData {
    private static final String FILE_PATH = "data/hal.txt";
    public static final String EXCEPTION_FILE_NOT_FOUND = "File was not found :(";
    public static final String EXCEPTION_IO = "File could not be created for some reason... :(";
    static StorageDataParser sr = new StorageDataParser();
    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";

    /**
     * Writes string version of Task object to a file object using FileWriter.
     *
     * @param str Str represents a string format of a Task object.
     */
    public static void writeToFile(String str) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(EXCEPTION_IO);
            System.out.println(LINE_BREAK_SINGLE);
        }
    }

    /**
     * Returns an ArrayList of Task objects.
     * The file containing user data is read line by line, and the string is parsed and converted to a Task object.
     * The tasks are then added to an arraylist and gets returned.
     * If no tasks are saved, null is returned.
     *
     * @return An ArrayList containing Task objects.
     */
    public static ArrayList<Task> readFromFile() {
        File f = new File(FILE_PATH);
        ArrayList<Task> tempTasks;

        try {
            Scanner s = new Scanner(f);
            tempTasks = new ArrayList<>(999);
            while (s.hasNext()) {
                tempTasks.add(sr.readListFromMemory(s.nextLine()));
            }
            return tempTasks;
        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_FILE_NOT_FOUND);
            System.out.println(LINE_BREAK_SINGLE);
        }
        return null;
    }

    /**
     * Creates a file directory and file if they do not exist.
     *
     * @param directory Directory the absolute path to the data file.
     */
    public static void initFileWithDirectory(String directory) {
        File dir = new File(directory);
        File dataFile = new File(FILE_PATH);

        //if the directory doesn't exist, it will be created
        if (!dir.exists()) {
            dir.mkdir();

        }

        //if the file doesn't exist, it will be created
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(EXCEPTION_IO);
                System.out.println(LINE_BREAK_SINGLE);
            }
        }
    }
}
