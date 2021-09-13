package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class TaskStorage {
    static final String DATA_FILE_PATH = "./data/tasks.txt";
    static final String SPACE_SEPARATOR = " ";

    private File dataFile;
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskStorage() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);
        File dataDir = new File(dataFile.getParent());

        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        this.dataFile = dataFile;
    }

    public ArrayList<Task> tasksFromFile() {
        ArrayList<Task> extractedTasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);

            while(scanner.hasNextLine()) {
                Task task = readTask(scanner.nextLine());
                extractedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tI cannot open the file!");
        } catch (DukeException e) {
            System.out.println("\tWrong task format");
        }

        tasks = extractedTasks;
        return extractedTasks;
    }

    public void storageAddTask(Task task) throws IOException {
        addTask(task);
    }

    public Task storageDeleteTask(int taskNumber) throws IOException, IndexOutOfBoundsException {
        return deleteTask(taskNumber);
    }

    public void storageSetTaskDone(int taskNumber) throws IOException, IndexOutOfBoundsException {
        setTaskDone(taskNumber);
    }

    private void taskToFile(ArrayList<Task> tasks) throws IOException {
        String data = "";
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        for (Task t : tasks) {
            data += taskToString(t) + System.lineSeparator();
        }

        fw.write(data);
        fw.close();
    }

    private String taskToString(Task t) {
        if (t instanceof ToDos) {
            ToDos todoTask = (ToDos) t;
            return String.format("todo %s %b", todoTask.getTaskDescription(),
                    todoTask.isDone());
        } else if (t instanceof Event) {
            Event eventTask = (Event) t;
            return String.format("event %s %s %b", eventTask.getTaskDescription(),
                    eventTask.getDate(), eventTask.isDone());
        } else if (t instanceof  Deadline) {
            Deadline deadlineTask = (Deadline) t;
            return String.format("deadline %s %s %b", deadlineTask.getTaskDescription(),
                    deadlineTask.getDate(), deadlineTask.isDone());
        } else {
            return null;
        }
    }

    private Task readTask(String string) throws DukeException {
        String[] detail = string.split(SPACE_SEPARATOR);

        switch (detail[0]) {
        case "event":
            return new Event(detail[1], detail[2], Boolean.parseBoolean(detail[3]));

        case "deadline":
            return new Deadline(detail[1],detail[2], Boolean.parseBoolean(detail[3]));

        case "todo":
            return new ToDos(detail[1], Boolean.parseBoolean(detail[2]));

        default:
            throw new DukeException("Not the right format. Not sure I can read that.");
        }
    }

    private void addTask(Task task) throws IOException {
        tasks.add(task);
        taskToFile(tasks);
    }

    private Task deleteTask(int taskNumber) throws IOException, IndexOutOfBoundsException {
        Task deletedTask = this.tasks.remove(taskNumber - 1);
        taskToFile(tasks);
        return deletedTask;
    }

    private void setTaskDone(int taskNumber) throws IOException, IndexOutOfBoundsException {
        this.tasks.get(taskNumber - 1).setDone();
        taskToFile(tasks);
    }
}
