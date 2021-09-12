package duke.util;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserData {
    private static final String FILE_PATH = "data/hal.txt";
    static StorageDataParser sr = new StorageDataParser();

    public static void writeToFile(String str) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(str);
        fileWriter.close();
    }

    public static Task[] readFromFile() throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);

        Task[] tempTasks = new Task[999];
        int numItems = 0;

        while (s.hasNext()) {
            tempTasks[numItems] = sr.readListFromMemory(s.nextLine());
            numItems++;
        }
        return tempTasks;
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
            }
        }
    }
}
