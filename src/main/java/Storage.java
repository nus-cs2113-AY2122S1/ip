import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;


public class Storage {

    protected static String filepath;
    private final static int TASK_TYPE_INDEX = 0;
    private final static int IS_DONE_INDEX = 1;
    private final static int TASK_INDEX = 2;
    private final static int BY_AT_INDEX = 3;


    public Storage(String filepath) {
        Storage.filepath = filepath;
    }


    public void readSavedTasks(TaskList tasks) {
        File dataFile = new File(filepath);
        try {
            Scanner lineScanner = new Scanner(dataFile);
            while (lineScanner.hasNext()) {
                AddLineTask(tasks, lineScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            createFile();
        } catch (DateTimeParseException e) {
            Duke.setDukeDone();
        }
    }

    public void AddLineTask(TaskList tasks, String line) {
        String[] lineContents = line.split(" \\| ");
        switch (lineContents[TASK_TYPE_INDEX]) {
        case "T":
            Todo todo = new Todo(lineContents[TASK_INDEX]);
            tasks.addTask(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(lineContents[TASK_INDEX], LocalDate.parse(lineContents[BY_AT_INDEX]));
            tasks.addTask(deadline);
            break;
        case "E":
            Event event = new Event(lineContents[TASK_INDEX], LocalDate.parse(lineContents[BY_AT_INDEX]));
            tasks.addTask(event);
            break;
        }
        if (lineContents[IS_DONE_INDEX].equals("1")) {
            tasks.getLastTask().markAsDone();
        }
    }

    public static void refreshFile(TaskList tasks) {
        try {
            FileWriter file = new FileWriter(filepath);
            for (Task task : tasks.getList()) {
                file.write(task.toFile());
            }
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }

    public static void createFile() {
        try {
            File newFile = new File(filepath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Unable to create file...");
        }
    }

    public static void saveTaskInFile(Task task) {
        try {
            FileWriter file = new FileWriter(filepath, true);
            file.write(task.toFile());
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }
}
