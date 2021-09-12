package duke.control;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;

public class List {
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int DONE_NUMBER_INDEX = 5;
    private static final int DEADLINE_NAME_START_INDEX = 9;
    private static final int EVENT_NAME_START_INDEX = 6;
    private static final int DATETIME_START_INDEX_OFFSET = 3;
    private static final int DELETE_NUMBER_INDEX = 7;
    private int numberOfEntries = 0;
    private ArrayList<Task> taskList;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    public List() {
        taskList = new ArrayList<>();
    }

    public void addEntryToList(String input) throws InvalidInputFormatException {
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

    public void deleteEntry(int entryNumber) {
        printDeleteEntryMessage(taskList.get(entryNumber-1));
        taskList.remove(entryNumber-1);
        numberOfEntries--;
    }

    public void doneEntry(int entryNumber) {
        (taskList.get(entryNumber-1)).setDone();
        System.out.println((taskList.get(entryNumber-1)).getName() + " done. Well done.");
    }

    public int parseInputForEntryNumber(String input, Duke.Command command) {
        if (command.equals(Duke.Command.DONE_COMMAND)) {
            return Integer.parseInt(input.substring(DONE_NUMBER_INDEX));
        }
        if (command.equals(Duke.Command.DELETE_COMMAND)) {
            return Integer.parseInt(input.substring(DELETE_NUMBER_INDEX));
        }
        return 0;
    }

    public String parseInputForDateTime(String input) {
        int markerIndex = input.indexOf('/');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        return (input.substring(dateTimeStartIndex).trim());
    }

    public TaskType parseTaskType(String input) throws InvalidInputFormatException{
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
