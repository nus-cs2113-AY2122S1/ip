package duke.command;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//brute force save
public class StoreData {
    //print a task array list in easy to wrangle data format
    //format:
    //separated by lines, [TYPE] [deadline] [DESC] [ISDONE]
    //within a function
    public static String printTaskAsString(Task t) {
        return t.toString() + '\n';
    }

    public static void saveList(ArrayList<Task> taskArrayList) throws IOException {
        checkAndAddDirectory();
        File newList = new File("data/list.txt");
        FileWriter fw = new FileWriter("data/list.txt");
        for (Task t : taskArrayList) {
            fw.write(printTaskAsString(t));
        }
        fw.close();
        System.out.println("successfully saved with directory :" + newList.getAbsolutePath());
    }
    //returns an array list, given a text file
    //format per LINE : [TASK_TYPE]|[DEADLINE]|[DESC]|[DONE]
    public static ArrayList<Task> readList(String filePath) throws IOException {
        ArrayList<Task> toRead = new ArrayList<Task>();
        try {
            checkAndAddDirectory();
            File f = new File(filePath);
            Scanner scanList = new Scanner(f);
            while (scanList.hasNext()) {
                //add to a list
                String readLine = scanList.nextLine();
                if (readLine.equals("")) {
                    break;
                }
                addTaskToArray(toRead, readLine);
            }
            return toRead;
        } catch (FileNotFoundException e) {
            //create new file
            File f = new File(filePath);
            System.out.println("Hey, I didn't find list.txt in /data!");
            System.out.println("creating new file...");
            return toRead;
        } catch (NullPointerException e) {
            return toRead;
        }
    }

    private static void addTaskToArray(ArrayList<Task> toRead, String readLine) {
        String[] readLineSeparate = readLine.split("\\|");
        char type = readLineSeparate[0].charAt(0);
        String deadline = (readLineSeparate[1].equals("null"))? "" : readLineSeparate[1];
        deadline = deadline.trim();
        String description = readLineSeparate[2];
        description = description.trim();
        Boolean isDone = readLineSeparate[3].equals("true");
        String eventDescription = (type == 'E')? readLineSeparate[4] : "";
        Task toAdd;
        toAdd = new Task(type, deadline, description, isDone, eventDescription);
        toRead.add(toAdd);
    }

    public static void writeNewFile(String filePath) throws IOException {
        checkAndAddDirectory();
        try {
            FileWriter fw = new FileWriter("data/list.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkAndAddDirectory() throws IOException {
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
