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


    public void folderChecker(File folder) {
        boolean result = folder.mkdir();
        ui.showCheckFolderMessage();
        if (result) {
            ui.showCreateMissingFolderMessage();
        } else {
            ui.showFolderFoundMessage();
        }
    }

    public void databaseChecker(File file) {
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
        }
    }

    public static void appendDatabase(String type, String input, String isDone) {
        //append new tasks to database
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
        }
    }

    public static void clearDatabase() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            ui.showNoDatabaseFileMessage();
        }
    }

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
