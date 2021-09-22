package duke;

import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String DELIMITER = "---";
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
//    private static ArrayList<Task> tasks = new ArrayList<>(); //Try something other than global variable


    public static void loadFile() {
        File directory = new File("data");
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                parseText(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            //Todo proper Ui boxing, ask user if want to save (y/n)
            System.out.println("File not found. Load failed.");
            System.out.println("New file created.");
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    public static void parseText(String line) throws DukeException {
        String[] words = line.split(DELIMITER);
        parseComponents(words);
    }

    public static void parseComponents(String[] words) throws DukeException {
        String taskType = words[0];
        String taskIsDone = words[1];
        String taskName= words[2];
        String taskDetails = "Hello there.";
        if (taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            taskDetails = words[3];
        }

        if (taskType.equals(TODO) || taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            Duke.addTaskFromFile(taskType, taskIsDone, taskName, taskDetails);
        } else {
            throw new DukeException("Unidentified task type.");
        }
    }

    //todo printing format to duke.txt not as desired
    public static void saveTaskToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(getTaskForSaving(tasks, i));
            }
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Save failed.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getTaskForSaving(ArrayList<Task> tasks, int taskIndex) throws IOException {
        return tasks.get(taskIndex).toSaveFile(DELIMITER) + "\n";
    }
}
