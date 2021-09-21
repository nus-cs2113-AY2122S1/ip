package duke.control;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskWithDateTime;
import duke.task.ToDo;
import java.time.LocalDateTime;
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
     *                          actual index of the item is entryNumber-1
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
            Task newEntry;
            switch (entryType) {
            case TODO:
                newEntry = new ToDo(description);
                break;
            case DEADLINE:
                newEntry = addDeadlineFromFile(inputLineFromFile, description);
                break;
            case EVENT:
                newEntry = addEventFromFile(inputLineFromFile, description);
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

    private Deadline addDeadlineFromFile(String inputLineFromFile, String description) throws
            InvalidInputFormatException{
        LocalDateTime dateTime = Parser.parseDateTimeFromFile(inputLineFromFile);
        return new Deadline(description, dateTime);
    }

    private Event addEventFromFile(String inputLineFromFile, String description) throws InvalidInputFormatException {
        LocalDateTime dateTime = Parser.parseDateTimeFromFile(inputLineFromFile);
        return new Event(description, dateTime);
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints entry, for use in list command
     * @param entry Task entry from list
     * @param entryIndex index of the task from the list. Index is displayed as index + 1 as the list starts from 1 to
     *                   the user.
     */
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

    /**
     * Prints all the list entries that occur on a specific date.
     * @param input user input, in the form "date yyyy-mm-dd"
     */
    public void printTasksOnDate(String input) {
        LocalDateTime date = Parser.parseDateTimeFromDateCommand(input);
        for (int i = 0; i < numberOfEntries; i++) {
            Task t = taskList.get(i);
            if (t instanceof Deadline || t instanceof Event) {
                if (isSameDate(date, (TaskWithDateTime) t)); {
                    printEntry(t, i);
                }
            }
        }
    }

    /**
     * returns true if a Deadline or Event object has the same date as input date.
     * Cannot directly compare the LocalDateTime of the two objects as this method is only concerned with the date.
     * @param date date to check
     * @param entry entry to match with the date
     * @return true if entry's date matches with input date.
     */
    private Boolean isSameDate(LocalDateTime date, TaskWithDateTime entry) {
        LocalDateTime entryDateTime = entry.getDateTime();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int entryYear = entryDateTime.getYear();
        int entryMonth = entryDateTime.getMonthValue();
        int entryDay = entryDateTime.getDayOfMonth();
        if ((year == entryYear) && (month == entryMonth) && (day == entryDay)) {
            return true;
        }
        return false;
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
