package duke.storage;

import duke.exception.InvalidFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILEPATH = "data/duke.txt";

    /**
     * Converts all task to string to write into duke.txt
     *
     * @param tasks ArrayList of tasks
     * @throws IOException throws an IO exception
     */
    public static void saveData(ArrayList<Task> tasks) throws IOException {
        FileWriter file = new FileWriter(FILEPATH);
        StringBuilder output = new StringBuilder();
        for (Task item : tasks) {
            output.append(item.toSave() + System.lineSeparator());
        }
        try {
            FileWriter myFile = new FileWriter(FILEPATH);
            myFile.write(output.toString());
            myFile.close();
        } catch (IOException e) {
            System.out.println("cannot write to file");
        }
    }

    /**
     * Processes tasks from ArrayList of tasks and store in the save file
     */
    public static void loadData(ArrayList<Task> tasks) {
        checkDirectory();
        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] descriptionParts = s.nextLine().split("\\|");
                String typeOfTask = descriptionParts[0].trim();
                String taskDescription = descriptionParts[2].trim();
                Boolean isCompleted = Boolean.parseBoolean(descriptionParts[1]);
                try {
                    Task task;
                    switch (typeOfTask) {
                    case "T":
                        task = new ToDo(taskDescription);
                        break;
                    case "D":
                        task = new Deadline(taskDescription, descriptionParts[3]);
                        break;
                    case "E":
                        task = new Event(taskDescription, descriptionParts[3]);
                        break;
                    default:
                        throw new InvalidFile();
                    }
                    if (isCompleted) {
                        task.setDone();
                    }
                    tasks.add(task);
                } catch (InvalidFile e) {
                    System.out.println("Invalid input in file");
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read from file");
        }
    }

    /**
     * Check if directory exists
     * If it does not exist then create a new file
     * Print error message if file cannot be created
     */
    public static void checkDirectory() {
        try {
            File directory = new File(FILEPATH);
            if (!directory.exists()) {
                directory.getParentFile().mkdirs();
                directory.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}