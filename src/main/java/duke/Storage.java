package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "data/atlasData.txt";


    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        // Solution below adapted from @@NonRNP
        for (Task task : tasks) {
            String taskAsString = String.valueOf(task);
            String taskType = taskAsString.substring(1, 2);
            String taskStatus = task.isDone ? "1" : "0";

            switch (taskType) {
            case ("T"):
                fw.write("T | " + taskStatus + " |" + task.description);
                break;
            case ("D"):
                Deadline deadlineTask = (Deadline) task;
                fw.write("D | " + taskStatus + " |" + task.description + "|" + deadlineTask.getDeadline());
                break;
            case ("E"):
                Event eventTask = (Event) task;
                fw.write("E | " + taskStatus + " |" + task.description + "|" + eventTask.getDuration());
                break;
            default:
                System.out.println("Something went wrong!");
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void checkDirectoryExists() {
        try {
            File directory = new File(FILE_PATH);
            if (!directory.exists()) {
                directory.getParentFile().mkdirs();
                directory.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("File not created.");
        }
    }

    public static void saveTaskFile(ArrayList<Task> tasks) {
        checkDirectoryExists();
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // function populates empty task ArrayList given contents of the Task file
    public static void fillArrayListFromFile(String input, ArrayList<Task> tasks) {
        String[] taskContent = input.split( "\\|");
        String taskType = taskContent[0].trim();
        String ifDone = taskContent[1].trim();
        String taskName = taskContent[2];

        switch (taskType) {
        case ("T"):
            tasks.add(new Todo(taskName));
            break;
        case ("D"):
            String taskDeadline = taskContent[3];
            tasks.add(new Deadline(taskName, taskDeadline));
            break;
        case ("E"):
            String taskDuration = taskContent[3];
            tasks.add(new Event(taskName, taskDuration));
            break;
        default:
            System.out.println("Something went wrong!");
            break;
        }
        if (ifDone.equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }


    public static void readTaskFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File taskFile = new File(FILE_PATH);
        Scanner scan = new Scanner(taskFile);
        while (scan.hasNext()) {
            String taskToAdd = scan.nextLine();
            fillArrayListFromFile(taskToAdd, tasks);
        }
    }

    public static void loadTaskFile(ArrayList<Task> tasks) {
        try {
            readTaskFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not yet created, I'll create one for you now!");
        }
    }
}
