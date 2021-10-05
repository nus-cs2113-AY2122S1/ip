import todo.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * This class includes methods which helps to save and load files containing
 * tasks into the program.
 */
public class Storage {
    /**
     * Formats the tasks currently in the task list and saves it into a .txt file.
     *
     *
     * @param filePath path where .txt file will be created at.
     * @throws IOException when IO operations fail.
     */
    public static void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String stringToAdd = "";
        String taskDescription, type;
        Boolean isDone;
        for(int i = 0; i < Duke.tasks.size(); i++) {
            Task currentTask = Duke.tasks.get(i);
            taskDescription = Parser.extractTask(currentTask);
            type = currentTask.getType();
            isDone = currentTask.getStatus();
            stringToAdd = getString(stringToAdd, taskDescription, type, isDone);
            if(type.equals("e") || type.equals("d")) {
                stringToAdd = extractDate(stringToAdd, currentTask);
            }
            stringToAdd += System.lineSeparator();
        }
        fw.write(stringToAdd);
        fw.close();
        System.out.println("Tasks saved successfully");
        Duke.printDivider();
    }

    /**
     * Returns a string containing the date which the deadline or event has to be done by.
     *
     *
     * @param stringToAdd string that data will be appended to
     * @param currentTask task which we want to extract the date from
     * @return string which will be appended into the .txt file
     */
    private static String extractDate(String stringToAdd, Task currentTask) {
        String date;
        String[] splitString = currentTask.toString().split(":", 2);
        String removedSpace = splitString[1].trim();
        date = removedSpace.replace(")", "");
        stringToAdd += "|" + date;
        return stringToAdd;
    }

    /**
     * Appends string with formatted data that is to be added into .txt file.
     * @param stringToAdd string that will be written into the .txt file.
     * @param taskDescription description of the task to add
     * @param type type of task to add
     * @param isDone status of task to add
     * @return appended string with new data
     */
    private static String getString(String stringToAdd, String taskDescription, String type, Boolean isDone) {
        stringToAdd +=  type + "|" + isDone + "|" + taskDescription;
        return stringToAdd;
    }

    /**
     * If filePath exists, this method reads every line of filePath into the program.
     * @param filePath file that we want to read into the program
     * @throws FileNotFoundException if filePath cannot be found
     */
    static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] splitInput = s.nextLine().split("\\|");
            TaskList.addTaskFromFile(splitInput);
        }
    }

    /**
     * Executes commands to read the contents of the saved.txt file, if any).
     * Displays error message if no file is found.
     */
    protected static void readFile() {
        try {
            readFileContents(Duke.filePath);
            System.out.println("Saved tasks successfully loaded!");
            TaskList.listAllTask();
        } catch (FileNotFoundException e) {
            System.out.println("There is no preloaded file found! Please input your own tasks!");
            Duke.printDivider();
        }
    }
}
