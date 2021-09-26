package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = new File(filePath).getAbsolutePath();
    }

    public static void saveNewTask(String[] input) throws IOException {
        String filePath = new File("Tasks.txt").getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, true);
        String fullTaskAsString = "";
        for (String individualString : input) {
            fullTaskAsString += individualString + " ";
        }
        fullTaskAsString += "| 0";
        fw.write(fullTaskAsString + "\n");
        fw.close();
    }

    public static void saveAllTasks(ArrayList<Task> commands) throws IOException {
        String filePath = new File("Tasks.txt").getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String taskInFile;
        String done;
        for (Task individualTask : commands) {
            if (individualTask instanceof Deadline) {
                taskInFile = "deadline " + individualTask.description + " /by " + ((Deadline) individualTask).by;
            } else if (individualTask instanceof Event) {
                taskInFile = "event " + individualTask.description + " /at " + ((Event) individualTask).at;
            } else {
                taskInFile = "todo " + individualTask.description;
            }
            if (individualTask.isDone) {
                done = "1";
            } else {
                done = "0";
            }
            String fullTaskAsString = taskInFile + " | " + done + "\n";
            Files.write(Paths.get(filePath), fullTaskAsString.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public ArrayList<String> loadTasks() throws IOException {
        ArrayList<String> loadedTasks = new ArrayList<>();
        String filePath = new File("Tasks.txt").getAbsolutePath();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            loadedTasks.add(textFromFile);
        }
        System.out.println(Ui.STARTING_MESSAGE);
        return loadedTasks;
    }
}
