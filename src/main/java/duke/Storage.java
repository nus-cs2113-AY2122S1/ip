package duke;

import duke.task.*;

import java.time.*;
import java.time.format.*;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Contains all the methods related to the data storage such as reading and writing file.
 */
public class Storage {

    private static final String PATH = "data/duke.txt";
    private static final Path ROOT_FOLDER = Paths.get("data");

    /**
     * Load data from the path given.
     * @return
     * @throws IOException
     */
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

    /**
     * Read the file per line and store it as an ArrayList.
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    private static ArrayList<Task> readFile(String filePath) throws FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
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
                tasksArrayList.add(new Deadline(taskDescription.split(" /by ")[0], LocalDate.parse(taskDescription.split(" /by ")[1], formatter)));
                completedTask(tasksArrayList, taskCount, taskStatus);
                break;
            case "E" :
                tasksArrayList.add(new Event(taskDescription.split(" /at ")[0], LocalDate.parse(taskDescription.split(" /at ")[1], formatter)));
                completedTask(tasksArrayList, taskCount, taskStatus);
                break;
            }
            taskCount++;
        }
        s.close();
        return tasksArrayList;
    }

    /**
     * Mark the completed task from the loaded file.
     * @param tasksArrayList
     * @param taskCount
     * @param taskStatus
     */
    public static void completedTask(ArrayList<Task> tasksArrayList, int taskCount, String taskStatus) {
        if (taskStatus.equals("X")) {
            tasksArrayList.get(taskCount).setDone(true);
        }
    }

    /**
     * Store the ArrayList in the program to a file in the path given.
     * Executed whenever a user type <b>bye</b>.
     * @param filePath
     * @param tasksArrayList
     * @throws IOException
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasksArrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: tasksArrayList) {
            fw.write(t + System.lineSeparator());
        }
        fw.close();
    }
}
