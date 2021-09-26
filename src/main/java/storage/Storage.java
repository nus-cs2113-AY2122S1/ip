package storage;

import common.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    public static final String ERROR_MESSAGE_UNKNOWN_TASK = "Unknown task encountered. Skipping.";

    private static File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        if (!dataFile.exists()) {
            createFile();
        }
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                Task t = convertFileStringToTask(line);
                taskList.add(t);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException f) {
            createFile();
            throw new DukeException("File not found. Creating new file. ");
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + getDataFile().toPath());
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws DukeException {
        if (!dataFile.exists()) {
            createFile();
        }
        try {
            FileWriter fw = new FileWriter(dataFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : taskList) {
                bw.write(task.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving to file. ");
        }
    }

    private static void createFile() throws DukeException {
        if (dataFile.exists()) {
            System.out.println("File exists. ");
            return;
        }
        try {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error. Could not create file. ");
        }
    }

    private static Task convertFileStringToTask(String line) throws DukeException {
        try {
            String[] args = line.trim().split("\\|");
            if (args.length < 3) {
                throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
            }
            char taskType = args[0].charAt(0); // definitely not empty

            int isCompleted = Integer.parseInt(args[1].trim()); // throws number format exception
            if (isCompleted != 0 && isCompleted != 1) {
                throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
            }
            boolean isDone = isCompleted == 1;

            String description = args[2].trim();
            if (description.isEmpty()) {
                throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
            }

            String dateStr;
            LocalDateTime dateTime;

            switch (taskType) {
            case 'D':
                if (args.length < 4) {
                    throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
                }
                dateStr = args[3].trim();
                dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return new Deadline(description, isDone, dateTime);
            case 'E':
                if (args.length < 4) {
                    throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
                }
                dateStr = args[3].trim();
                dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return new Event(description, isDone, dateTime);
            case 'T':
                return new Todo(description, isDone);
            default:
                throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
        }
    }
}



