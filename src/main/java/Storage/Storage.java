package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;
import Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of data into local storage
 */
public class Storage {

    private static final String FILE_PATH = "data/duke.txt";

    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Loads saved data
     * @return List of saved tasks
     */
    public static ArrayList<Task> loadTasks() throws FileNotFoundException, IOException {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            File dir = new File("data");
            dir.mkdir();
            f.createNewFile();
        }
            try {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNext()) {
                String[] taskDetails = scanner.nextLine().split("\\|", 3);
                String taskType = taskDetails[0].trim();
                boolean isDone = taskDetails[1].trim().equals("X");

                switch (taskType) {
                case "T":
                    taskList.add(new Todo(taskDetails[2].trim(), isDone));
                    break;
                case "D":
                    String[] deadline = taskDetails[2].split("\\(by: ");
                    taskList.add(new Deadline(deadline[0].trim(), isDone, deadline[1].substring(0, deadline[1].indexOf(")"))));
                    break;
                case "E":
                    String[] event = taskDetails[2].split("\\(at: ");
                    taskList.add(new Event(event[0].trim(), isDone, event[1].substring(0, event[1].indexOf(")"))));
                    break;
                default:
                    break;
                }
            }
        } catch ( IOException e) {
                System.out.println(e.getMessage());
            }
        return taskList;
    }

    /**
     * Saves current tasks onto local storage
     * @param taskList list of tasks needed to be saved
     */
    public static void saveTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).getTaskType() + " | " + taskList.get(i).getStatusIcon() + " | " + taskList.get(i).getDescription());
        }
        fw.close();
    }

}
