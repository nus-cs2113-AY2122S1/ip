package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_PATH = "duketasks.txt";

    /**
     * Initial function for getting stored tasks
     * in the duketasks.txt file
     *
     * @param tasks Arraylist of tasks
     */
    public static void getStoredData(ArrayList<Task> tasks) {
        try {
            loadTasksFromFile(tasks);
        } catch (FileNotFoundException e) {
            createFile();
        } catch (Exception e) {
            System.out.println("Error in opening duketasks.txt");
        }
    }

    /**
     * Loads tasks from
     * duketasks.txt file
     *
     * @param tasks Arraylist of tasks
     */
    private static void loadTasksFromFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File storedTasks = new File(FILE_PATH);
        Scanner scanner = new Scanner(storedTasks);
        while (scanner.hasNext()) {
            loadStoredTasks(scanner.nextLine(), tasks);
        }
    }

    /**
     * Creates a file
     * duketasks.txt if it does
     * not already exist
     */
    private static void createFile() {
        try {
            File newFile = new File(FILE_PATH);
            newFile.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Cannot create file");
        }
    }

    /**
     * Loads a task as an object in the program
     */
    private static void loadStoredTasks(String input, ArrayList<Task> tasks) {
        String[] splitArr = input.split("\\|");
        String taskType = splitArr[0].trim();
        String taskIsDone = splitArr[1].trim();
        String taskDescription = splitArr[2].trim();

        switch (taskType) {
        case "T":
            tasks.add(new Todo(taskDescription));
            break;
        case "D":
            String deadlineBy = splitArr[3];
            tasks.add(new Deadline(taskDescription, deadlineBy));
            break;
        case "E":
            String eventAt = splitArr[3];
            tasks.add(new Event(taskDescription, eventAt));
            break;
        default:
        }
        if (taskIsDone.equals("1")) {
            tasks.get(tasks.size() - 1).markDone();
        }
    }


    /**
     * Saves tasks from program to duketasks.txt file
     *
     * @param tasks
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String toAdd = task.fileDescription() + "\n";
                fileWriter.write(toAdd);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot save data to " + FILE_PATH);
        }
    }
}
