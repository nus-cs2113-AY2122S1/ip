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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadFromFile(TaskList taskList) {
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
                        taskList.tasks[taskNumber] = new ToDo(Integer.parseInt(words[1]), words[2]);
                        taskNumber++;
                        break;
                    case "D":
                        taskList.tasks[taskNumber] = new Deadline(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                        break;
                    case "E":
                        taskList.tasks[taskNumber] = new Event(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                        break;
                    }
                }
            }
            taskList.taskNumber = taskNumber;
            return;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }
    }

    public static void saveToFile(String filePath, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.taskNumber; i++) {
                switch (taskList.tasks[i].getType()) {
                case "todo":
                    fw.write("T" + " | " + taskList.tasks[i].getStatusNumber() + " | " + taskList.tasks[i].getDescription() + System.lineSeparator());
                    break;
                case "deadline":
                    fw.write("D" + " | " + taskList.tasks[i].getStatusNumber() + " | " + taskList.tasks[i].getDescription() + " | " + taskList.tasks[i].getTime() + System.lineSeparator());
                    break;
                case "event":
                    fw.write("E" + " | " + taskList.tasks[i].getStatusNumber() + " | " + taskList.tasks[i].getDescription() + " | " + taskList.tasks[i].getTime() + System.lineSeparator());
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
