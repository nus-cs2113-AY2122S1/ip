package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String DELIMITER = "---";
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

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
                if (!s.hasNext()) {
                    Ui.printTaskLoadedSuccessMessage();
                }
            }
        } catch (FileNotFoundException e) {
            Ui.printLoadFileNotFoundMessage();
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
        String taskDetails = "Hello there. Welcome to my FileManager.java!";
        if (taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            taskDetails = words[3];
        }

        if (taskType.equals(TODO) || taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            TaskManager.addTaskFromFile(taskType, taskIsDone, taskName, taskDetails);
        } else {
            throw new DukeException("Unidentified task type.");
        }
    }

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
