package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class used for load and save data file.
 */
public class Storage {
    public static final String root = System.getProperty("user.dir");
    public static final Path dataPath = Paths.get(root, "data", "duke.txt");
    public static final Path dirPath = Paths.get(root, "data");

    /**
     * Constructor of class.
     *
     * Creates new directory and file if dirPath and filePath is not found.
     */
    public Storage() {
        File dataDirectory = new File(dirPath.toString());

        if(!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            File dataFile = new File(dataPath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the previous saved data file.
     *
     * @return tasks ArrayList saved in previous data file.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();

        File dataFile = new File(dataPath.toString());
        Scanner dataScanner = new Scanner(dataFile);

        while (dataScanner.hasNext()) {
            String content = dataScanner.nextLine();
            String taskType = content.substring(0,1);
            String taskContent = content.substring(8);
            int taskStatusIndex = 4;
            int taskDateIndex = taskContent.indexOf('|');
            if (taskType.equals("T")) {
                tasks.add(new Todo(taskContent));
                if (content.charAt(taskStatusIndex) == '1') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            } else if (taskType.equals("D")) {
                tasks.add(new Deadline(taskContent.substring(0, taskDateIndex - 1), taskContent.substring(taskDateIndex + 2)));
                if (content.charAt(taskStatusIndex) == '1') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            } else if (taskType.equals("E")) {
                tasks.add(new Event(taskContent.substring(0, taskDateIndex - 1), taskContent.substring(taskDateIndex + 2)));
                if (content.charAt(taskStatusIndex) == '1') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        }
        return tasks;
    }

    /**
     * Saves new tasks to data file.
     *
     * @param tasks ArrayList of tasks to be saved.
     */
    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(dataPath.toString());
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    writer.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    writer.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Deadline) task).getBy() + System.lineSeparator());
                } else if (task instanceof Event) {
                    writer.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Event) task).getAt() + System.lineSeparator());
                } else {
                    return;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();;
        }

    }
}
