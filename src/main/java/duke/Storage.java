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

    protected String filePath;
    protected ArrayList<Task> tasks;

    public static final int TYPE_OF_TASK = 0;
    public static final int TASK_IS_DONE = 4;
    public static final int START_OF_TASK = 7;

    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Tasks from a given file with a
     * specific format.
     *
     * @return ArrayList of Tasks stored from a given a file.
     */
    public ArrayList<Task> load() {
        try {
            readFromFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return tasks;
    }

    /**
     * Reads every line of a given file and parses each line into the
     * addToList function to interpret each line as a task.
     *
     * @param filePath File path of the file used to store data of tasks.
     * @throws FileNotFoundException If file is not found in the given path.
     */
    public void readFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            addToList(s.nextLine());
        }
    }

    /**
     * Interprets each line of text as a task and stores the task in an ArrayList.
     *
     * @param line String of text meant to represent a task in the data file.
     */
    public void addToList(String line) {
        char typeOfTask = line.charAt(TYPE_OF_TASK);
        boolean isDone = line.charAt(TASK_IS_DONE) == '1';
        if (typeOfTask == 'T') {
            String task = line.substring(START_OF_TASK).trim();
            tasks.add(new ToDo(task));
            if (isDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        } else if (typeOfTask == 'D') {
            int bySeparator = line.lastIndexOf("|");
            String task = line.substring(START_OF_TASK, bySeparator).trim();
            String by = line.substring(bySeparator + 1).trim();
            tasks.add(new Deadline(task, by));
            if (isDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        } else if (typeOfTask == 'E') {
            int atSeparator = line.lastIndexOf("|");
            String task = line.substring(START_OF_TASK, atSeparator).trim();
            String at = line.substring(atSeparator + 1).trim();
            tasks.add(new Event(task, at));
            if (isDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    /**
     * Writes a string of text to the data file.
     *
     * @param filePath  File path of the file used to store data of tasks.
     * @param textToAdd String of text to be written to the data file.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns a string of tasks separated by lines to be stored in the data file.
     *
     * @return The text to be stored in the data file.
     */
    public String readTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (Task task : tasks) {
            listOfTasks.append(task.toText()).append("\n");
        }
        return listOfTasks.toString();
    }

    /**
     * Writes the stored tasks in the ArrayList onto a given data file, including
     * the types of tasks and whether they are done.
     */
    public void save() {
        try {
            writeToFile(filePath, readTasks());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
