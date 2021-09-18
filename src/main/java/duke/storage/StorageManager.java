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

public class StorageManager {
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    
    private ArrayList<Task> tasks;
    
    private String filePath;
    
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }
    
    private String getTaskType(String input) {
        String[] words = input.split("--");
        return words[0];
    }
    
    private String getTaskDescription(String input) {
        String[] words = input.split("--");
        return words[2];
    }
    
    private String getTaskDate(String input) {
        String[] words = input.split("--");
        return words[3];
    }

    private boolean isMarkedDoneTask(String input) {
        String[] words = input.split("--");
        return words[1].equals("1");
    }
    
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
    
    public ArrayList<Task> readFile() {
        try {
            return getStoredList();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void checkFilePath() {
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.getParentFile().mkdirs();
        }
    }

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
        checkFilePath();
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
