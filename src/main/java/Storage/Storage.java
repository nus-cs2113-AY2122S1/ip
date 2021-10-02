package Storage;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static final String FILEPATH = "duke.txt";

    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        try {
            File directory = new File(FILEPATH);
            if (!directory.exists()) {
                directory.mkdir();
            }

            Task task;
            Scanner s = new Scanner(FILEPATH);
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split("\\|");
                String taskType = parts[0].trim();
                Boolean isDone = Boolean.parseBoolean(parts[1].trim());
                String taskDescription = parts[2].trim();

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
    }
}
