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
        checkDirectory();
        FileWriter file = new FileWriter(FILEPATH);
        for (Task item : tasks) {
            file.append(item.toSave() + System.lineSeparator());
        }
        file.close();
    }

    /**
     * Processes tasks from ArrayList of tasks and store in the save file
     *
     * @param tasks ArrayList of tasks
     */
    public static void loadData(ArrayList<Task> tasks) {
        //ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] descriptionParts = s.nextLine().split("\\|");
                String typeOfTask = descriptionParts[0];
                String taskDescription = descriptionParts[1];
                Boolean isCompleted = Boolean.parseBoolean(descriptionParts[2]);
                try {
                    Task task;
                    switch (typeOfTask) {
                    case "todo":
                        task = new ToDo(taskDescription);
                        break;
                    case "deadline":
                        task = new Deadline(taskDescription, descriptionParts[3]);
                        break;
                    case "event":
                        task = new Event(taskDescription, descriptionParts[3]);
                        break;
                    default:
                        throw new InvalidFile();
                    }
                    if (isCompleted) {
                        task.setDone();
                    }
                    tasks.add(task);
                } catch (InvalidFile e){
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
    public static void checkDirectory(){
        try{
            File directory = new File(FILEPATH);
            if(!directory.exists()){
                directory.getParentFile().mkdirs();
                directory.createNewFile();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
