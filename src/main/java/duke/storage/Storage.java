package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String FILE_DIRECTORY = "data";
    public static final String FILE_NAME = "duke.txt";
    public static final String FILE_SEPARATOR = "/";
    public static final String FIELD_SEPARATOR = " -separator- ";
    public static final String NEWLINE = "\n";

    /**
     * Saves the list of tasks to a file.
     * @param tasks
     * @throws IOException If an error occurs while saving to the file.
     */
    public void saveListToFile(ArrayList<Task> tasks) throws IOException {
        File directory = new File(FILE_DIRECTORY);
        directory.mkdir();
        FileWriter fw = new FileWriter(FILE_DIRECTORY + FILE_SEPARATOR + FILE_NAME);
        for (Task task: tasks) {
            if (task.getTypeIcon().equals("T")) {
                fw.write( task.getTypeIcon() + FIELD_SEPARATOR + task.isDone() + FIELD_SEPARATOR
                        + task.getDescription() + NEWLINE);
            } else {
                fw.write( task.getTypeIcon() + FIELD_SEPARATOR + task.isDone() + FIELD_SEPARATOR
                        + task.getDescription() + FIELD_SEPARATOR + task.getDate() + NEWLINE);
            }
        }
        fw.close();
    }

    /**
     * Gets the list of tasks from a file.
     * @param tasks list to save tasks to
     * @throws FileNotFoundException If file does not exist
     */
    public void getTasksFromFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(FILE_DIRECTORY + FILE_SEPARATOR + FILE_NAME); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String text = s.nextLine();
            String[] fields = text.split(FIELD_SEPARATOR);
            switch (fields[0]){
            case "T":
                Todo todo = new Todo(fields[2]);
                tasks.add(todo);
                todo.setIsDone(fields[1].equals("true"));
                break;

            case "D":
                Deadline deadline = new Deadline(fields[2], fields[3]);
                tasks.add(deadline);
                deadline.setIsDone(fields[1].equals("true"));
                break;

            case "E":
                Event event = new Event(fields[2], fields[3]);
                tasks.add(event);
                event.setIsDone(fields[1].equals("true"));
                break;
            }
        }
    }
}
