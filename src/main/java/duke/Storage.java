package duke;

import duke.task.*;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    private static final String PATH = "data/duke.txt";
    private static final Path ROOT_FOLDER = Paths.get("data");

    public static ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> tasksArrayList;
        try {
            tasksArrayList = readFile(PATH);
        } catch (FileNotFoundException e) {
            if (Files.notExists(ROOT_FOLDER)) {
                Files.createDirectory(ROOT_FOLDER);
            }
            Files.createFile(Paths.get(PATH));
            tasksArrayList = readFile(PATH);
        }
        return tasksArrayList;
    }

    private static ArrayList<Task> readFile(String filePath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        ArrayList<Task> tasksArrayList = new ArrayList<>();
        int taskCount = 0;
        while (s.hasNext()) {
            String str = s.nextLine();
            String taskType = str.substring(1,2);
            String taskDescription = str.substring(7);
            String taskStatus = str.substring(4,5);
            switch (taskType) {
            case "T":
                tasksArrayList.add(new ToDo(taskDescription));
                completedTask(tasksArrayList, taskCount, taskStatus);
                break;
            case "D" :
                tasksArrayList.add(new Deadline(taskDescription.split(" /by ")[0], taskDescription.split(" /by ")[1]));
                completedTask(tasksArrayList, taskCount, taskStatus);
                break;
            case "E" :
                tasksArrayList.add(new Event(taskDescription.split(" /at ")[0], taskDescription.split(" /at ")[1]));
                completedTask(tasksArrayList, taskCount, taskStatus);
                break;
            }
            taskCount++;
        }
        s.close();
        return tasksArrayList;
    }

    public static void completedTask(ArrayList<Task> tasksArrayList, int taskCount, String taskStatus) {
        if (taskStatus.equals("X")) {
            tasksArrayList.get(taskCount).setDone(true);
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasksArrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: tasksArrayList) {
            fw.write(t + System.lineSeparator());
        }
        fw.close();
    }
}
