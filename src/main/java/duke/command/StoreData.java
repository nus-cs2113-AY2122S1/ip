package duke.command;

import Type.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//brute force save
public class StoreData extends Duke{
    private static final String folderName = "data/";
    private static final String fileName = "list" + ".txt";
    //print a task array list in easy to wrangle data format
    //format:
    //separated by lines, [TYPE] [DESC] -[DEADLINE]- [DONE]
    //within a function
    public static String printTaskAsString(Task t) {
        return t.toString() + '\n';
    }

    public static void saveList(ArrayList<Task> taskArrayList) throws IOException {
        checkAndAddDirectory();
        File newList = new File( folderName + fileName);
        FileWriter fw = new FileWriter(folderName + fileName);
        for (Task t : taskArrayList) {
            fw.write(printTaskAsString(t));
        }
        fw.close();
        System.out.println("successfully saved with directory :" + newList.getAbsolutePath());
    }
    //returns an array list, given a text file
    //format per LINE : [TASK_TYPE]|[DEADLINE]|[DESC]|[DONE]
    public static ArrayList<Task> readList(String filePath) throws IOException {
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
                addTaskToArray(tasksToRead, readLine);
            }
            return tasksToRead;
        } catch (FileNotFoundException e) {
            //create new file
            File f = new File(filePath);
            System.out.println("Hey, I didn't find list.txt in /data!");
            System.out.println("creating new file...");
            return tasksToRead;
        } catch (NullPointerException e) {
            return tasksToRead;
        }
    }

    private static void addTaskToArray(ArrayList<Task> toReadList, String readLine) {
        String toCommand = savedDataToCommandFormat(readLine);
        Task taskToAdd = parseInputAsTask(toCommand);
        markTaskIfDone(readLine, taskToAdd);
        toReadList.add(taskToAdd);
    }

    private static void markTaskIfDone(String readLine, Task taskToAdd) {
        if (readLine.endsWith("true")) {
            taskToAdd.setDone(true);
        }
    }

    //assume that data is saved in the following manner:
    // [TYPE] | [DESC] |  [BY/AT], where 3rd field can be null if its just a 'todo'
    private static String savedDataToCommandFormat(String readLine) {
        String[] separateData = readLine.split("\\|");
        switch (separateData[0]) {
        case ("D") :
            return separateData[1] + Duke.DEADLINE_DIVIDER + separateData[2];
        case ("E") :
            return separateData[1] + Duke.EVENT_DIVIDER + separateData[2];
        default:    //is a todo only
            return separateData[1];
        }
    }

    public static void writeNewFile(String filePath)  {
        try {
            checkAndAddDirectory();
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkAndAddDirectory() throws IOException {
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
