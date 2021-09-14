package duke.file;

import duke.exceptions.EmptyField;
import duke.exceptions.IllegalOperation;
import duke.list.TaskList;
import duke.ui.MessageBubble;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    static String STORAGE_PATH = "data/dukeData.txt";

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Load Duke data from local directory and parse the data to a TaskList object.
     * If no data is found, a new data file will be created and used.
     *
     * @return Stored TaskList
     */
    public static TaskList loadFile() {
        try {
            File f = new File(STORAGE_PATH); // create a File for the given file path
            if (!f.exists()) {
                String directory = STORAGE_PATH.substring(0, STORAGE_PATH.lastIndexOf("/"));
                Files.createDirectories(Paths.get(directory));
                if (f.createNewFile()) {
                    MessageBubble.printMessageBubble("New task list database created!");
                }
            }
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            TaskList data = new TaskList();
            while (s.hasNext()) {
                String temp = s.nextLine();
                String[] divided = temp.split(" \\| ");
                boolean isDone = divided[1].equals("1");
                String taskDescription = divided[2];
                switch (divided[0]){
                case "T":
                    data.addItem(new Todo(taskDescription, isDone), false);
                    break;
                case "D":
                    data.addItem(new Deadline(taskDescription, isDone, divided[3]), false);
                    break;
                case "E":
                    data.addItem(new Event(taskDescription, isDone, divided[3]), false);
                    break;
                default:
                    throw new EmptyField();
                }
            }
            return data;
        } catch (IOException | EmptyField | IllegalOperation | IllegalArgumentException e) {
            MessageBubble.printMessageBubble("Warning! No local data loaded.");
            return new TaskList();
        }
    }

    /**
     * Save the specified TaskList to the local file.
     *
     * @param data TaskList to be stored
     */
    public static void saveFile(TaskList data) {
        try {
            writeToFile(STORAGE_PATH, data.printListSaveFormat());
        } catch (IOException e) {
            MessageBubble.printMessageBubble(e.getMessage() + "Error saving data.");
        }
    }

    /**
     * Print out all information in the specified file
     *
     * @param filePath path to the specified file
     * @throws FileNotFoundException if no file is found
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }


}
