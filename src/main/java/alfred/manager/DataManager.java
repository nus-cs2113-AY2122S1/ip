package alfred.manager;

import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Manage data persistence in local storage
public abstract class DataManager {
    private final static String FILE_PATH = "data/alfred.txt";
    private final static String SEPARATOR = ",";
    private static File taskFile;

    //1. check for existing file
    private static boolean hasExistingFile() {
        // Fetch file
        taskFile = new File(FILE_PATH);
        try {
            if (!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
            }
            if (taskFile.createNewFile()) {
                MessageManager.createNewFileMessage();
                return false;
            }
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
        return true;
    }
    //1a. If no existing safe file, create new one
    public static int populateTasks(Task[] tasks) {
        if (!hasExistingFile()) {
            return 0;
        }
        int listIndex = 0;
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                decodeAndLoadTask(tasks, s.nextLine(), listIndex);
                listIndex++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return listIndex;
    }

    private static void decodeAndLoadTask(Task[] tasks, String input, int index) {
        String[] destructuredInputs = input.split(",");
        switch (destructuredInputs[0]) {
        case "T":
            tasks[index] = new Todo(destructuredInputs[2]);
            break;
        case "E":
            tasks[index] = new Event(destructuredInputs[2], destructuredInputs[3]);
            break;
        case "D":
            tasks[index] = new Deadline(destructuredInputs[2], destructuredInputs[3]);
            break;
        default:
            return;
        }
        if (destructuredInputs[1].equals("true")) {
            tasks[index].setTaskDone();
        }
    }
    //2. Save all tasks
    public static void saveAllTasks(Task[] tasks, int numberOfTasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < numberOfTasks; i++) {
                String encodedTask = encodeTask(tasks[i]);
                fw.write(encodedTask);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IO Exception at FW.");
        }
    }

    private static String encodeTask(Task t) {
        String baseTask = t.getType() + SEPARATOR + t.isDone()
                + SEPARATOR + t.getDescription() + SEPARATOR + t.getDate() + "\n";
        return baseTask;
    }
}
