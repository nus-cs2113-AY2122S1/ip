package duke;

import duke.actions.Task;
import duke.exceptions.DukeException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {

    public static void appendToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        // Format of duke.txt file: D|run|2pm|
        for (Task task : taskList) {
            fw.write(task.getTaskType() + "|" + task.getDescription() + "|" + task.getDeadline() + "|" + task.getStatusIcon() + System.lineSeparator());
        }
        fw.close();
    }

    public static void readFileContents(String filePath, ArrayList<Task> taskList) throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int currentIndex = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] params = line.split("\\|");
            switch (params[0]) {
            case "T":
                TaskManager.addTaskAsToDo(taskList, params[1], true);
                break;
            case "D":
                TaskManager.addTaskAsDeadline(taskList, "deadline " + params[1] + "/by" + params[2], true);
                break;
            case "E":
                TaskManager.addTaskAsEvent(taskList, "event " + params[1] + "/at" + params[2], true);
                break;
            }
            if (params[3].equals("X")) {
                taskList.get(currentIndex).updateIsDone();
            }
            currentIndex++;
        }
    }

    public static void printPreviousFileContents(String filePath, ArrayList<Task> taskList) {
        try {
            DataManager.readFileContents(filePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No data detected! I see you are a new user...Starting afresh!");
        } catch (DukeException e) {
            System.out.println("Duke Exception thrown!");
        }
    }

    public static void storeCurrentList(String filePath, ArrayList<Task> taskList) {
        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.write("");
            pw.flush();
            pw.close();
            DataManager.appendToFile(filePath, taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
