package duke.control;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;


public class TaskList {
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int DONE_NUMBER_INDEX = 5;
    private static final int DEADLINE_NAME_START_INDEX = 9;
    private static final int EVENT_NAME_START_INDEX = 6;
    private static final int DATETIME_START_INDEX_OFFSET = 4;
    private static final int DELETE_NUMBER_INDEX = 7;
    private static final int FILE_TASK_NAME_INDEX = 7;
    private static final int FILE_TASKTYPE_INDEX = 1;
    private static final int FILE_ISDONE_INDEX = 4;
    private int numberOfEntries = 0;
    private final ArrayList<Task> taskList;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a new entry into the list. Entries are Tasks which can either be Todo, Deadline or Event objects.
     * Deadline and Event entries have an additional parameter of date and/or time which must also be set.
     * @param input user input
     * @throws InvalidInputFormatException user input is not in the correct format.
     */
    protected void addEntryToList(String input) throws InvalidInputFormatException {
        TaskType entryType = parseTaskType(input);
        String description = parseDescription(input, entryType);
        Task newEntry;
        switch (entryType) {
        case TODO:
            newEntry = new ToDo(description);
            taskList.add(newEntry);
            break;
        case DEADLINE:
            newEntry = new Deadline(description, parseInputForDateTime(input));
            taskList.add(newEntry);
            break;
        case EVENT:
            newEntry = new Event(description, parseInputForDateTime(input));
            taskList.add(newEntry);
            break;
        default:
            break;
        }
        printAddEntryMessage(taskList.get(numberOfEntries));
        numberOfEntries++;
    }

    /**
     * Deletes an item from the list
     * @param entryNumber the entry number for the item to be deleted. To the user, the list indexes from 1, so the
     *                    actual index of the item is entryNumber-1
     */
    protected void deleteEntry(int entryNumber) {
        printDeleteEntryMessage(taskList.get(entryNumber-1));
        taskList.remove(entryNumber-1);
        numberOfEntries--;
    }

    /**
     * Sets an entry in the list as done
     * @param entryNumber the entry number of the list item to be set. To the user, the list indexes from 1, so the
     *      *                    actual index of the item is entryNumber-1
     */
    protected void doneEntry(int entryNumber) {
        (taskList.get(entryNumber-1)).setDone();
        System.out.println((taskList.get(entryNumber-1)).getName() + " done. Well done.");
    }

    /**
     * Parses and returns the entry number entered for the "done" or "delete" commands
     * @param input user input
     * @param command type of command. Either "done" or "delete" commands
     * @return the entry number of the list entry to be set as done or deleted
     */
    protected int parseInputForEntryNumber(String input, Duke.Command command) {
        if (command.equals(Duke.Command.DONE_COMMAND)) {
            return Integer.parseInt(input.substring(DONE_NUMBER_INDEX));
        }
        if (command.equals(Duke.Command.DELETE_COMMAND)) {
            return Integer.parseInt(input.substring(DELETE_NUMBER_INDEX));
        }
        return 0;
    }

    private String parseInputForDateTime(String input) {
        int markerIndex = input.indexOf('/');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        return (input.substring(dateTimeStartIndex).trim());
    }

    private TaskType parseTaskType(String input) throws InvalidInputFormatException{
        if (input.startsWith("deadline") && input.contains(" /by")) {
            return TaskType.DEADLINE;
        }
        if (input.startsWith("event") && input.contains(" /at")) {
            return TaskType.EVENT;
        }
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        }
        throw new InvalidInputFormatException();
    }

    private String parseDescription(String input, TaskType taskType) {
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

    /**
     * Adds an entry to the list using one line of input obtained from the save file.
     * The string is stored in the same format as it is displayed: [T][X] name (at: dateTime)
     * InvalidInputFormatException will be thrown and caught if the data in the save file is not of the correct format.
     * i.e. it was saved incorrectly
     *
     * @param inputLineFromFile the String input that is read from the save file.
     */
    protected void addEntryFromFile(String inputLineFromFile) {
        try {
            TaskType entryType = parseTaskTypeFromFile(inputLineFromFile);
            boolean isDone = parseIsDoneFromFile(inputLineFromFile);
            String description = parseDescriptionFromFile(inputLineFromFile, entryType);
            String dateTime = "";
            if (entryType.equals(TaskType.DEADLINE) || entryType.equals(TaskType.EVENT)) {
                dateTime = parseDateTimeFromFile(inputLineFromFile);
            }

            Task newEntry;
            switch (entryType) {
            case TODO:
                newEntry = new ToDo(description);
                break;
            case DEADLINE:
                newEntry = new Deadline(description, dateTime);
                break;
            case EVENT:
                newEntry = new Event(description, dateTime);
                break;
            default:
                throw new InvalidInputFormatException();
            }
            taskList.add(newEntry);
            if (isDone) {
                taskList.get(numberOfEntries).setDone();
            }
            numberOfEntries++;
        } catch (InvalidInputFormatException e) {
            System.out.println("Something went wrong, could not load saved data");
        }
    }

    private boolean parseIsDoneFromFile(String inputLineFromFile) throws InvalidInputFormatException{
        switch (inputLineFromFile.charAt(FILE_ISDONE_INDEX)) {
        case (' '):
            return false;
        case ('X'):
            return true;
        default:
            throw new InvalidInputFormatException();
        }
    }

    private TaskType parseTaskTypeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        switch (inputLineFromFile.charAt(FILE_TASKTYPE_INDEX)) {
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

    private String parseDateTimeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        int markerIndex = inputLineFromFile.indexOf('(');
        int endIndex = inputLineFromFile.indexOf(')');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        return (inputLineFromFile.substring(dateTimeStartIndex, endIndex).trim());
    }

    private String parseDescriptionFromFile(String inputLineFromFile, TaskType taskType) throws
            InvalidInputFormatException, StringIndexOutOfBoundsException {
        if (taskType.equals(TaskType.TODO)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX);
        } else if (taskType.equals(TaskType.DEADLINE) || taskType.equals(TaskType.EVENT)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX, inputLineFromFile.indexOf(" ("));
        } else {
            throw new InvalidInputFormatException();
        }
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printEntry(Task entry, int entryIndex) {
        int entryNumber = entryIndex + 1;
        System.out.println(entryNumber + "." + entry.toString());
    }

    public void printList() {
        if (numberOfEntries == 0) {
            System.out.println("your list is empty");
        } else {
            System.out.println("Here is your current list");
            for (int i = 0; i < numberOfEntries; i++) {
                printEntry(taskList.get(i), i);
            }
            System.out.println("You have " + (numberOfEntries) + " task(s) on your list.");
        }
    }

    public void printAddEntryMessage(Task entry) {
        System.out.println("I've added the following task:");
        System.out.println(entry.toString());
    }

    public void printDeleteEntryMessage(Task entry) {
        System.out.println("I've deleted the following entry");
        System.out.println(entry.toString());
    }
}
