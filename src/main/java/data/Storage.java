package data;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

import static common.Error.FILE_NOT_EXIST;
import static common.Error.WRITE_IOEXCEPTION;
import static common.Message.SUCCESS_FILE_FOUND;
import static common.Message.SUCCESS_DATA_READ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String dataPath;
    protected static final String PATH_DELIMITER = "/";
    protected static final String TASK_DELIMITER = " | ";
    protected static final String TASK_DELIMITER_REGEX = " \\| ";

    public Storage() {
        this.dataPath = "data/data.txt";
    }

    private File validatePath() {
        String[] directories = this.dataPath.split(PATH_DELIMITER);
        /*
        Check for whether ./data directory exists
        if not, create ./data directory
        */
        File directory = new File(directories[0]);
        if (!directory.exists()) {
            directory.mkdir();
        }
        /*
        Check for whether ./data/data.txt exists
        if so, process list of tasks contained within
        if not, create new, empty data.txt file
        */
        File data = new File(dataPath);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println(WRITE_IOEXCEPTION);
            }
            String absolutePath = data.getAbsolutePath();
            System.out.println(String.format(FILE_NOT_EXIST, absolutePath));
        } else {
            System.out.println(String.format(SUCCESS_FILE_FOUND, data.getAbsolutePath()));
        }
        return data;
    }

    public ArrayList<Task> read() {
        File data = validatePath();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(TASK_DELIMITER_REGEX);
                Boolean status = false;
                if (line[1].equals("1")) {
                    status = true;
                }
                switch(line[0]) {
                case "T":
                    ToDo newToDo = new ToDo(line[2], status);
                    tasks.add(newToDo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(line[2], line[3], status);
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(line[2], line[3], line[4], status);
                    tasks.add(newEvent);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            //TODO
            System.out.println("file not found");
        }
        System.out.println(String.format(SUCCESS_DATA_READ, tasks.size()));
        return tasks;
    }

    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(dataPath);
            for (Task currentTask : tasks) {
                fw.write(getTaskData(currentTask) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(WRITE_IOEXCEPTION);
        }
    }

    private static String getTaskData(Task current) {
        if (current instanceof ToDo) {
            return "T" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription();
        } else if (current instanceof Deadline) {
            return "D" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription() + TASK_DELIMITER +
                    ((Deadline) current).getTime();
        } else if (current instanceof Event) {
            return "E" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription() + TASK_DELIMITER +
                    ((Event) current).getStart() + TASK_DELIMITER +
                    ((Event) current).getEnd() + TASK_DELIMITER;
        } else {
            return "";
        }
    }

    private static int convertStatus(String status) {
        if (status.equals("X")) {
            return 1;
        } else {
            return 0;
        }
    }
}
