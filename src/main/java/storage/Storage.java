package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    public ArrayList<Task> load() throws StorageOperationException {
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
            throw new StorageOperationException("File not found. Creating new file");
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + getDataFile().toPath());
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
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
            e.printStackTrace();
            System.out.println("Error saving to file");
        }
    }

    private static void createFile() {
        if (dataFile.exists()) {
            System.out.println("File exists");
            return;
        }
        try {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in creating file");
        }
    }

    private static Task convertFileStringToTask(String line) {
        try {
            final String[] split = line.trim().split("\\|");
            char taskType = split[0].charAt(0);
            int isComplete = Integer.parseInt(split[1].trim());
            boolean isDone = isComplete == 1;
            String description = split[2].trim();
            String dateStr;
            LocalDateTime dateTime;

            switch (taskType) {
            case 'D':
                dateStr = split[3].trim();
                dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return new Deadline(description, isDone, dateTime);
            case 'E':
                dateStr = split[3].trim();
                dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return new Event(description, isDone, dateTime);
            case 'T':
                return new Todo(description, isDone);
            default:
                System.out.println("Unknown task encountered. Skipping.");
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Error converting file string to task");
        }
        return null;
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
