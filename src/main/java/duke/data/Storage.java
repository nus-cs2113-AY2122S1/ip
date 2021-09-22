package duke.data;

import Type.Task;
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

    public Storage(String filePathToInput) {
        filePath = filePathToInput;
    }

    //print a task array list in easy to wrangle data format
    //format:
    //separated by lines, [TYPE] [deadline] [DESC] [ISDONE]
    //within a function
    public static String printTaskAsString(Task t) {
        return t.toString() + '\n';
    }

    public static void saveList(ArrayList<Task> taskList) throws IOException {
        checkAndAddDirectory();
        File newList = new File("data/list.txt");
        FileWriter fw = new FileWriter("data/list.txt");
        for (Task t : taskList) {
            fw.write(printTaskAsString(t));
        }
        fw.close();
        System.out.println("successfully saved with directory :" + newList.getAbsolutePath());
    }
    //returns an array list, given a text file
    //format per LINE : [TASK_TYPE]|[DEADLINE]|[DESC]|[DONE]
    public static ArrayList<Task> load() throws IOException {
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
            //create new file
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
        taskListToSave.add(taskToAdd);
    }

    //assume that data is saved in the following manner:
    // [TYPE] | [DESC] |  [BY/AT], where 3rd field can be null if its just a 'todo'
    private static String savedDataToCommandFormat(String readLine) {
        String[] separateData = readLine.split("\\|");
        switch (separateData[0]) {
        case ("D") :
            return separateData[1] + "/by" + separateData[2];
        case ("E") :
            return separateData[1] + "/at" + separateData[2];
        default:    //is a todo only
            return separateData[1];
        }
    }

    private void writeNewFile() throws IOException {
        checkAndAddDirectory();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkAndAddDirectory() throws IOException {
        String home = new File("").getAbsolutePath();
        File dirCheck = new File(home + "/data");
        if (dirCheck.isDirectory()) {
            return;
        }
        System.out.println("Hey, I didn't find directory /data");
        System.out.println("adding /data into repository...");
        Files.createDirectories(Paths.get(home + "/data"));
    }

}
