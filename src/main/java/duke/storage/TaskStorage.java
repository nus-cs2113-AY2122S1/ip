package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class TaskStorage {
    static final String DATA_FILE_PATH = "./data/tasks.txt";
    static final String SPACE_SEPARATOR = "\\|";
    static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    static private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private File dataFile;

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

    public void writeTasksToMemory(ArrayList<Task> tasks) throws IOException {
        String data = "";
        FileWriter fw = new FileWriter(DATA_FILE_PATH);

        for (Task t : tasks) {
            data += taskToString(t) + System.lineSeparator();
        }

        fw.write(data);
        fw.close();
    }

    public ArrayList<Task> readTasksFromMemory() {
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

        return extractedTasks;
    }

    private String taskToString(Task t) {
        if (t instanceof ToDos) {
            ToDos todoTask = (ToDos) t;
            return String.format("todo|%s|%b", todoTask.getTaskDescription(),
                    todoTask.isDone());
        } else if (t instanceof Event) {
            Event eventTask = (Event) t;
            return String.format("event|%s|%s|%b", eventTask.getTaskDescription(),
                    eventTask.getDate(), eventTask.isDone());
        } else if (t instanceof  Deadline) {
            Deadline deadlineTask = (Deadline) t;
            return String.format("deadline|%s|%s|%b", deadlineTask.getTaskDescription(),
                    deadlineTask.getDate(), deadlineTask.isDone());
        } else {
            return null;
        }
    }

    private Task readTask(String string) throws DukeException {
        String[] detail = string.split(SPACE_SEPARATOR);

        switch (detail[0]) {
        case "event":
            try {
                LocalDateTime date = LocalDateTime.parse(detail[2], dateTimeFormat);
                return new Event(detail[1], date, Boolean.parseBoolean(detail[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Not the right format. Not sure I can read that.");
            }

        case "deadline":
            try {
                LocalDateTime deadline = LocalDateTime.parse(detail[2], dateTimeFormat);
                return new Deadline(detail[1], deadline, Boolean.parseBoolean(detail[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Not the right format. Not sure I can read that.");
            }

        case "todo":
            return new ToDos(detail[1], Boolean.parseBoolean(detail[2]));

        default:
            throw new DukeException("Not the right format. Not sure I can read that.");
        }
    }
}
