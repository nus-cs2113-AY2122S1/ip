package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Converts string to Task objects and vice versa.
 * Parses the data so that they can be stored or read from memory.
 */
public class StorageDataParser {
    private String SPACE_AND_SEPARATOR = " | ";
    private int TASK_TYPE_INDEX = 0;
    private int DONE_TYPE_INDEX = 4;
    private int DESCRIPTION_INDEX = 8;

    /**
     * The function takes in an ArrayList of Task objects and converts them into a string.
     * Each task is saved as a string in a new line.
     * If no tasks exist, an empty string is returned.
     *
     * @param listTasks ListTasks an array of Task objects.
     * @return savedString A String of the converted Task objects.
     */
    public String saveListAsString (ArrayList<Task> listTasks) {
        if (listTasks.isEmpty()) {
            return "";
        }
        String taskDescription = "";
        String isDone = "";
        String taskType = "";
        String taskTimings = "";
        StringBuilder savedString = new StringBuilder();

        for (int i = 0; i < listTasks.size(); i++) {
            Task tempTask = listTasks.get(i);
            if (tempTask == null) {
                continue;
            }
            isDone = tempTask.isDone() ? "1" : "0";
            taskDescription = tempTask.getDescription();
            taskType = tempTask.getType();

            if (tempTask instanceof Event) {
                taskTimings = ((Event) tempTask).getAt();
            } else if (tempTask instanceof Deadline) {
                taskTimings = ((Deadline) tempTask).getBy();
            }

            savedString.append(taskType)
                    .append(SPACE_AND_SEPARATOR)
                    .append(isDone)
                    .append(SPACE_AND_SEPARATOR)
                    .append(taskDescription)
                    .append(SPACE_AND_SEPARATOR)
                    .append(taskTimings)
                    .append(System.lineSeparator());
        }
        return savedString.toString();
    }

    /**
     * The function reads the string saved in memory and converts it into a Task object.
     * It detects the type of task and parses it accordingly.
     * If a string is not detected, null is returned.
     *
     * @param str Str a string read from local storage file.
     * @return returns a Task object created from parsing str.
     */
    public Task readListFromMemory(String str) {
        String taskDescriptionSubstring = "";
        String taskDescription = "";
        String taskTiming = "";

        String isDone = "";
        String taskType = "";

        taskType = String.valueOf(str.charAt(TASK_TYPE_INDEX));
        isDone = String.valueOf(str.charAt(DONE_TYPE_INDEX));
        taskDescriptionSubstring = str.substring(DESCRIPTION_INDEX);
        taskDescription = taskDescriptionSubstring.substring(0,taskDescriptionSubstring.indexOf('|')).trim();

        if (taskType != "T") {
            taskTiming = taskDescriptionSubstring.substring(taskDescriptionSubstring.indexOf("|") + 2).trim();
        }

        //Generate task object
        if (taskType.equals("T")) {
            ToDo newToDo = new ToDo(taskDescription);
            if (isDone.equals("1")) {
                newToDo.setDone(true);
            } else {
                newToDo.setDone(false);
            }
            return newToDo;
        } else if (taskType.equals("E")) {
            Event newEvent = new Event(taskDescription,taskTiming);
            if (isDone.equals("1")) {
                newEvent.setDone(true);
            } else {
                newEvent.setDone(false);
            }
            return newEvent;
        } else if (taskType.equals("D")) {
            Deadline newDeadline = new Deadline(taskDescription,taskTiming);
            if (isDone.equals("1")) {
                newDeadline.setDone(true);
            } else {
                newDeadline.setDone(false);
            }
            return newDeadline;
        }
        return null;
    }
}
