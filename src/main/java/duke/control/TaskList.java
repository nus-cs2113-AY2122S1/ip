package duke.control;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;


public class TaskList {
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
    public void addEntryToList(String input) throws InvalidInputFormatException {
        TaskType entryType = Parser.parseTaskType(input);
        String description = Parser.parseDescription(input, entryType);
        Task newEntry;
        switch (entryType) {
        case TODO:
            newEntry = new ToDo(description);
            taskList.add(newEntry);
            break;
        case DEADLINE:
            newEntry = new Deadline(description, Parser.parseInputForDateTime(input));
            taskList.add(newEntry);
            break;
        case EVENT:
            newEntry = new Event(description, Parser.parseInputForDateTime(input));
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
    public void deleteEntry(int entryNumber) {
        printDeleteEntryMessage(taskList.get(entryNumber-1));
        taskList.remove(entryNumber-1);
        numberOfEntries--;
    }

    /**
     * Sets an entry in the list as done
     * @param entryNumber the entry number of the list item to be set. To the user, the list indexes from 1, so the
     *      *                    actual index of the item is entryNumber-1
     */
    public void doneEntry(int entryNumber) {
        (taskList.get(entryNumber-1)).setDone();
        System.out.println((taskList.get(entryNumber-1)).getName() + " done. Well done.");
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
            TaskType entryType = Parser.parseTaskTypeFromFile(inputLineFromFile);
            boolean isDone = Parser.parseIsDoneFromFile(inputLineFromFile);
            String description = Parser.parseDescriptionFromFile(inputLineFromFile, entryType);
            String dateTime = "";
            if (entryType.equals(TaskType.DEADLINE) || entryType.equals(TaskType.EVENT)) {
                dateTime = Parser.parseDateTimeFromFile(inputLineFromFile);
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

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    private Boolean isSearchMatched(String searchTerm, Task entry) {
        return entry.getName().contains(searchTerm);
    }

    public void printSearchList(String input) {
        String searchTerm = Parser.parseSearchTerm(input);
        int matchCount = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (searchTerm.equals("")) {
                break;
            }
            if (isSearchMatched(searchTerm, taskList.get(i))) {
                printEntry(taskList.get(i), i);
                matchCount++;
            }
        }
        System.out.println("Number of results: " + matchCount);
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
