package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * To deal with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final File file = new File("data/tasks.txt");

    public Storage() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to load data!");
        }
    }

    /**
     * Adds data from the task list to the text file
     *
     * @throws DukeException if IO operations fails
     */
    public void saveData() throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task task : TaskList.list) {
                fw.write("" + task.getStoreDataString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save data!");
        }
    }

    /**
     * Loads data from the text file to the task list
     *
     * @throws DukeException if file not found
     */
    public void loadData() throws DukeException {
        try {
            Scanner load = new Scanner(file);
            while (load.hasNext()) {
                String sentence = load.nextLine();
                addData(sentence);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load data!");
        }
    }

    /**
     * Creates task from txt file and stores them in Task objects respectively
     *
     * @param taskWords data present in the txt file
     * @throws DukeException if the lines in the txt file do not start with T/D/E or if negative/unwanted array index is being accessed
     */
    public void addData(String taskWords) throws DukeException {
        try {
            String[] word = taskWords.split(" \\| ");

            switch (word[0]) {
            case "T":
                Task todo = new Todo(word[2]);
                if (word[1].equals("1")) {
                    todo.markAsDone();
                }
                TaskList.list.add(TaskList.count, todo);
                TaskList.count++;
                break;
            case "D":
                Task deadline = new Deadline(word[2], word[3]);
                if (word[1].equals("1")) {
                    deadline.markAsDone();
                }
                TaskList.list.add(TaskList.count, deadline);
                TaskList.count++;
                break;
            case "E":
                Task event = new Event(word[2], word[3]);
                if (word[1].equals("1")) {
                    event.markAsDone();
                }
                TaskList.list.add(TaskList.count, event);
                TaskList.count++;
                break;
            default:
                throw new DukeException("Unable to parse data!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to parse data!");
        }
    }
}

