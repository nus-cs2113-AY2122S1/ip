package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.TaskList;
import ui.Ui;


public class Storage {
    private final String DEFAULT_SAVE_DIRECTORY = "data";
    private final String DEFAULT_SAVE_FILE = "duke.txt";

    public Storage() {}

    public void saveTasksToFile(TaskList tasks) {
        ArrayList<String> stringFormattedTasks;
        File saveDir = new File(DEFAULT_SAVE_DIRECTORY);
        saveDir.mkdir();
        File saveFile = new File(saveDir, DEFAULT_SAVE_FILE);
        try {
            saveFile.createNewFile();
            FileWriter fw = new FileWriter(saveFile);
            stringFormattedTasks = TaskList.getTasksAsStringArrayList(tasks);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(stringFormattedTasks.get(i) + Ui.LINE_BREAK);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    public void instantiateTasksFromFile(TaskList tasks) {
        File saveDir = new File(DEFAULT_SAVE_DIRECTORY);
        saveDir.mkdir();
        File savedFile = new File(saveDir, DEFAULT_SAVE_FILE);
        if (!savedFile.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(savedFile);
            while (fileScanner.hasNext()) {
                String fileLine = fileScanner.nextLine();
                Task savedTask = TaskList.createSavedTask(fileLine);
                tasks.loadTaskFromFile(savedTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
