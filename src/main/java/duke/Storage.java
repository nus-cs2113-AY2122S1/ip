package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String INITIAL_TODO = "T";
    public static final String INITIAL_DEADLINE = "D";
    public static final String INITIAL_EVENT = "E";

    public static final String DELIMITER_ARROW = "=>";
    public static final String DELIMITER_SPACE = " ";

    public static final String TASK_COMPLETED = "1";
    public static final String TASK_INCOMPLETE = "0";
    public static final String TASK_MARKED_COMPLETE = "X";

    private static final String FILE_PATH = "duke.txt";

    public static void loadData(TaskList taskList) {
        try {
            loadPreviousData(taskList);
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            try {
                file.createNewFile();
            } catch (IOException ee) {
                System.out.println("Cannot create a new file");
            }
        }
    }

    public static void loadPreviousData(TaskList taskList) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadSavedTasksToList(sc.nextLine(), taskList);
        }
    }

    public static void loadSavedTasksToList(String input, TaskList taskList) {
        String[] splitInput = input.split(DELIMITER_ARROW);
        String taskType = splitInput[0].trim();
        String taskStatus = splitInput[1].trim();
        String taskDescription = splitInput[2].trim();

        switch (taskType) {
        case INITIAL_TODO:
            taskList.scheduledTasks.add(new Todo(taskDescription));
            break;
        case INITIAL_DEADLINE:
            String timeDueBy = splitInput[3];
            taskList.scheduledTasks.add(new Deadline(taskDescription, timeDueBy));
            break;
        case INITIAL_EVENT:
            String timeDueAt = splitInput[3];
            taskList.scheduledTasks.add(new Event(taskDescription, timeDueAt));
            break;
        default:
        }

        if (taskStatus.equals(TASK_COMPLETED)) {
            taskList.scheduledTasks.get(TaskList.scheduledTasks.size() - 1).markAsDone();
        }

    }

    public static void saveTaskToDisk(ArrayList<Task> scheduledTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        String lineToWrite = "";
        for (Task task : scheduledTasks) {
            lineToWrite = "";
            if (task.taskType == TaskType.TODO) {
                lineToWrite = INITIAL_TODO + DELIMITER_SPACE;
            } else if (task.taskType == TaskType.DEADLINE) {
                lineToWrite = INITIAL_DEADLINE + DELIMITER_SPACE;
            } else {
                lineToWrite = INITIAL_EVENT + DELIMITER_SPACE;
            }

            lineToWrite = lineToWrite + DELIMITER_ARROW + DELIMITER_SPACE;
            String taskStatus = task.getStatus();

            if (taskStatus.equals(TASK_MARKED_COMPLETE)) {
                lineToWrite = lineToWrite + TASK_COMPLETED;
            } else {
                lineToWrite = lineToWrite + TASK_INCOMPLETE;
            }

            String taskDescription = task.description;

            lineToWrite = lineToWrite + " => " + taskDescription;
            if (task instanceof Deadline) {
                lineToWrite = lineToWrite + " => " + ((Deadline) task).by;
            } else if (task instanceof Event) {
                lineToWrite = lineToWrite + " => " + ((Event) task).at;
            }

            lineToWrite += "\n";
            fw.write(lineToWrite);
        }
        fw.close();
    }

    public static void callSaveTaskToList(ArrayList<Task> scheduledTasks) {
        try {
            saveTaskToDisk(scheduledTasks);
        } catch (IOException e) {
            System.out.println("Unable to save data to the disk");
        }
    }
}
