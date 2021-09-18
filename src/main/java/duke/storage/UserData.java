package duke.storage;
import duke.storage.StorageDataParser;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UserData {
    private static final String FILE_PATH = "data/hal.txt";
    public static final String EXCEPTION_FILE_NOT_FOUND = "File was not found :(";
    public static final String EXCEPTION_IO = "File could not be created for some reason... :(";
    static StorageDataParser sr = new StorageDataParser();
    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";

    public static void writeToFile(String str) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(EXCEPTION_IO);
            System.out.println(LINE_BREAK_SINGLE);

        }
    }

    public static ArrayList<Task> readFromFile() throws IOException {
        File f = new File(FILE_PATH);
        ArrayList<Task> tempTasks = null;

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
