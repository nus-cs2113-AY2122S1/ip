
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DataManager {

    /*examples of file content
    T | 1 | read book
    E | 0 | project meeting | Aug 6th 2-4pm */
    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] params = line.split("\\s\\|\\s");
            switch (params[0]) {
            case "T":
                TaskManager.addTodo(params[2]);
                break;
            case "D":
                TaskManager.addDeadline(params[2], params[3]);
                break;
            case "E":
                TaskManager.addEvent(params[2], params[3]);
                break;
            }
            if (params[1].equals("1")) {
                TaskManager.taskDoneLatest();
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeFileContents(String filePath) throws IOException {
        //clear contents of file
        String textToAppend;
        FileWriter fw = new FileWriter(filePath, false);// create a FileWriter in override mode
        fw.write("");
        for (int i = 0; i < TaskManager.taskCount; i++) {
            Task task = TaskManager.tasks.get(i);
            if (task.getType().equals("T")) {
                textToAppend = task.getType() + " | " + task.getStatus() + " | " + task.getDescription() + System.lineSeparator();
            }
            else {
                textToAppend = task.getType() + " | " + task.getStatus() + " | " + task.getDescription() + " | " + task.getTime() + System.lineSeparator();
            }
            appendToFile(filePath, textToAppend);
        }
    }
}