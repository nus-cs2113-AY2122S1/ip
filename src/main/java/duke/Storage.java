package duke;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import static duke.TaskList.*;

public class Storage {

    //Saves Task list into local file
    public static void saveData(ArrayList<Task> t) {
        try {
            String path = new File("userData.txt").getAbsolutePath();
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (int i = 0; i < taskCount; i++) {
                String input = t.get(i).toSave() + "\n";
                Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
            }
        }  catch (IOException e) {
            System.out.println("ERROR: Could not write to file");
        }
    }

    //Loads Task list into Duke
    public static void loadData() throws FileNotFoundException, DukeException {
        String path = new File("userData.txt").getAbsolutePath();
        File f = new File(path);

        Scanner scan = new Scanner(f);
        String taskType;
        String s;
        int taskNumber = 1; //tracks how many tasks read from .txt file so far

        while(scan.hasNext())                                                                                           //todo hello | 1
        {                                                                                                               //deadline hello /by Sunday | 1
            String data = scan.nextLine();                                                                              //event project meeting /at Mon 2-4pm | 0
            String[] arrayString = data.split(" \\| ");
            String[] arrayString2 = arrayString[0].split(" ");
            taskType = arrayString2[0];
            s = Integer.toString(taskNumber);

            switch (taskType) {
            case "todo":
                sayTodo(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            case "deadline":
                sayDeadline(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            case "event":
                sayEvent(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            default:
            }
            taskNumber++;
        }
        scan.close();
    }
}
