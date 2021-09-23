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

public class DukeStorage {
    private static final String filePath = "data/data.txt";
    private final File file = new File(filePath);

    public DukeStorage() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can't create a new save, try to load existing save now");
        }
    }

    /**
     * @param tasks the list to save the data from
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                if (task != null) {
                    fw.write(task.toStorageString() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot save your tasks");
        }
    }

    /**
     * @param tasks the list to load the data into
     */
    public void loadData(ArrayList<Task> tasks) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task savedTask = readTaskData(scanner.nextLine());
                if (savedTask != null) {
                    tasks.add(savedTask);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Create a new save file");
        }
    }

    /**
     * @param savedTask the string represents the saved task
     * @return the saved task decode from the string
     */
    public Task readTaskData(String savedTask) {
        Task task = null;
        try {
            String[] words = savedTask.split("//");
            switch (words[0]) {
            case "T":
                task = new ToDo(words[1]);
                break;
            case "D":
                task = new Deadline(words[1], words[2]);
                break;
            case "E":
                task = new Event(words[1], words[2]);
            }
            if (words[3].equals("X")) {
                assert task != null;
                task.setDone();
            }
        } catch (IndexOutOfBoundsException e) {
            DukeUI.printError(e);
        }
        return task;
    }
}
