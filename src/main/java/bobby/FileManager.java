package bobby;
import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileManager {
    public static final String FILEPATH = "data/bobby.txt";

    public static void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public static Task parseDataToTask(String[] splitData) {
        String taskType = splitData[0].toLowerCase();
        String taskDoneStatus = splitData[1];
        String taskDescription = splitData[2].trim();
        String fullTaskDescription;

        Task task = new Task(taskDescription);

        switch (taskType) {
        case "todo":
            task = new ToDo(taskDescription);
            break;
        case "deadline":
            String by = splitData[3].trim();
            fullTaskDescription = String.format("%s /by %s", taskDescription, by);
            task = new Deadline(fullTaskDescription);
            break;
        case "event":
            String at = splitData[3].trim();
            fullTaskDescription = String.format("%s /at %s", taskDescription, at);
            task = new Event(fullTaskDescription);
            break;
        default:
            ResponseManager.printIOExceptionMessage();
            break;
        }

        if (taskDoneStatus.equals("X")) {
            task.markAsDone();
        }

        return task;
    }

    public static void bootUpData(String filePath, TaskManager taskManager) throws IOException {
        File f = new File(filePath);

        // if file does not exist
        if (!f.exists()) {
            createFile(f);
        }

        //parse saved data into taskList
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //check type of task
            String splitData[] = s.nextLine().split(",");
            Task task = parseDataToTask(splitData);
            taskManager.addTask(task);
        }
    }

    public static void saveToFile(Task[] taskList) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        int totalTasks = Task.getTotalTasks();
        String textToAdd = "";
        for (int i = 0; i < totalTasks; i ++){
            Task currentTask = taskList[i];
            textToAdd += currentTask.getFormattedFileDescription();
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


}
