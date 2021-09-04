public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int numOfTask = 0;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int BY_OR_AT_INDEX = 1;

    public static Task getLatestTask() {
        return tasks[numOfTask - 1];
    }

    public static Task getTask(int num) {
        return tasks[num - 1];
    }

    public static int getNumOfTask() {
        return numOfTask;
    }

    public static boolean setDone(int taskIndex) {
        if (checkCorrectIndex(taskIndex)) {
            tasks[taskIndex - 1].markDone();
            return true;
        }
        return false;
    }

    private static String[] splitDescription(String description, String splitBy) {
        return description.split(splitBy, 2);
    }

    public static void printList() {
        if (numOfTask == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Here are your items: ");
        for (int i = 0; i < numOfTask; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
    }

    public static boolean checkCorrectIndex(int index) {
        return index > 0 && index <= numOfTask;
    }

    public static void addTask(Command type, String description) throws DukeBlankDescriptionsException{
        switch (type) {
        case ADD_TODO:
            addToDo(description);
            break;
        case ADD_DEADLINE:
            addDeadline(description);
            break;
        case ADD_EVENT:
            addEvent(description);
            break;
        }
    }

    private static void addToDo(String description) throws DukeBlankDescriptionsException{
        if (!description.isBlank()) {
            tasks[numOfTask] = new ToDo(description);
            numOfTask++;
        } else {
            throw new DukeBlankDescriptionsException();
        }
    }

    private static void addEvent(String description) throws DukeBlankDescriptionsException{
        String[] descriptions;
        descriptions = splitDescription(description, " /at ");
        tasks[numOfTask] = new Event(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]);
        if (checkBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        numOfTask++;
    }

    private static void addDeadline(String description) throws DukeBlankDescriptionsException{
        String[] descriptions;
        descriptions = splitDescription(description, " /by ");
        tasks[numOfTask] = new Deadline(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]);
        if (checkBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        numOfTask++;
    }

    private static boolean checkBlankEntry(String[] descriptions) {
        return descriptions[TASK_DESCRIPTION_INDEX].isBlank() || descriptions[BY_OR_AT_INDEX].isBlank();
    }
}
