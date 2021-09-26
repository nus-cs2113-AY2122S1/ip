package storage;

import exceptions.JimException;
import ui.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static Ui ui;
    private static TaskList tasks;

    //Paths
    private static final String FILE_PATH = "data/jim.txt";
    private static final String FOLDER_PATH = "data";


    public Storage(TaskList tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    /**
     * Checks if the folder for the database exists. Creates one if it does not.
     *
     * @param folder The folder path.
     */
    public static void folderChecker(File folder) {
        boolean result = folder.mkdir();
        ui.showCheckFolderMessage();
        if (result) {
            ui.showCreateMissingFolderMessage();
        } else {
            ui.showFolderFoundMessage();
        }
    }

    /**
     * Checks if the database file exists. Creates one if it does not.
     *
     * @param file The file path for the database.
     */
    public static void databaseChecker(File file) {
        try {
            boolean result = file.createNewFile();
            ui.showCheckDatabaseFileMessage();
            if (result) {
                ui.showCreateMissingDatabaseFileMessage();
            } else {
                ui.showDatabaseFileFoundMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the task list using the data from the database file.
     *
     * @param s Scanner for database file.
     * @throws JimException If file line is corrupted with other data.
     */
    public void initList(Scanner s) throws JimException {
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] listedTask = task.split(" \\| ", 4);
            switch (listedTask[0]) {
            case "T":
                tasks.addTaskToList(new Todo(listedTask[2]));
                if (listedTask[1].equals("1")) {
                    tasks.getTaskFromList(tasks.getListSize() - 1).markAsDone();
                }
                break;
            case "D":
                tasks.addTaskToList(new Deadline(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.getTaskFromList(tasks.getListSize() - 1).markAsDone();
                }
                break;
            case "E":
                tasks.addTaskToList(new Event(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.getTaskFromList(tasks.getListSize() - 1).markAsDone();
                }
                break;
            default:
                throw new JimException();
            }
        }
        s.close();
    }

    /**
     * Initialises the storage component of Jim.
     */
    public void initJim() {
        try {
            File file = new File(FILE_PATH);
            File folder = new File(FOLDER_PATH);
            folderChecker(folder);
            databaseChecker(file);
            Scanner s = new Scanner(file);
            initList(s);
        } catch (JimException e) {
            ui.showCorruptedDatabaseFileMessage(tasks.getListSize() + 1);
        } catch (FileNotFoundException e) {
            ui.showNoDatabaseFileMessage();
            folderChecker(new File(FOLDER_PATH));
            databaseChecker(new File(FILE_PATH));
        }
    }

    /**
     * Appends a task to the database.
     *
     * @param type The type of task to append ("T" for todo, "D" for deadline, "E" for event)
     * @param input The description of the task.
     * @param isDone The boolean that checks if the task has been completed.
     */
    public static void appendDatabase(String type, String input, String isDone) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            switch (type) {
            case "T":
                fw.write(type + " | " + isDone + " | " + input + System.lineSeparator());
                break;
            case "D":
                String[] deadlineSplit = input.split("/by", 2);
                fw.write(type + " | " + isDone + " | " + deadlineSplit[0] + " | " +
                        deadlineSplit[1] + System.lineSeparator());
                break;
            case "E":
                String[] eventSplit = input.split("/at", 2);
                fw.write(type + " | " + isDone + " | " + eventSplit[0] + " | " + eventSplit[1]
                        + System.lineSeparator());
                break;
            default:
                break;
            }
            fw.close();
        } catch (IOException e) {
            ui.showNoDatabaseFileMessage();
            folderChecker(new File(FOLDER_PATH));
            databaseChecker(new File(FILE_PATH));
        }
    }

    /**
     * Clears all data from the database file.
     */
    public static void clearDatabase() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            ui.showNoDatabaseFileMessage();
            folderChecker(new File(FOLDER_PATH));
            databaseChecker(new File(FILE_PATH));
        }
    }

    /**
     * Updates the tasks in the database using the task list as reference.
     */
    public static void updateDatabase() {
        //clear file then append from list
        clearDatabase();
        for (Task task: tasks.getTaskList()) {
            String isDone = task.getIsDone()? "1" : "0";
            if (task instanceof Todo) {
                appendDatabase("T", task.getDescription(), isDone);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String input = deadline.getDescription() + "/by" + deadline.getDeadline();
                appendDatabase("D", input , isDone);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String input = event.getDescription() + "/at" + event.getTimeRange();
                appendDatabase("E", input , isDone);
            }
        }
    }
}
