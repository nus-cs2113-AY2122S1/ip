package alfred.manager;

import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Manage data persistence in local storage
public abstract class DataManager {
    private final static String FILE_PATH = "data/alfred.txt";
    private final static String SEPARATOR = ",";
    private static File taskFile;

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

    public static void populateTasks(ArrayList<Task> tasks) {
        if (!hasExistingFile()) {
            return;
        }
        int index = 0;
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                decodeAndLoadTask(tasks, s.nextLine(), index);
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void decodeAndLoadTask(ArrayList<Task> tasks, String input, int index) {
        String[] destructuredInputs = input.split(",");
        switch (destructuredInputs[0]) {
        case "T":
            tasks.add(new Todo(destructuredInputs[2]));
            break;
        case "E":
            tasks.add(new Event(destructuredInputs[2], destructuredInputs[3]));
            break;
        case "D":
            tasks.add(new Deadline(destructuredInputs[2], destructuredInputs[3]));
            break;
        default:
            return;
        }
        if (destructuredInputs[1].equals("true")) {
            tasks.get(index).setTaskDone();
        }
    }

    public static void saveAllTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : tasks) {
                String encodedTask = encodeTask(t);
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
