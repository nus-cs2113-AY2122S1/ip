package duke.storage;

import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public void createDataFile() {
        File dataFolder = new File("data");
        File dataFile = new File("data/duke.txt");

        try {
            dataFolder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void loadDataFile(TaskList taskList) {
        File dataFile = new File("data/duke.txt");
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                taskList.processDataFile(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(("File not found!"));
        }
    }
}
