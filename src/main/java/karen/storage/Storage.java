package karen.storage;
import karen.program.ProgramManager;
import karen.tasklist.TaskList;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.Task;
import karen.tasklist.task.ToDo;
import karen.ui.Ui;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Storage {
    private static final String FILE_PATH = "data/bobby.txt";
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
            Ui.printCreateFileErrorMessage();
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
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
            Ui.printIOExceptionMessage();
            break;
        }

        if (taskStatus.equals(TASK_DONE_STATUS)) {
            task.markAsDone();
        }

        return task;
    }

    public static void bootUpData(TaskList taskList) {
        File dataFile = new File(FILE_PATH);
        try {
            if (!dataFile.exists()) {
                createFile(dataFile);
            }
            //parse saved data into taskList
            readFile(taskList, dataFile);
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }

    public static void readFile(TaskList taskList, File dataFile) throws IOException{
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            //check type of task
            String splitData[] = s.nextLine().split(",");
            Task task = parseData(splitData);
            taskList.addTask(task);
        }
    }

    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int totalTasks = taskList.getTaskList().size();
        String textToAdd = "";
        for (int i = 0; i < totalTasks; i ++){
            Task currentTask = taskList.getTaskList().get(i);
            textToAdd += currentTask.getFormattedFileDescription();
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
