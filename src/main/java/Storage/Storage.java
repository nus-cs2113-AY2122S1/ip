package Storage;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static final String FILEPATH = "data\\duke.txt";

    /**public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        //FileWriter fw = new FileWriter(FILEPATH);
        PrintWriter writer = new PrintWriter(FILEPATH);
        for (Task task : tasks) {
            //fw.write(task.toString() + System.lineSeparator());
            writer.println(task.toString() + System.lineSeparator());
        }
        writer.close();
    }*/

    public static void saveToFile(ArrayList<Task> tasks) throws FileNotFoundException {
        try {
            File directory = new File(FILEPATH);
            if (!directory.exists()) {
                directory.mkdir();
                directory.createNewFile();
            }
        } catch (IOException e) {
            Ui.showLoadingError();
        }

        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split("\\|");
                String taskType = parts[0].trim();
                boolean isDone = Boolean.parseBoolean(parts[1].trim());
                String taskDescription = parts[2].trim();
                Task task;
                switch (taskType) {
                case "todo":
                    task = new ToDo(taskDescription);
                    break;
                case "deadline":
                    task = new Deadline(taskDescription, parts[3]);
                    break;
                case "event":
                    task = new Event(taskDescription, parts[3]);
                    break;
                default:
                    throw new FileNotFoundException();
                }
                if (isDone) {
                    task.setDone();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file\n");
        }

        PrintWriter writer = new PrintWriter(FILEPATH);
        for (Task task : tasks) {
            //fw.write(task.toString() + System.lineSeparator());
            writer.println(task.toString() + System.lineSeparator());
        }
        writer.close();
    }
}

