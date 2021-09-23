import Exceptions.JimException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static ArrayList<Task> tasks;
    protected static Messages messages = new Messages();
    //Creation of database file
    private static final String FILE_PATH = "data/jim.txt";
    private static final String FOLDER_PATH = "data";

    public static void folderChecker(File folder) {
        boolean result = folder.mkdir();
        messages.showCheckFolderMessage();
        if (result) {
            messages.showCreateMissingFolderMessage();
        } else {
            messages.showFolderFoundMessage();
        }
    }

    public static void databaseChecker(File file) {
        try {
            boolean result = file.createNewFile();
            messages.showCheckDatabaseFileMessage();
            if (result) {
                messages.showCreateMissingDatabaseFileMessage();
            } else {
                messages.showDatabaseFileFoundMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initList(Scanner s) throws JimException {
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] listedTask = task.split(" \\| ", 4);
            switch (listedTask[0]) {
            case "T":
                tasks.add(new Todo(listedTask[2]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case "D":
                tasks.add(new Deadline(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case "E":
                tasks.add(new Event(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            default:
                throw new JimException();
            }
        }
        s.close();
    }

    public static void initJim() {
        try {
            tasks = new ArrayList<>();
            File file = new File(FILE_PATH);
            File folder = new File(FOLDER_PATH);
            messages = new Messages();
            folderChecker(folder);
            databaseChecker(file);
            Scanner s = new Scanner(file);
            initList(s);
        } catch (JimException e) {
            messages.showCorruptedDatabaseFileMessage(tasks.size() + 1);
        } catch (FileNotFoundException e) {
            messages.showNoDatabaseFileMessage();
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
            messages.showNoDatabaseFileMessage();
        }
    }

    public static void clearDatabase() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            messages.showNoDatabaseFileMessage();
        }
    }

    public static void updateDatabase() {
        //clear file then append from list
        clearDatabase();
        for (Task task: tasks) {
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

    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
