package FileManager;

import InputHandle.Tasks.TaskList;
import InputHandle.Tasks.Task;
import InputHandle.Tasks.Todo;
import InputHandle.Tasks.Event;
import InputHandle.Tasks.Deadline;


import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    private Path filePath;

    public FileReader(String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get(System.getProperty("user.dir"), "UserStatus", fileName);
    }

    public FileReader() {
        String fileName = "default.txt";
        filePath = Paths.get(System.getProperty("user.dir"),"UserStatus", fileName);
    }

    public TaskList restore() {
        try {
            Scanner fileReader = new Scanner(filePath);
            TaskList tasks = new TaskList();
            while (fileReader.hasNext()) {
                tasks.addTask(convertToTask(fileReader.nextLine()));
            }
            fileReader.close();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean fileExists() {
        return filePath.toFile().exists();
    }

    private Task convertToTask(String taskLine) {
        String[] split = taskLine.split("\\|");
        switch(split[0].strip()) {
        case "T" :
            return new Todo(split[2].strip(), split[1].strip().equals("1"));
        case "D":
            return new Deadline(split[2].strip(), split[3].strip(), split[1].strip().equals("1"));
        case "E":
            return new Event(split[2].strip(), split[3].strip(), split[1].strip().equals("1"));
        }
        return null;
    }

}
