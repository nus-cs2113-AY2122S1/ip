package duke.control;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    private Storage() {
        setPath();
    }

    /**
     * Checks and ensures that the save file exists in the correct location. Creates the directory/file if required.
     * save file must have the path ../Data/dukeData.txt
     *
     * @throws IOException
     */
    protected static void createFile() throws IOException {
        File file = new File(filePath);
        File dir = new File(System.getProperty("user.dir") + "/Data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Saves the current list data into a text file.
     * It ensures that the save file exists in the correct location, then overrides it with the current data.
     *
     * @param list TaskList that will be saved
     */
    public static void saveData(TaskList list) {
        try {
            createFile();
            writeDukeDataIntoFile(list);
        } catch (IOException e) {
            System.out.println("Something went wrong, your data was not saved");
        }
    }

    private static void writeDukeDataIntoFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> taskList = list.getTaskList();
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            fw.write(taskList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads the data from the save file and loads it into the current Duke list.
     * The data will only be read if the save file is not empty.
     *
     * @param list TaskList object to add the data to
     * @throws IOException If the save file is not found
     */
    protected static void readDukeDataFromFile(TaskList list) throws IOException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            list.addEntryFromFile(s.nextLine());
        }
    }

    protected static void loadDataFromFile(TaskList list) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                readDukeDataFromFile(list);
            } catch (IOException e) {
                System.out.println("Something went wrong, cannot load saved data");
            }
        }
    }

    /**
     * Wipes saved data
     *
     * @throws IOException if the save file is not found
     */
    protected static void clearSavedData() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Checks if the save file is empty
     *
     * @return true is the save file is empty, false otherwise.
     */
    protected static boolean isEmpty() {
        File file = new File(filePath);
        if (file.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Sets the filePath static variable.
     * filePath is set according to the directory that the user has launched Duke from
     */
    protected static void setPath() {
        filePath = System.getProperty("user.dir") + "/Data/dukeData.txt";
    }

    /**
     * Returns the path to the save file
     *
     * @return filePath the String of the save file's location
     */
    public static String getFilePath() {
        return filePath;
    }
}
