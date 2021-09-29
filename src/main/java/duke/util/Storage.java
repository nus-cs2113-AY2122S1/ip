package duke.util;

import duke.Duke;
import duke.task.Task;
import duke.ui.PrintBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.Duke.*;

public class Storage {

    private static final String FILE_PATH = "./data/duke.txt";
    private static File taskData = new File(FILE_PATH);

    public Storage() throws IOException {
        taskData = new File(FILE_PATH);
        if (!taskData.exists()) {
            boolean has_mkdir = taskData.getParentFile().mkdirs();
            boolean has_newFile = taskData.createNewFile();
            if (has_mkdir && has_newFile) {
                PrintBot.print("New File created at" + FILE_PATH);
            }  else {
                PrintBot.print("Trouble creating new file at " + FILE_PATH);
            }
        }
    }

    public void loadData() {
        try {
            print.loadingData();
            Scanner s = new Scanner(taskData);
            if (!s.hasNext()) {
                PrintBot.print(" => File is empty.");
            }
            while (s.hasNext()) {
                String data = s.nextLine();
                Duke.duke.loadData(data);
                print.loadData(data);
            }
            PrintBot.print("Finish loading Data.");
            print.line();
        } catch (FileNotFoundException e) {
            //file not found
        }
    }

    /*
     * Overwrites all data in the duke.txt file with
     * current list.
     */
    public void saveData() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            fw.write(t.getStorageFormat()+ System.lineSeparator());
        }

        fw.close();
    }
}
