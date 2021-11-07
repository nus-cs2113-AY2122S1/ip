package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage for writing and loading tasks from a save file.
 */
public class Storage {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILEPATH = "data/savedData.txt";
    private static final String SAVEDIR = "data";

    /**
     * Serializes each task into a string and writes onto a save file.
     *
     * @param tasksList List of tasks
     * @throws IOException If the system failed to write data onto save file
     */
    public static void saveData(ArrayList<Task> tasksList) throws IOException {
        StringBuilder line = new StringBuilder();
        for (Task task: tasksList) {
            line.append(serializeTask(task));
        }

        FileWriter fw = new FileWriter(FILEPATH);
        fw.write(line.toString());
        fw.close();
    }

    /**
     * Deserializes the tasks stored in the save file in string format
     * and returns the entire list.
     *
     * @return List of tasks
     * @throws FileNotFoundException If save file was not found
     */
    public static ArrayList<Task> loadData() throws FileNotFoundException {
        File directory = new File(SAVEDIR);
        if (!directory.exists() && directory.mkdir()) {
            System.out.println("Successfully created a new directory for save file");
        } else {
            File f = new File(FILEPATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(deserializeTask(s.nextLine()));
            }
        }

        return tasks;
    }

    /**
     * Deserializes the task from string format into parsable commands
     *
     * @param serializedTask Task to be deserialized from string format
     */
    private static Task deserializeTask(String serializedTask) {
        Task task = null;
        String[] splitTask = serializedTask.split("\\|");

        String taskType = splitTask[0];
        boolean isDone = splitTask[1].equals("1");
        String description = splitTask[2].trim();

        switch (taskType) {
        case "T":
            task = new ToDo(description, isDone);
            break;
        case "D":
            String by = splitTask[3].trim();
            task = new Deadline(description, by, isDone);
            break;
        case "E":
            String at = splitTask[3].trim();
            task = new Event(description, at, isDone);
            break;
        }
        return task;
    }

    /**
     * Serializes the task to strings in a format delimited by "|"
     *
     * @param task Task to be delimited
     */
    private static String serializeTask(Task task) {
        String result = "";
        result += task.getTaskType() + "|";
        result += (task.getDoneStatus() ? 1 : 0) + "|";
        result += task.getDescription();

        if (task instanceof Deadline) {
            result += "|" + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            result += "|" + ((Event) task).getAt();
        }

        return result + "\n";
    }

}