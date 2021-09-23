package duke.program;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";
    private static final String SEPARATOR_TO = "to";
    private static final String ICON_DONE = "X";
    private static final DateTimeFormatter DATE_TIME_FORMAT_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static ArrayList<Task> tasks;
    private static int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    public Task getLatestTask() {
        return tasks.get(taskCount-1);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void addNewTodo(String rawDescription)
            throws EmptyDescriptionException {
        String description = rawDescription.trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        tasks.add(new ToDo(description));
        taskCount++;
    }

    public void addNewDeadline(String descriptionAndTime)
            throws EmptyDescriptionException, InvalidFormatException, DateTimeParseException {
        String[] descriptionAndByTimeArray = descriptionAndTime.split(SEPARATOR_BY);
        String description = descriptionAndByTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndByTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String byDateTimeString = descriptionAndByTimeArray[1].trim();
        LocalDateTime byDateTime = LocalDateTime.parse(byDateTimeString, DATE_TIME_FORMAT_INPUT);

        tasks.add(new Deadline(description, byDateTime));
        taskCount++;
    }

    public void addNewEvent(String descriptionAndTime)
            throws EmptyDescriptionException, InvalidFormatException {
        String[] descriptionAndAtTimeArray = descriptionAndTime.split(SEPARATOR_AT);
        String description = descriptionAndAtTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndAtTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String[] startAndEndTimeArray = descriptionAndAtTimeArray[1].split(SEPARATOR_TO);
        String startTimeString = startAndEndTimeArray[0].trim();
        String endTimeString = startAndEndTimeArray[1].trim();
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, DATE_TIME_FORMAT_INPUT);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, DATE_TIME_FORMAT_INPUT);

        tasks.add(new Event(description, startTime, endTime));
        taskCount++;
    }

    public void markAsDone(ArrayList<Task> tasks, String[] lineArgs, LizUi ui)
            throws InvalidIndexException, TaskIndexOutOfBoundsException {
        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int doneIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (doneIndex >= taskCount || doneIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {
            if (tasks.get(doneIndex).getStatusIcon().equals(ICON_DONE)) {
                ui.printAlreadyDoneMessage();
            } else {
                tasks.get(doneIndex).setDone();
                ui.printMarkAsDoneMessage(tasks.get(doneIndex));
            }
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String[] lineArgs, LizUi ui)
            throws InvalidIndexException, TaskIndexOutOfBoundsException {
        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int deleteIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (deleteIndex >= taskCount || deleteIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {

            //taskCount - 1 is passed as the taskCount parameter for printDeletedTaskMessage since
            //print method is called first before removing
            ui.printDeletedTaskMessage(tasks.get(deleteIndex), taskCount - 1);
            tasks.remove(deleteIndex);
        }

        taskCount--;
    }
}
