package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected static File save;

    public Storage(String filename) {
        this.filepath = filename;
    }

    static void load() throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        save = new File(directory + "/" + "duke1.txt");
        save.createNewFile();
        Scanner s = new Scanner(save);
        int counter = 1;
        while (s.hasNext()) {
            String[] parts = s.nextLine().split("\\|");
            String taskType = parts[0].trim();
            String status = parts[1].trim();
            String taskDescription = parts[2].trim();
            switch (taskType) {
            case "T":
                TaskList.addTask(Parser.TODO, taskDescription);
                break;
            case "D":
                TaskList.addTask(Parser.DEADLINE, taskDescription);
                break;
            case "E":
                TaskList.addTask(Parser.EVENT, taskDescription);
                break;
            }
            if (status.equals("X")) {
                TaskList.markTaskComplete(Integer.toString(counter));
            }
            counter++;
        }
    }

    static void writeToFile() throws IOException {
        String type, status, description;
        FileWriter fw = new FileWriter(save);
        for (int i = 0; i < TaskList.numberOfTasks; i++) {
            type = TaskList.taskList.get(i).getType();
            status = TaskList.taskList.get(i).getStatusIcon();
            description = TaskList.taskList.get(i).getOriginalDescription();
            fw.write(type + " | " + status + " | " + description + System.lineSeparator());
        }
        fw.close();
    }
}