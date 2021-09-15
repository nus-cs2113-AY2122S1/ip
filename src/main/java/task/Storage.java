package task;

import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import error.Error;

import static task.Duke.count;
import static task.Duke.list;

public class Storage {
    private final File file = new File("data/tasks.txt");

    public Storage() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            Error.showUnableToLoadError();
        }
    }

    /**
     * Adds data from the task list to the text file
     * @param list gg
     */
    public void saveData(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task task : list) {
                fw.write("" + task.getStoreDataString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            Error.showUnableToSaveError();
        }
    }

    /**
     * Loads data from the text file to the task list
     */
    public void loadData() {
        try {
            Scanner load = new Scanner(file);
            while (load.hasNext()) {
                String sentence = load.nextLine();
                addData(sentence);
            }
        } catch (FileNotFoundException e) {
            Error.showUnableToLoadError();
        }
    }

    /**
     * Creates task from txt file and stores them in Task objects respectively
     *
     * @param taskWords data present in the txt file
     */
    public void addData(String taskWords) {
        try {
            String[] word = taskWords.split(" \\| ");

            switch (word[0]) {
            case "T":
                Task todo = new Todo(word[2]);
                if (word[1].equals("1")) {
                    todo.markAsDone();
                }
                list.add(count, todo);
                count++;
                break;
            case "D":
                Task deadline = new Deadline(word[2], word[3]);
                if (word[1].equals("1")) {
                    deadline.markAsDone();
                }
                list.add(count, deadline);
                count++;
                break;
            case "E":
                Task event = new Event(word[2], word[3]);
                if (word[1].equals("1")) {
                    event.markAsDone();
                }
                list.add(count, event);
                count++;
                break;
            default:
                Error.showUnableToParseError();
            }
        } catch (IndexOutOfBoundsException e) {
            Error.showUnableToParseError();
        }
    }
}

