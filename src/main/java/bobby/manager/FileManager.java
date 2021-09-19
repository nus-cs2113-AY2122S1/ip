package bobby.manager;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.ToDo;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public abstract class FileManager {
    public static final String FILE_PATH = "data/bobby.txt";
    private static final String TASK_DONE_STATUS = "X";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    public static void createFile(File dataFile) {
        try {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            ResponseManager.printCreateFileErrorMessage();
        }
    }

    public static Task parseData(String[] splitData) {
        String taskType = splitData[0].toLowerCase();
        String taskStatus = splitData[1];
        String taskDescription = splitData[2].trim();
        String fullTaskDescription;

        Task task = new Task(taskDescription);

        switch (taskType) {
        case TODO_COMMAND:
            task = new ToDo(taskDescription);
            break;
        case DEADLINE_COMMAND:
            String by = splitData[3].trim();
            fullTaskDescription = String.format("%s /by %s", taskDescription, by);
            task = new Deadline(fullTaskDescription);
            break;
        case EVENT_COMMAND:
            String at = splitData[3].trim();
            fullTaskDescription = String.format("%s /at %s", taskDescription, at);
            task = new Event(fullTaskDescription);
            break;
        default:
            ResponseManager.printIOExceptionMessage();
            break;
        }

        if (taskStatus.equals(TASK_DONE_STATUS)) {
            task.markAsDone();
        }

        return task;
    }

    public static void bootUpData(TaskManager taskManager) {
        File dataFile = new File(FILE_PATH);
        try {
            if (!dataFile.exists()) {
                createFile(dataFile);
            }
            //parse saved data into taskList
            readFile(taskManager, dataFile);
        } catch (IOException e) {
            ResponseManager.printIOExceptionMessage();
        }
    }

    public static void readFile(TaskManager taskManager, File dataFile) throws IOException{
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            //check type of task
            String splitData[] = s.nextLine().split(",");
            Task task = parseData(splitData);
            taskManager.addTask(task);
        }
    }

    public static void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int totalTasks = taskList.size();
        String textToAdd = "";
        for (int i = 0; i < totalTasks; i ++){
            Task currentTask = taskList.get(i);
            textToAdd += currentTask.getFormattedFileDescription();
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


}
