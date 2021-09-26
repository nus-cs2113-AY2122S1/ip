package duke.storage;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles all saving and loading of data
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static File myFile;

    /**
     * Constructor of Storage class.
     * Initializes myFile variable with file data/duke.txt
     */
    public Storage() {
        myFile = new File(FILE_PATH);
    }

    /**
     * Write data into file data/duke.txt
     *
     * @param data Data to be written into the file
     * @throws IOException If unable to write into file
     */
    private static void writeToFile(String data) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write(data);
        fw.close();
    }

    /**
     * Saves all task details into data/duke.txt
     * Creates directory or file if it does not exist
     */
    public static void saveData(TaskList tasks) {
        String data = "";
        data = tasks.getTaskListInFileFormat();

        File myDirectory = new File(DIRECTORY_PATH);
        File myFile = new File(FILE_PATH);

        if (!myDirectory.exists()) {
            myDirectory.mkdir();
        }

        try {
            myFile.createNewFile();
            writeToFile(data);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Read and process tasks details from file data/duke.txt to replicate task state in program
     *
     * @param myFile File object of data/duke.txt
     * @throws FileNotFoundException If file is not found
     */
    private static ArrayList<Task> readFromFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String taskDetails = sc.nextLine();
            try {
                Task parsedTask = Parser.parseFile(taskDetails);
                tasks.add(parsedTask);
            } catch (DukeException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }
        return tasks;
    }

    /**
     * Load saved data from data/duke.txt into program
     */
    public static ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (myFile.exists()) {
            try {
                tasks = readFromFile(myFile);
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return tasks;
    }
}
