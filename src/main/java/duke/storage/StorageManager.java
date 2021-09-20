package duke.storage;

import duke.Duke;
import duke.exception.DukeInvalidAddTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manage reading and writing task list from and to the specified text file.
 */
public class StorageManager {
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    
    private ArrayList<Task> tasks;
    
    private String filePath;

    /**
     * Constructor of the StorageManager object, initializing the path to the
     * text file.
     * 
     * @param filePath path to text file containing the task list.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Return the task type of task stored in text file.
     * 
     * @param input task stored in text file.
     * @return the type of the task.
     */
    private String getTaskType(String input) {
        String[] words = input.split("--");
        return words[0];
    }

    /**
     * Return the task description of task stored in text file.
     * 
     * @param input task stored in text file.
     * @return the description of the task.
     */
    private String getTaskDescription(String input) {
        String[] words = input.split("--");
        return words[2];
    }

    /**
     * Return the date of task stored in text file.
     *
     * @param input task stored in text file.
     * @return the date of the task.
     */
    private String getTaskDate(String input) {
        String[] words = input.split("--");
        return words[3];
    }

    /**
     * Check if the task has already been marked as done.
     * 
     * @param input task stored in text file.
     * @return true if the task is marked as done, false otherwise.
     */
    private boolean isMarkedDoneTask(String input) {
        String[] words = input.split("--");
        return words[1].equals("1");
    }

    /**
     * Read each task stored in the text file and convert them to a task
     * to be added to the task list.
     *
     * @param input a line inside the text file containing the task type, done
     *              status, and description.
     * @return the task created from the input description.
     */
    private Task getTask(String input) {
        String taskType = getTaskType(input);
        String taskDescription = getTaskDescription(input);
        Task task = null;
        
        try {
            switch (taskType) {
            case TASK_TODO:
                task = new Todo(taskDescription);
                break;
            case TASK_DEADLINE:
                String deadlineDate = getTaskDate(input);
                task = new Deadline(taskDescription + " /by " + deadlineDate);
                break;
            case TASK_EVENT:
                String eventDate = getTaskDate(input);
                task = new Event(taskDescription + " /at " + eventDate);
                break;
            default:
                Duke.getUi().printInvalidTaskInFileMessage();
                break;
            }
        } catch (DukeInvalidAddTaskException | ArrayIndexOutOfBoundsException e) {
            System.out.println("INVALID TASK FOUND IN FILE");
        }
        
        if (task != null && isMarkedDoneTask(input)) {
            task.markAsDone();
        }
        
        return task;
    }

    /**
     * Get the tasks stored in the text file, keep reading the stored tasks and
     * adding each of them to the task list until the last line in the text file.
     * 
     * @return list of tasks stored in the file
     * @throws FileNotFoundException if the text file doesn't exist.
     */
    private ArrayList<Task> getStoredList() throws FileNotFoundException {
        tasks = new ArrayList<>();
        File inputFile = new File(filePath);
        Scanner input = new Scanner(inputFile);
        String inputTask;
        while (input.hasNext()) {
            inputTask = input.nextLine();
            tasks.add(getTask(inputTask));
        }
        return tasks;
    }

    /**
     * Read the tasks stored in the text file. If the file doesn't exist, 
     * return an empty list.
     * 
     * @return task list stored in the file.
     */
    public ArrayList<Task> readFile() {
        try {
            return getStoredList();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Check if the text file exists or not. If not, then create a new one.
     */
    private void checkFilePath() {
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.getParentFile().mkdirs();
        }
    }

    /**
     * Write each task in the task list to the text file.
     * 
     * @throws IOException if the text file doesn't exist.
     */
    private void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            String taskAsString = String.valueOf(task);
            String taskType = taskAsString.substring(1, 2);
            String doneSymbol = task.isDone() ? "1" : "0";
            String formattedTask = null;
            switch (taskType) {
            case "T":
                formattedTask = "T--" + doneSymbol + "--" + task.getDescription();
                break;
            case "D":
                Deadline currentDeadline = (Deadline) task;
                formattedTask = "D--" + doneSymbol + "--" + currentDeadline.getDescription() + "--" + currentDeadline.getDeadlineDate();
                break;
            case "E":
                Event currentEvent = (Event) task;
                formattedTask = "E--" + doneSymbol + "--" + currentEvent.getDescription() + "--" + currentEvent.getEventTime();
                break;
            default:
                System.out.println("Invalid Task Found");
                break;
            }
            writer.write(formattedTask + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Update the text file with the task list provided.
     * 
     * @param tasks task list provided.
     */
    public void updateStorage(ArrayList<Task> tasks) {
        this.tasks = tasks;
        checkFilePath();
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
