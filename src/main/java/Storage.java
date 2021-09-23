import todo.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * This class includes functions for saving and loading files.
 *
 */
public class Storage {
    public static void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String stringToAdd = "";
        String taskDescription, type, date;
        Boolean isDone;
        for(int i = 0; i < Duke.tasks.size(); i++) {
            Task currentTask = Duke.tasks.get(i);
            taskDescription = Parser.extractTask(currentTask);
            type = currentTask.getType();
            isDone = currentTask.getStatus();
            stringToAdd = getString(stringToAdd, taskDescription, type, isDone);
            if(type == "e" || type == "d") {
                stringToAdd = extractDate(stringToAdd, currentTask);
            }
            stringToAdd += System.lineSeparator();
        }
        fw.write(stringToAdd);
        fw.close();
        Duke.printMessage("Tasks saved successfully");
    }

    private static String extractDate(String stringToAdd, Task currentTask) {
        String date;
        String[] splitString = currentTask.toString().split(":", 2);
        String removedSpace = splitString[1].trim();
        date = removedSpace.replace(")", "");
        stringToAdd += "|" + date;
        return stringToAdd;
    }

    private static String getString(String stringToAdd, String taskDescription, String type, Boolean isDone) {
        stringToAdd +=  type + "|" + isDone + "|" + taskDescription;
        return stringToAdd;
    }

    static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] splitInput = s.nextLine().split("\\|");
            TaskList.addTask(splitInput);
        }
    }

    protected static void readFile() {
        try {
            readFileContents(Duke.filePath);
            Duke.printMessage("Saved tasks successfully loaded!");
            TaskList.listAllTask();
        } catch (FileNotFoundException e) {
            Duke.printMessage("There is no preloaded file found! Please input your own tasks!");
        }
    }
}
