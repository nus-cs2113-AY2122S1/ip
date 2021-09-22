package duke.storage;

import duke.tasklist.TaskManager;
import duke.tasklist.Task;
import duke.exceptions.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {

    /**
     * Save tasks to a text file
     *
     * @param filePath name of the text file that tasks are saved in
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @throws IOException if the file does not exist
     */
    public static void appendToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        // Format of duke.txt file: D|run|2pm|
        for (Task task : taskList) {
            fw.write(task.getTaskType() + "|" + task.getDescription() + "|" + task.getDeadline() + "|" + task.getStatusIcon() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Read tasks from the text file if it exists and adds the tasks into the current working task list
     *
     * @param filePath name of the text file that tasks are saved in
     * @param taskList Task type arraylist to store all the tasks
     * @throws FileNotFoundException if the file does not exist
     * @throws DukeException if the task cannot be added into the task list due to formatting issues
     */
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

    /**
     * Checks if the file exists and for tasks to be added
     *
     * @param filePath name of the text file that tasks are saved in
     * @param taskList Task type arraylist to store all the tasks
     */
    public static void printPreviousFileContents(String filePath, ArrayList<Task> taskList) {
        try {
            DataManager.readFileContents(filePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No data detected! I see you are a new user...Starting afresh!");
        } catch (DukeException e) {
            System.out.println("Duke Exception thrown!");
        }
    }

    /**
     * At the end of the program, updates and saves the current task list into the text file to be retrieved
     * when program is ran again
     *
     * @param filePath name of the text file that tasks are saved in
     * @param taskList Task type arraylist to store all the tasks
     */
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
