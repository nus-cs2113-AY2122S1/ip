package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DELIMITER = "---";
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

    /**
     * Loads previously saved file for tasks on startup.
     * If it does not exist, a new file will be created.
     */
    public static void load() {
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
                    Ui.showTaskLoadedSuccessMessage();
                    return;
                }
            }
            Ui.showBlankLoadFileMessage();
        } catch (FileNotFoundException e) {
            Ui.showLoadingFileMissing();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Splits a line from load file into information parseComponents can understand.
     *
     * @param line A String of one sentence scanned from the load file.
     * @throws DukeException Format of string is unexpected.
     */
    private static void parseText(String line) throws DukeException {
        String[] words = line.split(DELIMITER);
        try {
            parseComponents(words);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.showLoadFileCorrupted();
        }
    }

    /**
     * Adds tasks from load file using information parsed from parseText.
     *
     * @param words String array containing the different information regarding a task.
     * @throws DukeException Format of taskType is unexpected.
     */
    private static void parseComponents(String[] words) throws DukeException {

        if (words.length < 3 || words.length > 4) {
            throw new ArrayIndexOutOfBoundsException();
        }

        String taskType = words[0];
        String taskIsDone = words[1];
        String taskName= words[2];
        String taskDetails = "Hello there. Welcome to my FileManager.java!";

        if (taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            taskDetails = words[3];
        }

        if (taskType.equals(TODO) || taskType.equals(DEADLINE) || taskType.equals(EVENT)) {
            try {
                TaskList.addTaskFromFile(taskType, taskIsDone, taskName, taskDetails);
            } catch (DukeException e) {
                Ui.showLoadFileCorrupted();
            }
        } else {
            throw new DukeException("Unidentified task type.");
        }
    }

    /**
     * Saves the tasks input by user during the session to duke.txt.
     *
     * @param tasks ArrayList containing tasks the user wishes to save.
     */
    protected static void saveTaskToFile(ArrayList<Task> tasks) {
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

    /**
     * Formats information about a task into a string readable by the load() method.
     *
     * @param tasks ArrayList containing tasks the user wishes to save.
     * @param taskIndex Index of the task in the ArrayList.
     * @return A string format to be saved in file.
     * @throws IOException Writing to save file is erroneous.
     */
    private static String getTaskForSaving(ArrayList<Task> tasks, int taskIndex) throws IOException {
        return tasks.get(taskIndex).toSaveFile(DELIMITER) + "\n";
    }
}
