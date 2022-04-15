package storage;
import java.util.Scanner;

import exceptions.CorruptedFileException;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import ui.TextUi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Represents the file used to store taskList data.
 */
public class Storage {

    private static TaskList taskList;

    private static final String FILE_PATH = "data/task_list.txt";
    private static final String FOLDER_PATH = "data";

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Initialises the TaskList based on the data in the data file.
     *
     * If the specified folder does not exist, a new Folder is created.
     *
     * Prints a message if data file accessed has invalid data.
     */
    public void initTaskList(){
        try {
            File dataFile = new File(FILE_PATH);
            Scanner s = new Scanner(dataFile); // create a Scanner using the dataFile as the source
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] taskTypeAndParams = task.split(" / ");
                String taskType = taskTypeAndParams[0];
                boolean isDone = taskTypeAndParams[1].equals("1");
                String taskParams =  taskTypeAndParams[2];
                int taskIndex = taskList.getSize();
                switch(taskType) {
                case ToDo.INITIAL:
                    taskList.addTask(new ToDo(taskParams));
                    if (isDone) {
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                case Deadline.INITIAL:
                    String[] taskDescriptionAndDeadline = taskParams.split(" \\| ");
                    String deadlineDescription = taskDescriptionAndDeadline[0];
                    String deadline = taskDescriptionAndDeadline[1];
                    taskList.addTask(new Deadline(deadlineDescription, deadline));
                    if (isDone) {
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                case Event.INITIAL:
                    String[] taskDescriptionAndTimeRange = taskParams.split(" \\| ");
                    String eventDescription = taskDescriptionAndTimeRange[0];
                    String timeRange = taskDescriptionAndTimeRange[1];
                    taskList.addTask(new Event(eventDescription, timeRange));
                    if (isDone) {
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                default:
                    throw new CorruptedFileException();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            createFolder();
        } catch (CorruptedFileException | ArrayIndexOutOfBoundsException e) {
            TextUi.showInvalidFileFormatMessage();
        }
    }

    private static void createFolder() {
        File folder = new File(FOLDER_PATH);
        boolean folderIsCreated = folder.mkdir();
        if (folderIsCreated) {
            TextUi.showNewFolderCreatedMessage();
        }
    }

    /**
     * Clears the current data file and appends every task from this taskList into the data file
     */
    public static void updateDataFile() {
        try {
            clearDataFile();
            for (Task task: taskList.getTaskList()) {
                String textToAppend = null;
                String isDone = task.getIsDone() ? "1" : "0";
                if (task instanceof ToDo) {
                    textToAppend = "T / " + isDone + " / " + task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String taskParams = deadline.getDescription() + " | " + deadline.getDeadline();
                    textToAppend = "D / " + isDone + " / " + taskParams;
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String taskParams = event.getDescription() + " | " + event.getEventTimeRange();
                    textToAppend = "E / " + isDone + " / " + taskParams;
                }
                appendToDataFile(textToAppend);
            }
        } catch (IOException e) {
            TextUi.showIOExceptionMessage(e);
        }
    }

    private static void clearDataFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write("");
        fileWriter.close();
    }

    /**
     * Appends a given text to the data file
     *
     * @param textToAppend the text to be appended
     * @throws IOException if fail to access or append to the data file
     */
    public static void appendToDataFile(String textToAppend) throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fileWriter.write(textToAppend + System.lineSeparator());
        fileWriter.close();
    }

}
