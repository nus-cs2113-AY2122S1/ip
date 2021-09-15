package files;

import jarvis.Jarvis;
import features.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class FileAccess {
    private static final String FILE_PATH = "jarvis/jarvis.txt";
    private static final String DIVIDER = ",";

    public static void findTaskFile(ArrayList<Task> taskList) {
        File jarvisFile = new File(FILE_PATH);
        try {
            if (!jarvisFile.exists()) {
                jarvisFile.getParentFile().mkdirs();
            }
            if (jarvisFile.createNewFile()) {
                System.out.println("A new file has been created to store your tasks Sir!\n"
                        + "What would you like me to do Sir?\n"
                        + Jarvis.LINE);
            }
            else {
                System.out.println("Give me a moment to load up your tasks Sir!\n"
                        + ".\n" + ".\n" + "Done\n"
                        + "What would you like me to do Sir?\n"
                        + Jarvis.LINE);
                loadTasks(taskList, jarvisFile);
            }
        } catch (IOException e) {
            System.out.println(Jarvis.LINE_W_NL
                    + "There has been an error detected when creating a new file Sir!\n"
                    + "You might want to take a look at it.\n"
                    + Jarvis.LINE);
        }
    }

    private static void loadTasks(ArrayList<Task> taskList, File jarvisFile) {
        int lineCount = 0;
        try {
            Scanner j = new Scanner(jarvisFile);
            while(j.hasNext()){
                inputTask(taskList,j.nextLine(),lineCount);
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(Jarvis.LINE_W_NL
                    + "I can't seem to find the file Sir.\n"
                    + Jarvis.LINE);
        }

    }

    public static void inputTask(ArrayList<Task> taskList, String textLine, int lineCount) {
        String[] lineInputs = textLine.split(",");
        switch(lineInputs[0]){
        case "T":
            Jarvis.todoFileTask(lineInputs[2], taskList);
            break;
        case "D":
            Jarvis.deadlineFileTask(lineInputs[2], lineInputs[3], taskList);
            break;
        case "E":
            Jarvis.eventFileTask(lineInputs[2],lineInputs[3], taskList);
            break;
        default:
            return;
        }
        if (lineInputs[1].equals("1")) {
            taskList.get(lineCount).markAsDone();
        }
    }

    public static void fillJarvisFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.size(); i++){
                String type = taskList.get(i).getType();
                String doneStatus;
                if (taskList.get(i).isDone()){
                    doneStatus = "1";
                } else {
                    doneStatus = "0";
                }
                writer.write(type + DIVIDER + doneStatus + DIVIDER + taskList.get(i).getDescription() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Jarvis.LINE_W_NL
                    + "There seems to be an error saving the task Sir.\n"
                    + Jarvis.LINE);
        }

    }

}
