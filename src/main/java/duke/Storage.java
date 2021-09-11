package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String pathName = "data/data.txt";
    private File file = new File(pathName);

    public Storage() {
        try {
            file.createNewFile();
        } catch (IOException e) {

        }
    }

    public void loadData(TaskList taskList) throws DukeException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {

        }

        while (scanner.hasNext()) {
            Task task = readTask(scanner.nextLine());
            taskList.addTask(task, false);
        }
    }

    public void saveData(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(pathName);
            for (Task task: taskList.getTasks()) {
                if (task != null) {
                    fw.write(task.getStorageString() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {

        }
    }

    private Task readTask(String string) throws DukeException {
        Task task = null;
        String[] words = string.split(" \\| ");
        switch (words[0]) {
        case "T":
            task = new ToDo(words[2]);
            break;
        case "E":
            task = new Event(words[2], words[3]);
            break;
        case "D":
            task = new Deadline(words[2], words[3]);
            break;
        }
        if (words[1].equals("1")) {
            task.setCompleted();
        }
        return task;
    }
}
