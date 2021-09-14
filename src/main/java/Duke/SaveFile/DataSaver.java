package Duke.SaveFile;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;

import Duke.TaskTypes.Todo;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Deadline;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataSaver {

    private static final String DONE_STATUS = "1";
    private static final String NOT_DONE_STATUS = "0";

    public static void manageLoad(ArrayList<Task> taskList) {
        try {
            loadFileContents(taskList);
        } catch (DukeException | FileNotFoundException invalidDeleteException) {
            DukeException.InvalidSaveFileException();
        }
    }

    private static void loadFileContents(ArrayList<Task> taskList) throws FileNotFoundException, DukeException {
        File file = new File("duke.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            String newTask = scan.nextLine();
            addToTaskList(taskList, newTask);
        }
    }

    private static void addToTaskList(ArrayList<Task> taskList, String newTask) throws DukeException {
        String[] taskDetails = newTask.split("\\|");

        if (!checkValidDetails(taskDetails)) {
            throw new DukeException();
        }

        String type = taskDetails[0].trim();

        switch(type) {
        case "T":
            String todoDescription = taskDetails[2].trim();
            Todo addedTodo = new Todo(todoDescription);
            addDoneStatus(addedTodo, taskDetails);
            taskList.add(addedTodo);
            break;
        case "D":
            String deadlineDescription = taskDetails[2].trim();
            String deadlineBy = taskDetails[3].trim();
            Deadline addedDeadline = new Deadline(deadlineDescription, deadlineBy);
            addDoneStatus(addedDeadline, taskDetails);
            taskList.add(addedDeadline);
            break;
        case "E":
            String eventDescription = taskDetails[2].trim();
            String eventAt = taskDetails[3].trim();
            Event addedEvent = new Event(eventDescription, eventAt);
            addDoneStatus(addedEvent, taskDetails);
            taskList.add(addedEvent);
            break;
        default:
            throw new DukeException();
        }
    }

    private static boolean checkValidDetails(String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (!status.equals(DONE_STATUS) && !status.equals(NOT_DONE_STATUS)) {
            return false;
        }

        return taskDetails.length == 3 || taskDetails.length == 4;
    }

    private static void addDoneStatus(Task addedTask, String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (status.equals(DONE_STATUS)) {
            addedTask.markAsDone();
        }
    }



}
