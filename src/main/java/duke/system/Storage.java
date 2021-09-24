package duke.system;

import duke.exception.CorruptedFile;
import duke.exception.DukeException;
import duke.exception.NoDataFile;
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

public class Storage {

    public ArrayList<Task> readData() throws DukeException {
        String filePath = "data.txt";
        File file = new File(filePath);
        try {
            return DecodeDataFile(file);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CorruptedFile();
        } catch (FileNotFoundException e) {
            throw new NoDataFile();
        }
    }

    private ArrayList<Task> DecodeDataFile(File file) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        try {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                tasks = DecodeLine(currentLine, tasks);
            }
            return tasks;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private ArrayList<Task> DecodeLine(String currentLine, ArrayList<Task> tasks) {
        String[] currentData = currentLine.split(" \\* ");
        String taskType = currentData[0];
        switch (taskType) {
        case "T":
            String todoName = currentData[2];
            tasks.add(new ToDo(todoName));
            String todoStatus = currentData[1];
            if (todoStatus.equals("1")) {
                int currentTaskIndex = tasks.size() - 1;
                tasks.get(currentTaskIndex).markAsDone();
            }
            break;
        case "D":
            String deadlineName = currentData[2];
            String deadlineTime = currentData[3];
            tasks.add(new Deadline(deadlineName, deadlineTime));
            String deadlineStatus = currentData[1];
            if (deadlineStatus.equals("1")) {
                int currentTaskIndex = tasks.size() - 1;
                tasks.get(currentTaskIndex).markAsDone();
            }
            break;
        case "E":
            String eventName = currentData[2];
            String eventTime = currentData[3];
            tasks.add(new Event(eventName, eventTime));
            String eventStatus = currentData[1];
            if (eventStatus.equals("1")) {
                int currentTaskIndex = tasks.size() - 1;
                tasks.get(currentTaskIndex).markAsDone();
            }
            break;
        }
        return tasks;
    }

    public void writeData(ArrayList<Task> tasks) throws IOException {

        String filePath = "data.txt";
        String updatedData = EncodeTask(tasks);
        try {
            writeToFile(filePath, updatedData);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private String EncodeTask(ArrayList<Task> tasks) {
        String updatedData = "";
        String deadlineIndicator = "(by:";
        String eventIndicator = "(at:";
        int statusIndex = 4;
        int taskStartIndex = 7;
        int indicatorLength = 5;
        for (int i = 0; i < tasks.size(); i++) {
            String currentTask = tasks.get(i).toString();
            String type = currentTask.substring(1, 2);
            boolean isDone = (currentTask.charAt(statusIndex) == 'X');
            String taskStatus;
            if (isDone) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (currentTask.contains(deadlineIndicator)) {
                int endOfDeadlineName = currentTask.indexOf(deadlineIndicator);
                String deadlineName = currentTask.substring(taskStartIndex,
                        endOfDeadlineName);
                int lastIndex = currentTask.length() - 1;
                int startOfDeadlineTime = endOfDeadlineName + indicatorLength;
                String deadlineTime = currentTask.substring(startOfDeadlineTime,
                        lastIndex);
                updatedData += type
                        + " * " + taskStatus
                        + " * " + deadlineName
                        + " * " + deadlineTime
                        + System.lineSeparator();
            } else if (currentTask.contains(eventIndicator)) {
                int endOfEventName = currentTask.indexOf(eventIndicator);
                String eventName = currentTask.substring(taskStartIndex,
                        endOfEventName);
                int lastIndex = currentTask.length() - 1;
                int startOfEventTime = endOfEventName + indicatorLength;
                String eventTime = currentTask.substring(startOfEventTime,
                        lastIndex);
                updatedData += type
                        + " * " + taskStatus
                        + " * " + eventName
                        + " * " + eventTime
                        + System.lineSeparator();
            } else {
                String content = currentTask.substring(taskStartIndex);
                updatedData += type
                        + " * " + taskStatus
                        + " * " + content
                        + System.lineSeparator();
            }
        }
        return updatedData;
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
