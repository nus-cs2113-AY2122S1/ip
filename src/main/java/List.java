public class List {
    private int numberOfEntries = 0;
    private Task[] taskList;
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int DEADLINE_NAME_START_INDEX = 9;
    private static final int EVENT_NAME_START_INDEX = 6;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    public List() {
        taskList = new Task[100];
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

    public void addEntryToList(String input) {
        TaskType entryType = parseTaskType(input);
        String description = parseDescription(input, entryType);
        if (entryType.equals(TaskType.INVALID)) {
            System.out.println("I don't know what you mean, please look at the instructions and try again");
        } else {
            switch (entryType) {
            case TODO:
                taskList[numberOfEntries] = new ToDo(description);
                break;
            case DEADLINE:
                taskList[numberOfEntries] = new Deadline(description, parseInputForDate(input));
                break;
            case EVENT:
                taskList[numberOfEntries] = new Event(description, parseInputForDate(input));
                break;
            }
            printAddEntryMessage(taskList[numberOfEntries]);
            numberOfEntries++;
        }
    }

    public int parseInputForEntryNumber(String input) {
        int entryNumber = Integer.parseInt(input.substring(5));
        return entryNumber;
    }

    public String parseInputForDate(String input) {
        int markerIndex = input.indexOf('/');
        return (input.substring(markerIndex + 3).trim());
    }

    public TaskType parseTaskType(String input) {
        if (input.contains("deadline") && input.contains("/by")) {
            return TaskType.DEADLINE;
        }
        if (input.contains("event") && input.contains(" /at")) {
            return TaskType.EVENT;
        }
        if (input.contains("todo")) {
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

    public void doneEntry(int entryNumber) {
        taskList[entryNumber-1].setDone();
        System.out.println(taskList[entryNumber-1].getName() + " done. Well done.");
    }
}
