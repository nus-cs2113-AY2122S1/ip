package duke.data;

import duke.Type.Task;
import duke.Type.Divider;
import duke.startup.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage functionalities to write task list to a save file.
 * A <code>Storage</code> object primarily runs on a save file, given by fileName,
 *  in the directory folderName.
 */
public class Storage{
    private static String filePath;
    private static String folderName = "data/";
    private static String fileName = "list.txt";
    public Storage(String filePathToInput) {
        filePath = filePathToInput;
    }


    /**
     * Prints a task array list in easy to parse data format
     *  format: [TYPE] | [DESC] | [DEADLINE IF EXIST] | [DONE]
     * @param t task to wrangle data from
     * @return
     */
    public static String printTaskAsString(Task t) {
        return t.toString() + '\n';
    }

    /**
     * Saves list to save file
     * @param taskList task list to save
     * @throws IOException  if file/ directory not found
     *                      - handled by creating new directory/ file if necessary
     */
    public static void saveList(TaskList taskList) throws IOException {
        checkAndAddDirectory();
        File newList = new File(folderName + fileName);
        FileWriter fw = new FileWriter(folderName + fileName);
        for (Task t : taskList.getTaskList()) {
            fw.write(printTaskAsString(t));
        }
        fw.close();
    }

    /**
     * Returns a task list with attributes of every task, given save file
     * format of task per line of save file:
     *      [TASK_TYPE] | [DEADLINE IF EXISTS] | [DESCRIPTION] | [DONE]
     * @return tasksToRead task list
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasksToRead = new ArrayList<>();
        try {
            checkAndAddDirectory();
            File f = new File(filePath);
            Scanner scanList = new Scanner(f);
            while (scanList.hasNext()) {
                String readLine = scanList.nextLine();
                if (readLine.equals("")) {
                    break;
                }
                addTaskToArray(readLine, tasksToRead);
            }
            return tasksToRead;
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            System.out.println("Hey, I didn't find list.txt in /data!");
            System.out.println("creating new file...");
        } catch (NullPointerException e) {
        } catch (IOException e) {
            System.out.println("Hey, Input/ Output exception, returning empty list...");
        } finally {
            return tasksToRead;
        }
    }

    /**
     * Parses text to a <code>Task</code> with attributes done, type, description etc.
     * @param readLine line of text to read
     * @param taskListToSave    task list to add tasks
     */
    private static void addTaskToArray(String readLine, ArrayList<Task> taskListToSave) {
        String toCommand = savedDataToCommandFormat(readLine);
        Task taskToAdd = Parser.parseInputAsTask(toCommand);
        markTaskIfDone(readLine, taskToAdd);
        taskListToSave.add(taskToAdd);
    }

    private static void markTaskIfDone(String readLine, Task taskToAdd) {
        if (readLine.endsWith("true")) {
            taskToAdd.setDone(true);
        }
    }

    //assume that data is saved in the following manner:
    // [TYPE] | [DESC] |  [BY/AT] | [DONE], where 3rd field can be null if its just a 'todo'

    /**
     * Converts save data into simulated user input for easy convertability
     *  Note this does not mark the done attribute, which we do in <code>addTaskToArray</code>
     * @param readLine line of text to read
     * @return simulated user input to add a single task
     */
    private static String savedDataToCommandFormat(String readLine) {
        String[] separateData = readLine.split("\\|");
        switch (separateData[0]) {
        case ("D") :
            return separateData[1] + Divider.D.getDivisor() + separateData[2];
        case ("E") :
            return separateData[1] + Divider.E.getDivisor() + separateData[2];
        default:    //is a todo only
            return separateData[1];
        }
    }

    /**
     * Creates directory if directory folderName is not found.
     * @throws IOException case where directory not found
     */
    private static void checkAndAddDirectory() throws IOException {
        String home = new File("").getAbsolutePath();
        File dirCheck = new File(home + folderName);
        if (dirCheck.isDirectory()) {
            return;
        }
        System.out.println("Hey, I didn't find directory " + folderName);
        System.out.println("adding " + folderName + " into repository...");
        Files.createDirectories(Paths.get(home + folderName));
    }

}
