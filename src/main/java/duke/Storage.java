package duke;

/* Importing some packages to help  store and read data from files */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads the previous tasks into the scheduledTasks(Task List) from the file (Duke.txt) at the start of the execution.
 * Saves the tasks to the file when user creates them.
 * Updates the file in case there is any deletion of the task.
 * Updates the tasks as "done" when user marks them as done in the scheduledTasks Task List.
 */
public class Storage {
    /**
     * Initializing initials of the three types of tasks.
     */
    public static final String INITIAL_TODO = "T";
    public static final String INITIAL_DEADLINE = "D";
    public static final String INITIAL_EVENT = "E";

    /**
     * Storing some Strings as delimiters.
     */
    public static final String DELIMITER_ARROW = "=>";
    public static final String DELIMITER_SPACE = " ";

    /**
     * Storing some flag values
     */
    public static final String TASK_COMPLETED = "1";
    public static final String TASK_INCOMPLETE = "0";
    public static final String TASK_MARKED_COMPLETE = "X";

    /**
     * Storing the path of the file which will record the tasks for future reference.
     */
    private static final String FILE_PATH = "duke.txt";

    /**
     * Calls the loadPreviousData() function and catches the encountered exceptions(if any).
     * Creates a new file at the FILE_PATH if file is not found.
     */
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

    /**
     * Load the tasks from the Duke.txt file into the current taskList
     *
     * @throws FileNotFoundException if file is not found
     */
    public static void loadPreviousData(TaskList taskList) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadSavedTasksToList(sc.nextLine(), taskList);
        }
    }

    /**
     * Loads a particular task from the Duke.txt file and stores it in the memory in the scheduledTasks Task List.
     */
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
            break;
        }

        if (taskStatus.equals(TASK_COMPLETED)) {
            taskList.scheduledTasks.get(TaskList.scheduledTasks.size() - 1).markAsDone();
        }

    }

    /**
     * Calls the function to save the tasks from the scheduledTasks task List into the file-> Duke.txt
     * Catches exceptions if encountered and displays appropriate error messages.
     */
    public static void callSaveTaskToList(ArrayList<Task> scheduledTasks) {
        try {
            saveTaskToDisk(scheduledTasks);
        } catch (IOException e) {
            System.out.println("Unable to save data to the disk");
        }
    }


    /**
     * Writes the tasks to the file -> Duke.txt, from the scheduledTasks task list.
     *
     * @param lineToWrite     LineToWrite stores the String containing the task that will be stored in the file Duke.txt
     * @param taskStatus      TaskStatus stores the completion status of the task
     * @param taskDescription TaskDescription stores the description of the task.
     */
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

}
