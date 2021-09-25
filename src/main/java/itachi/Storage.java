package itachi;

import itachi.exception.ItachiException;
import itachi.task.Deadline;
import itachi.task.Event;
import itachi.task.Task;
import itachi.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static itachi.Parser.dueTime;

/**
 * To deal with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final File file = new File("data/tasks.txt");

    public Storage() throws ItachiException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new ItachiException("Unable to load data!");
        }
    }

    /**
     * Adds data from the task list to the text file
     *
     * @throws ItachiException if IO operations fails
     */
    public void saveData() throws ItachiException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task task : TaskList.list) {
                fw.write("" + task.getStoreDataString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new ItachiException("Unable to save data!");
        }
    }

    /**
     * Loads data from the text file to the task list
     *
     * @throws ItachiException if file not found
     */
    public void loadData() throws ItachiException {
        try {
            Scanner load = new Scanner(file);
            while (load.hasNext()) {
                String sentence = load.nextLine();
                addData(sentence);
            }
        } catch (FileNotFoundException e) {
            throw new ItachiException("Unable to load data!");
        }
    }

    /**
     * Creates task from txt file and stores them in Task objects respectively
     *
     * @param taskWords data present in the txt file
     * @throws ItachiException if the lines in the txt file do not start with T/D/E or if negative/unwanted array index is being accessed
     */
    public void addData(String taskWords) throws ItachiException {
        try {
            // To break down the task words in the saved txt file
            String[] word = taskWords.split(" \\| ");

            switch (word[0]) {
            case "T":
                Task todo = new Todo(word[2]);
                if (word[1].equals("1")) {
                    todo.markAsDone();
                }
                TaskList.list.add(todo);
                break;
            case "D":
                dueTime = LocalDate.parse(word[3]);
                Task deadline = new Deadline(word[2], dueTime);
                if (word[1].equals("1")) {
                    deadline.markAsDone();
                }
                TaskList.list.add(deadline);
                break;
            case "E":
                Task event = new Event(word[2], word[3]);
                if (word[1].equals("1")) {
                    event.markAsDone();
                }
                TaskList.list.add(event);
                break;
            default:
                throw new ItachiException("Unable to parse data!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ItachiException("Unable to parse data!");
        }
    }
}

