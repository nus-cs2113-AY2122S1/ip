package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "data/atlasData.txt";

    /**
     * Processes tasks from the main ArrayList of tasks and stores them in the save file.
     * Method stores tasks in the .txt file in the following format:
     * T | 0 | task description and/or deadline
     * The first index indicates the type of task, the second indicates whether a task is done by storing a 1 and
     * 0 if undone. The last field contains the description and/or deadline containing the type of task. If the last
     * field contains a deadline, the format of the last field will be: task description | deadline.
     * If an error occurs while writing to the file, an error message is printed.
     *
     * @param tasks ArrayList of tasks
     * @throws IOException throws an IOException
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task task : tasks) {
            String taskAsString = String.valueOf(task);
            String taskType = taskAsString.substring(1, 2);
            String taskStatus = task.isDone() ? "1" : "0";

            switch (taskType) {
            case ("T"):
                fw.write("T | " + taskStatus + " |" + task.getDescription());
                break;
            case ("D"):
                Deadline deadlineTask = (Deadline) task;
                String deadlineString = Parser.storeDateAndTimeAsString(deadlineTask.getDeadline());
                fw.write("D | " + taskStatus + " |" + task.getDescription() + "| " + deadlineString);
                break;
            case ("E"):
                Event eventTask = (Event) task;
                String eventDuration = Parser.storeDateAndTimeAsString(eventTask.getDuration());
                fw.write("E | " + taskStatus + " |" + task.getDescription() + "| " + eventDuration);
                break;
            default:
                System.out.println("Something went wrong!");
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Checks if the directory and file to save the list of tasks in exists. If not, the directory
     * or file will be created, depending on which is/are missing.
     * Prints an error message if the file cannot be created successfully.
     */
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

    /**
     * Populates empty task ArrayList given contents of the save file.
     * This method processes tasks from the save file format to the same format as a user would input
     * into the command line, one by one.
     *
     * @param input string representing one task from the save file
     * @param tasks ArrayList of tasks to be populated
     */
    public static void fillArrayListFromFile(String input, ArrayList<Task> tasks) {
        String[] taskContent = input.split( "\\|");
        String taskType = taskContent[0].trim();
        String isDone = taskContent[1].trim();
        String description = taskContent[2];

        switch (taskType) {
        case ("T"):
            tasks.add(new Todo(description));
            break;
        case ("D"):
            String taskDeadline = taskContent[3];
            LocalDateTime deadlineDateAndTime = Parser.convertSubStringToDateAndTime(taskDeadline.trim());
            tasks.add(new Deadline(description, deadlineDateAndTime));
            break;
        case ("E"):
            String taskDuration = taskContent[3];
            LocalDateTime eventDateAndTime = Parser.convertSubStringToDateAndTime(taskDuration.trim());
            tasks.add(new Event(description, eventDateAndTime));
            break;
        default:
            System.out.println("Something went wrong!");
            break;
        }

        // Current task will always be at the end of the ArrayList, so the marking of a task as done can be done after
        // exiting the switch statement
        if (isDone.equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Takes in the save file and passes tasks individually to be processed to be added to the list of tasks.
     *
     * @param tasks ArrayList of tasks to be populated.
     * @throws FileNotFoundException throws a FileNotFoundException
     */
    public static void readTaskFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File taskFile = new File(FILE_PATH);
        Scanner scan = new Scanner(taskFile);
        while (scan.hasNext()) {
            String taskToAdd = scan.nextLine();
            fillArrayListFromFile(taskToAdd, tasks);
        }
    }

    /**
     * Loads the save file for processing into the main ArrayList of tasks.
     * Done at the very beginning when the program is run. Creates a file if the save file is not
     * yet created (usually done when the program is run by a new user for the first time).
     *
     * @param tasks empty ArrayList of tasks to be populated.
     */
    public static void loadTaskFile(ArrayList<Task> tasks) {
        try {
            readTaskFile(tasks);
        } catch (FileNotFoundException e) {
            Ui.printHorizontalLine();
            System.out.println("Perhaps this is your first time running Atlas! Even if not, I will" +
                    " create a save file for you now!");
            Ui.printHorizontalLine();
        }
    }
}
