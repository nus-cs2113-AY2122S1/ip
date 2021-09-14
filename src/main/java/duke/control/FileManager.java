package duke.control;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static String filePath;

    public FileManager() {
        filePath = getPath();
    }

    protected static void createFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    protected static void saveData(List list) {
        try {
            createFile();
            writeDukeDataIntoFile(list);
        } catch (IOException e) {
            System.out.println("Something went wrong, your data was not saved");
        }
    }

    protected static void writeDukeDataIntoFile(List list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> taskList = list.getTaskList();
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            fw.write(taskList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    protected static void readDukeDataFromFile(List list) throws IOException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            list.addEntryFromFile(s.nextLine());
        }
    }

    protected static void clearSavedData() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    private static String getPath() {
        return(System.getProperty("user.dir") + "/Data/dukeData.txt");
    }
}
