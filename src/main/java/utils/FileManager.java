package utils;

import console.InputParser;
import error.Error;
import task.Task;
import task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static final String FILE_PATH = "data/savedTasks.txt";

    public static void parseFile(TaskManager taskManager) throws IOException {
        File taskFile = new File(FILE_PATH);
        taskFile.getParentFile().mkdir();
        if (taskFile.createNewFile()) {
            return;
        }
        Scanner s = new Scanner(taskFile);
        while (s.hasNext()) {
            String[] taskComponents = InputParser.getCommandComponents(s);
            String taskDetails = InputParser.getTaskDetails(taskComponents);
        }
    }

    public static void loadFileData(TaskManager taskManager) {
        try {
            parseFile(taskManager);
        } catch (IOException e) {
            Error.displayFileCreateError();
        }
    }

    public static void updateFileData(Task[] allTasks, int taskCount) throws IOException {
        FileWriter taskFile = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskCount; i++) {
            taskFile.write(allTasks[i].toString());
            taskFile.write(System.lineSeparator());
        }
        taskFile.close();
    }
}
