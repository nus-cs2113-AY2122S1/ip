package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Database class gets the filepath from Logic class and handles the saving of data and uploading
 * of data to Logic class
 *
 * @param "filepath" filepath of storage file, duke.txt.
 */
public class Database{
    private static String filePath;

    public Database(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task from duke.txt data file if it exists. If the file does
     * not exist, the file is created.
     *
     * @return "tasksCopy" previous state of tasks if the data file exists,
     * or an empty file if the data file does not exist.
     */
    public static TaskList loadTasks() {
        TaskList tasksCopy = new TaskList();

        File storedFile;
        try {
            storedFile = new File(filePath);
            //if savedFile is not present and a new file is created
            if (storedFile.createNewFile()) {
                return tasksCopy;
            } else {
                Scanner readFile = new Scanner(storedFile);
                while (readFile.hasNext()) {
                    duke.Logic.listIndex++;
                    String fileLine = readFile.nextLine();
                    Scanner lineData = new Scanner(fileLine);
                    lineData.useDelimiter("\\|");
                    String commandType = lineData.next().trim();
                    String isDone = lineData.next().trim();
                    String taskDescription = lineData.next().trim();

                    switch (commandType) {
                    case "T":
                        Todo savedTodo = new Todo(taskDescription);
                        if (isDone.equals("1")) {
                            savedTodo.setDone();
                        }
                        tasksCopy.addTask(savedTodo);
                        break;
                    case "D":
                        String deadLineDate = lineData.next().trim();
                        Deadline savedDeadLine = new Deadline(taskDescription, deadLineDate);
                        if (isDone.equals("1")) {
                            savedDeadLine.setDone();
                        }
                        tasksCopy.addTask(savedDeadLine);
                        break;
                    case "E":
                        String eventDate = lineData.next().trim();
                        Event savedEvent = new Event(taskDescription, eventDate);
                        if (isDone.equals("1")) {
                            savedEvent.setDone();
                        }
                        tasksCopy.addTask(savedEvent);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Oops! An error cropped up while loading file!");
        }
        return tasksCopy;
    }

    /**
     * Saves the entire taskList into data storage file.
     *
     * @param "taskList" the current list of tasks
     */
    public static void saveFile(TaskList taskList) {
        try {
            File database;
            database = new File("duke.txt");
            database.createNewFile();
        } catch (IOException e) {
            System.out.println("Oops! An error cropped up while saving");
        }
        String savedData = "";
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            savedData += currTask.toStringStore();
            savedData += "\n";
        }
        try {
            FileWriter editor = new FileWriter("duke.txt");
            editor.write(savedData);
            editor.close();
        } catch (IOException e) {
            System.out.println("Oops! An error occurred during saving.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the data storage file by appending the most
     * recently added file to the end of the file.
     *
     * @param "taskList" the current list of tasks
     */
    public static void autoSaveFile(TaskList taskList) {
        Task currTask = taskList.getTask(taskList.getSize());
        String data = currTask.toStringStore() + "\n";
        try {
            FileWriter editor = new FileWriter("duke.txt", true);
            editor.write(data);
            editor.close();
        } catch (IOException e) {
            System.out.println("Oops! An error occurred during autosave.");
            e.printStackTrace();
        }
    }
}
