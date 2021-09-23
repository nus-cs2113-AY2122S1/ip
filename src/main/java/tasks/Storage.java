package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    final public static String saveFilesDir = System.getProperty("user.dir") + File.separator + "saveFiles.txt";
    protected static void saveTasks(ArrayList<Tasks> tasksAL) {
        try {
            FileWriter myWriter = new FileWriter(saveFilesDir); //directory
            for (Tasks task : tasksAL) {
                myWriter.write(task.getTaskData());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException error while saving tasks.");
        }
    }

    public static void loadSaves(ArrayList<Tasks> tasksAL) {
        try {
            File file = new File(saveFilesDir);
            if (file.createNewFile()) {
                System.out.println("No save file detected. New save file created.");
            } else {
                tasksAL.clear();
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    loadTasks(myReader.nextLine(), tasksAL);
                }
                myReader.close();
                System.out.println("Save file detected. Save file has been loaded.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No save file detected. New save file created.");
        } catch (IOException e) {
            System.out.println("IOException error while loading tasks.");
            e.printStackTrace();
        }
    }

    private static void loadTasks(String input, ArrayList<Tasks> tasksAL) {
        String[] data = input.split(",");
        switch (data[0]){
        case "T":
            tasksAL.add(new TodoTasks(data[2]));
            break;
        case "D":
            tasksAL.add(new DeadlineTasks(data[2], data[3]));
            break;
        case "E":
            tasksAL.add(new EventTasks(data[2], data[3]));
            break;
        default:
            TaskList.checkComplete(data[1], tasksAL);
        }
    }
}
