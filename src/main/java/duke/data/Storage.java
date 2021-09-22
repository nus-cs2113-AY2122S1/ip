package duke.data;

import Type.Task;
import Type.task.Divider;
import duke.startup.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{
    private static String filePath;
    private static String folderName = "/data";
    private static String fileName = "list.txt";
    public Storage(String filePathToInput) {
        filePath = filePathToInput;
    }

    //print a task array list in easy to wrangle data format
    //format:
    //separated by lines, [TYPE] [DESC] -[DEADLINE]- [DONE]
    //within a function
    public static String printTaskAsString(Task t) {
        return t.toString() + '\n';
    }

    public static void saveList(TaskList taskList) throws IOException {
        checkAndAddDirectory();
        File newList = new File(folderName + fileName);
        FileWriter fw = new FileWriter(folderName + fileName);
        for (Task t : taskList.getTaskList()) {

            fw.write(printTaskAsString(t));
        }
        fw.close();
    }

    //returns an array list, given a text file
    //format per LINE : [TASK_TYPE]|[DEADLINE]|[DESC]|[DONE]
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
    private static String savedDataToCommandFormat(String readLine) {
        String[] separateData = readLine.split("\\|");
        switch (separateData[0]) {
        case ("D") :
            return separateData[1] + '/' + Divider.by + separateData[2];
        case ("E") :
            return separateData[1] + '/' + Divider.at + separateData[2];
        default:    //is a todo only
            return separateData[1];
        }
    }
    
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

    public static String getFolderName() {
        return folderName;
    }

}
