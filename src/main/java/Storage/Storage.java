package Storage;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import Ui.Ui;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static final String FILEPATH = "data/duke.txt";

    /**
     * Checks if directory and file exists, if not, create new
     * directory and file
     */
    public static void checkDirectory () {
        try {
            File directory = new File("data");
            File file = new File("data.txt");
            if (!directory.exists()) {
                file.mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (IOException e) {
            Ui.showLoadingError();
        }
    }

    /**
     * Saves the list of tasks to a file duke.txt
     * @param tasks arraylist of tasks
     * @throws IOException
     */
    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        checkDirectory();
        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split("\\|");
                String taskType = parts[0].trim();
                boolean isDone = Boolean.parseBoolean(parts[1].trim());
                String taskDescription = parts[2].trim();
                Task task;
                switch (taskType) {
                case "todo":
                    task = new ToDo(taskDescription);
                    break;
                case "deadline":
                    task = new Deadline(taskDescription, parts[3]);
                    break;
                case "event":
                    task = new Event(taskDescription, parts[3]);
                    break;
                default:
                    throw new FileNotFoundException();
                }
                if (isDone) {
                    task.setDone();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file\n");
        }

        FileWriter fw = new FileWriter(FILEPATH);
        for (Task task : tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }
}

