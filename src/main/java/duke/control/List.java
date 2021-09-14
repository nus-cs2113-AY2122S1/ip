package duke.control;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.StringBufferInputStream;

public class List {
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int DONE_NUMBER_INDEX = 5;
    private static final int DEADLINE_NAME_START_INDEX = 9;
    private static final int EVENT_NAME_START_INDEX = 6;
    private static final int LIST_MAX_ENTRIES = 100;
    private int numberOfEntries = 0;
    private Task[] taskList;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    public List() {
        taskList = new Task[LIST_MAX_ENTRIES];
    }

    public void addEntryToList(String input) {
        TaskType entryType = parseTaskType(input);
        if (entryType.equals(TaskType.INVALID)) {
            System.out.println("Command not entered properly, remember to use \"/by\" or " +
                    "\"/at\" modifiers for deadline and event tasks respectively. Type \"help\"" +
                    " for more information");
        } else {
            String description = parseDescription(input, entryType);
            switch (entryType) {
            case TODO:
                taskList[numberOfEntries] = new ToDo(description);
                break;
            case DEADLINE:
                taskList[numberOfEntries] = new Deadline(description, parseInputForDateTime(input));
                break;
            case EVENT:
                taskList[numberOfEntries] = new Event(description, parseInputForDateTime(input));
                break;
            }
            printAddEntryMessage(taskList[numberOfEntries]);
            numberOfEntries++;
        }
    }

    public int parseInputForEntryNumber(String input) {
        return Integer.parseInt(input.substring(DONE_NUMBER_INDEX));
    }

    public String parseInputForDateTime(String input) {
        int markerIndex = input.indexOf('/');
        //To account for the "by" or "at" proceeding the "/"
        int dateTimeStartIndex = markerIndex + 3;
        return (input.substring(dateTimeStartIndex).trim());
    }

    public TaskType parseTaskType(String input) {
        if (input.startsWith("deadline") && input.contains(" /by")) {
            return TaskType.DEADLINE;
        }
        if (input.startsWith("event") && input.contains(" /at")) {
            return TaskType.EVENT;
        }
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        }
        return TaskType.INVALID;
    }

    public String parseDescription(String input, TaskType taskType) {
        switch (taskType) {
        case TODO:
            return input.substring(TODO_NAME_START_INDEX);
        case DEADLINE:
            return input.substring(DEADLINE_NAME_START_INDEX, input.indexOf(" /"));
        case EVENT:
            return input.substring(EVENT_NAME_START_INDEX, input.indexOf(" /"));
        default:
            return input;
        }
    }

    public void getDataFromFile(String inputLineFromFile) {
        try {
            TaskType entryType = getTaskTypeFromFile(inputLineFromFile);
            Boolean isDone = getIsDoneFromFile(inputLineFromFile);
            String description = getDescriptionFromFile(inputLineFromFile, entryType);
            String dateTime = "";
            if (entryType.equals(TaskType.DEADLINE) || entryType.equals(TaskType.EVENT)) {
                dateTime = getDateTimeFromFile(inputLineFromFile);
            }
            switch (entryType) {
            case TODO:
                taskList[numberOfEntries] = new ToDo(description);
                break;
            case DEADLINE:
                taskList[numberOfEntries] = new Deadline(description, dateTime);
                break;
            case EVENT:
                taskList[numberOfEntries] = new Event(description, dateTime);
                break;
            default:
                throw new InvalidInputFormatException();
            }
            if (isDone) {
                taskList[numberOfEntries].setDone();
            }
            numberOfEntries++;
        } catch (InvalidInputFormatException e) {
            System.out.println("Something went wrong, could not load saved data");
        }
    }

    private boolean getIsDoneFromFile(String inputLineFromFile) throws InvalidInputFormatException{
        switch (inputLineFromFile.charAt(4)) {
        case (' '):
            return false;
        case ('X'):
            return true;
        default:
            throw new InvalidInputFormatException();
        }
    }

    private TaskType getTaskTypeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        switch (inputLineFromFile.charAt(1)) {
        case ('T'):
            return TaskType.TODO;
        case ('D'):
            return TaskType.DEADLINE;
        case ('E'):
            return TaskType.EVENT;
        default:
            throw new InvalidInputFormatException();
        }
    }

    private String getDateTimeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        int markerIndex = inputLineFromFile.indexOf('/');
        int dateTimeStartIndex = markerIndex + 3;
        return (inputLineFromFile.substring(dateTimeStartIndex).trim());
    }

    private String getDescriptionFromFile(String inputLineFromFile, TaskType taskType) throws
            InvalidInputFormatException {
        if (taskType.equals(TaskType.TODO)) {
            return inputLineFromFile.substring(7);
        } else if (taskType.equals(TaskType.DEADLINE) || taskType.equals(TaskType.EVENT)) {
            return inputLineFromFile.substring(7, inputLineFromFile.indexOf(" /"));
        } else {
            throw new InvalidInputFormatException();
        }
    }

    public void doneEntry(int entryNumber) {
        taskList[entryNumber-1].setDone();
        System.out.println(taskList[entryNumber-1].getName() + " done. Well done.");
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public Task[] getTaskList() {
        return taskList;
    }

    public void printEntry(Task entry, int entryIndex) {
        int entryNumber = entryIndex + 1;
        System.out.println(entryNumber + "." + entry.toString());
    }

    public void printList() {
        System.out.println("Here if your current list");
        for (int i = 0; i < numberOfEntries; i++) {
            printEntry(taskList[i], i);
        }
        System.out.println("You have " + (numberOfEntries) + " task(s) on your list.");
    }

    public void printAddEntryMessage(Task entry) {
        System.out.println("I've added the following task:");
        System.out.println(entry.toString());
    }
}
