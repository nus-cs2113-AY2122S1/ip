package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;
    private TaskList currentData;

    public static final String SAVEFILE_SEPERATOR = "\\|";

    /**
     * Constructor to create Storage object.
     *
     * @param filepath intended filepath to store data.
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Read file and extract file into TaskList.
     *
     * @param taskList Most Recent ArrayList taskList.
     * @return Latest TaskList.
     * @throws FileNotFoundException if file has never been created.
     */
    public TaskList loadFile(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(this.filepath);
        Scanner s = new Scanner(f);
        currentData = new TaskList(taskList);
        while (s.hasNext()) {
            currentData = loadCommands(s.nextLine(), currentData.getList());
        }
        return currentData;
    }

    /**
     * Creates a new file.
     */
    public void createFile() {
        File f = new File(this.filepath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert stored line of data into current TaskList.
     *
     * @param taskDetails Saved data from save file.
     * @param taskList    Current ArrayList task List.
     * @return Current TaskList.
     */
    private static TaskList loadCommands(String taskDetails, ArrayList<Task> taskList) {
        String[] taskBreakdown = taskDetails.split(SAVEFILE_SEPERATOR);
        String taskType = taskBreakdown[0].trim();
        String completionStatus = taskBreakdown[1].trim();
        String task = taskBreakdown[2].trim();
        Task savedTask;
        switch (taskType) {
        case "T":
            savedTask = new ToDo(task);
            break;
        case "D":
            savedTask = new Deadline(task, taskBreakdown[3].trim());
            break;
        case "E":
            savedTask = new Event(task, taskBreakdown[3].trim());
            break;
        default:
            savedTask = null;
        }
        if (completionStatus.equals("1")) {
            savedTask.markAsDone();
        }
        taskList.add(savedTask);
        return new TaskList(taskList);
    }

    /**
     * Input stored data into save file.
     *
     * @param filePath  filePath of save file.
     * @param textToAdd String to be written.
     * @throws IOException when Run into execution.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter dukeIn = new FileWriter(filePath);
        dukeIn.write(textToAdd);
        dukeIn.close();
    }

    /**
     * Automatically ArrayList Tasks.
     *
     * @param taskArrayList current ArrayList.
     */
    public void autoSave(ArrayList<Task> taskArrayList) {
        String dukeUpdate = "";
        for (Task task : taskArrayList) {
            if (task != null) {
                String completionStatus = task.isDone() ? "1" : "0";
                dukeUpdate = dukeUpdate + task.getType() + " | " + completionStatus + " | " + task.getTask();
                dukeUpdate = (task instanceof Deadline || task instanceof Event) ? dukeUpdate + " | " + task.getMoreDetails() : dukeUpdate;
                dukeUpdate = dukeUpdate + "\n";
            }
        }
        try {
            writeToFile(this.filepath, dukeUpdate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
