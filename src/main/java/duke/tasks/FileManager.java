package duke.tasks;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static final String FILE_PATH = "data/duke.txt";
    private File taskFile;

    public FileManager() {
        taskFile = new File(FILE_PATH);
    }

    public void writeToDoToFile(Task task, int taskNumber, TaskManager taskManager) {
        try {
            appendToFile("T" + " | " + task.getStatusIcon() + " | " + task.getDescription());
            if (taskNumber != taskManager.getNumberOfTasksAdded() - 1) {
                appendToFile(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Oops, something went wrong! " + e.getMessage());
        }
    }

    public void writeDeadlineToFile(Deadline deadline, int taskNumber, TaskManager taskManager) {
        try {
            appendToFile("D" + " | " + deadline.getStatusIcon() + " | "
                    + deadline.getDescription() + " | " + deadline.getBy());
            if (taskNumber != taskManager.getNumberOfTasksAdded() - 1) {
                appendToFile(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Oops, something went wrong! " + e.getMessage());
        }
    }

    public void writeEventToFile(Event event, int taskNumber, TaskManager taskManager) {
        try {
            appendToFile("E" + " | " + event.getStatusIcon() + " | "
                    + event.getDescription() + " | " + event.getAt());
            if (taskNumber != taskManager.getNumberOfTasksAdded() - 1) {
                appendToFile(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Oops, something went wrong! " + e.getMessage());
        }
    }

    /**
     * Inputs the whole task file to the task list
     *
     * @param taskFile the task file to be read
     * @param taskManager the task manager class to access add task methods
     * @throws FileNotFoundException if the file is not found in the directory
     */
    public void retrieveFile(File taskFile, TaskManager taskManager) throws FileNotFoundException {
        File f = taskFile;
        Scanner s = new Scanner(f);
        int taskNumber = 1;
        while (s.hasNext()) {
            String input = s.nextLine();
            fillArrayFromFile(input, taskManager, taskNumber);
            taskNumber++;
        }
    }

    /**
     * Inputs the task from the task file to the task list one by one
     *
     * @param input the task to be inputted to the task list
     * @param taskManager the task manager class to access add task methods
     * @param taskNumber the ith number of task from the top of the list
     */
    public void fillArrayFromFile(String input, TaskManager taskManager, int taskNumber) {
        String[] taskItem = input.split("\\|");
        String taskType = taskItem[0].trim();
        String statusIcon = taskItem[1].trim();
        String taskName = taskItem[2].trim();

        switch (taskType) {
        case ("T"):
            taskManager.addToDoTaskToList(taskName);
            if (statusIcon.contains("X")) {
                taskManager.markTaskAsDone("done " + taskNumber);
            }
            break;
        case ("D"):
            String taskDeadline = taskItem[3];
            taskManager.addDeadlineTaskToList(taskName + "/by " + taskDeadline);
            if (statusIcon.contains("X")) {
                taskManager.markTaskAsDone("done " + taskNumber);
            }
            break;
        case ("E"):
            String taskTime = taskItem[3];
            taskManager.addEventTaskToList(taskName + "/at " + taskTime);
            if (statusIcon.contains("X")) {
                taskManager.markTaskAsDone("done " + taskNumber);
            }
            break;
        }
    }

    /**
     * Writes the taskList array to the text file
     *
     * @param taskManager the task manager class to access the task list
     */
    public void writeArrayToFile(TaskManager taskManager) {
        ArrayList<Task> taskList = taskManager.getTaskList();
        try {
            writeToFile("");
        } catch (IOException e) {
            System.out.println("Oops!" + e.getMessage());
        }
        for (int i = 0; i < taskManager.getNumberOfTasksAdded(); i++) {
            String taskType = taskList.get(i).toString().substring(1, 2);
            switch (taskType) {
            case ("T"):
                writeToDoToFile(taskList.get(i), i, taskManager);
                break;
            case ("D"):
                Deadline deadline = (Deadline) taskList.get(i);
                writeDeadlineToFile(deadline, i, taskManager);
                break;
            case ("E"):
                Event event = (Event) taskList.get(i);
                writeEventToFile(event, i , taskManager);
                break;
            }
        }
    }

    /**
     * Rewrite text to file
     *
     * @param textToWrite the text to be written in the file
     * @throws IOException if the file cannot be written to
     */
    public void writeToFile(String textToWrite) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(textToWrite);
        writer.close();
    }

    /**
     * Appends text to file
     *
     * @param textToAppend the text to be appended to the file
     * @throws IOException if the file cannot be written to
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH, true);
        writer.write(textToAppend);
        writer.close();
    }
}
