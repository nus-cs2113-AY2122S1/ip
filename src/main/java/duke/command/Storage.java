package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected Task[] tasks;

    public Storage(String filePath, Task[] tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public static int loadFromFile(String filePath, Task[] tasks) {
        int taskNumber = 0;
        try {
            File f = new File(filePath);
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String task = s.nextLine();
                    String[] words = task.split(" \\| ");
                    switch (words[0]) {
                    case "T":
                        tasks[taskNumber] = new ToDo(Integer.parseInt(words[1]), words[2]);
                        taskNumber++;
                        break;
                    case "D":
                        tasks[taskNumber] = new Deadline(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                        break;
                    case "E":
                        tasks[taskNumber] = new Event(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                        break;
                    }
                }
            }
            return taskNumber;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveToFile(String filePath, Task[] tasks, int taskNumber) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskNumber; i++) {
                switch (tasks[i].getType()) {
                case "todo":
                    fw.write("T" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + System.lineSeparator());
                    break;
                case "deadline":
                    fw.write("D" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + " | " + tasks[i].getTime() + System.lineSeparator());
                    break;
                case "event":
                    fw.write("E" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + " | " + tasks[i].getTime() + System.lineSeparator());
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
