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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A system component in charge of reading/saving data from local file
 */
public class Storage {

    /**
     * Read data from the local file in a default path.
     * @return ArrayList that contains all the tasks stored locally
     * @throws DukeException if there is any file-related problems
     */
    public ArrayList<Task> readData() throws DukeException {
        String filePath = "data.txt";
        File file = new File(filePath);
        try {
            return DecodeDataFile(file);
        } catch (ArrayIndexOutOfBoundsException | CorruptedFile e) {
            throw new CorruptedFile();
        } catch (FileNotFoundException e) {
            throw new NoDataFile();
        }
    }

    private ArrayList<Task> DecodeDataFile(File file) throws FileNotFoundException, CorruptedFile {
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
        } catch (CorruptedFile e) {
            throw new CorruptedFile();
        }
    }

    private ArrayList<Task> DecodeTodo(String[] currentTaskData, ArrayList<Task> tasks) {
        String todoName = currentTaskData[2];
        tasks.add(new ToDo(todoName));
        String todoStatus = currentTaskData[1];
        if (todoStatus.equals("1")) {
            int currentTaskIndex = tasks.size() - 1;
            tasks.get(currentTaskIndex).markAsDone();
        }
        return tasks;
    }

    private ArrayList<Task> DecodeDeadline(String[] currentTaskData, ArrayList<Task> tasks) {
        String deadlineName = currentTaskData[2];
        String rawDeadlineDateTime = currentTaskData[3];
        String rawDeadlineDate = rawDeadlineDateTime.split(" ")[0];
        String deadlineTime = rawDeadlineDateTime.split(" ")[1];
        LocalDate deadlineDate = LocalDate.parse(rawDeadlineDate);
        tasks.add(new Deadline(deadlineName, deadlineDate, deadlineTime));
        String deadlineStatus = currentTaskData[1];
        if (deadlineStatus.equals("1")) {
            int currentTaskIndex = tasks.size() - 1;
            tasks.get(currentTaskIndex).markAsDone();
        }
        return tasks;
    }

    private ArrayList<Task> DecodeEvent(String[] currentTaskData, ArrayList<Task> tasks) {
        String eventName = currentTaskData[2];
        String rawEventDatesTimes = currentTaskData[3];
        String rawEventStartDateTime = rawEventDatesTimes.split(" to ")[0];
        String rawEventEndDateTime = rawEventDatesTimes.split(" to ")[1];
        String rawEventStartDate = rawEventStartDateTime.split(" ")[0];
        LocalDate eventStartDate = LocalDate.parse(rawEventStartDate);
        String eventStartTime = rawEventStartDateTime.split(" ")[1];
        String rawEventEndDate = rawEventEndDateTime.split(" ")[0];
        LocalDate eventEndDate = LocalDate.parse(rawEventEndDate);
        String eventEndTime = rawEventEndDateTime.split(" ")[1];
        tasks.add(new Event(eventName, eventStartDate, eventStartTime,
                eventEndDate, eventEndTime));
        String eventStatus = currentTaskData[1];
        if (eventStatus.equals("1")) {
            int currentTaskIndex = tasks.size() - 1;
            tasks.get(currentTaskIndex).markAsDone();
        }
        return tasks;
    }

    private ArrayList<Task> DecodeLine(String currentLine, ArrayList<Task> tasks) throws CorruptedFile {
        ArrayList<Task> updatedTasks;
        String[] currentTaskData = currentLine.split(" \\* ");
        String taskType = currentTaskData[0];
        switch (taskType) {
        case "T":
            updatedTasks = DecodeTodo(currentTaskData, tasks);
            break;
        case "D":
            updatedTasks = DecodeDeadline(currentTaskData, tasks);
            break;
        case "E":
            updatedTasks = DecodeEvent(currentTaskData, tasks);
            break;
        default:
            throw new CorruptedFile();
        }
        return updatedTasks;
    }

    /**
     * Back up all tasks in the local txt file in a default path
     * @param tasks task list that holds all the tasks
     * @throws IOException if there is problem in writing to local file
     */
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
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentTaskName = currentTask.getTaskName();
            String currentTaskString = currentTask.toString();
            String taskType = currentTaskString.substring(1, 2);
            boolean isDone = currentTask.getIsDone();
            String taskStatus;
            if (isDone) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (currentTaskString.contains(deadlineIndicator)) {
                String deadlineDateTime = ((Deadline) currentTask).getDeadlineDateTime(false);
                updatedData += taskType
                        + " * " + taskStatus
                        + " * " + currentTaskName
                        + " * " + deadlineDateTime
                        + System.lineSeparator();
            } else if (currentTaskString.contains(eventIndicator)) {
                String eventDatesTimes = ((Event)currentTask).getEventDatesTimes(false);
                updatedData += taskType
                        + " * " + taskStatus
                        + " * " + currentTaskName
                        + " * " + eventDatesTimes
                        + System.lineSeparator();
            } else {
                updatedData += taskType
                        + " * " + taskStatus
                        + " * " + currentTaskName
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
