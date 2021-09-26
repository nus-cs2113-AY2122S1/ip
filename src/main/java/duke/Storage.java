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

    Storage(String filepath) {
        this.filepath = filepath;
    }

    public TaskList loadFile(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(this.filepath);
        Scanner s = new Scanner(f);
        currentData = new TaskList(taskList);
        while (s.hasNext()) {
            currentData = loadCommands(s.nextLine(), currentData.getList());
        }
        return currentData;
    }

    public void createFile() {
        File f = new File(this.filepath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter dukeIn = new FileWriter(filePath);
        dukeIn.write(textToAdd);
        dukeIn.close();
    }

    public void autoSave(ArrayList<Task> unfilteredTasks) {
        String dukeUpdate = "";
        for (Task task : unfilteredTasks) {
            if (task != null) {
                String completionStatus = task.isDone() ? "1" : "0";
                dukeUpdate = dukeUpdate + task.getType() + " | " + completionStatus + " | " + task.getTask();
                dukeUpdate = (task instanceof Deadline || task instanceof Event) ? dukeUpdate + " | " + task.getTime() : dukeUpdate;
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
