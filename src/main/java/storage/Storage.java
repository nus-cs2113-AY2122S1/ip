package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;


public class Storage {
    private final String DEFAULT_SAVE_DIRECTORY = "data";
    private final String DEFAULT_SAVE_FILE = "duke.txt";

    public Storage() {
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        ArrayList<String> stringFormattedTasks;
        File saveDir = new File(DEFAULT_SAVE_DIRECTORY);
        saveDir.mkdir();
        File saveFile = new File(saveDir, DEFAULT_SAVE_FILE);
        try {
            saveFile.createNewFile();
            FileWriter fw = new FileWriter(saveFile);
            stringFormattedTasks = getTasksAsStringArrayList(tasks);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(stringFormattedTasks.get(i) + Ui.LINE_BREAK);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    /**
     * Formats the list of Tasks and
     * returns a List of Tasks formatted as string
     *
     * @return ArrayList of String formatted Tasks
     */
    private ArrayList<String> getTasksAsStringArrayList(ArrayList<Task> tasks) {
        ArrayList<String> stringFormattedTasks = new ArrayList<>();
        for (Task task : tasks) {
            char taskIdentifier = task.toString().charAt(1);
            String temp = taskIdentifier + "||" + task.isDone() + "||" + task.getTaskName();
            if (taskIdentifier == 'D') {
                temp += "||" + ((Deadline) task).getByWhen();
            } else if (taskIdentifier == 'E') {
                temp += "||" + ((Event) task).getAtWhen();
            }
            stringFormattedTasks.add(temp);
        }
        return stringFormattedTasks;
    }

    public void instantiateTasksFromFile(ArrayList<Task> tasks) {
        File saveDir = new File(DEFAULT_SAVE_DIRECTORY);
        saveDir.mkdir();
        File savedFile = new File(saveDir, DEFAULT_SAVE_FILE);
        if (!savedFile.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(savedFile);
            while (fileScanner.hasNext()) {
                String fileLine = fileScanner.nextLine();
                Task savedTask = createSavedTask(fileLine);
                tasks.add(savedTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Task createSavedTask(String fileLine) {
        String[] tokens = fileLine.split("\\|\\|");
        String taskType = tokens[0];
        String taskName = tokens[2];
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        Task savedTask;
        switch (taskType) {
        case "D":
            savedTask = new Deadline(taskName, isDone, tokens[3]);
            break;
        case "E":
            savedTask = new Event(taskName, isDone, tokens[3]);
            break;
        default:
            savedTask = new Todo(taskName, isDone);
        }
        return savedTask;
    }
}
