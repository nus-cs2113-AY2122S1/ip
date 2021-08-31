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

    public static boolean addTask(Command type, String description) {
        switch (type) {
        case ADD_TODO:
            return addToDo(description);

        case ADD_DEADLINE:
            return addDeadline(description);

        case ADD_EVENT:
            return addEvent(description);

        default:
            return false;
        }
    }

    private static boolean addToDo(String description) {
        if (!description.isBlank()) {
            tasks[numOfTask] = new ToDo(description);
            numOfTask++;
            return true;
        } else {
            return false;
        }
    }

    private static boolean addEvent(String description) {
        String[] descriptions;
        descriptions = splitDescription(description, " /at ");
        try {
            tasks[numOfTask] = new Event(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]);
            if (checkBlankEntry(descriptions)) {
                return false;
            }
            numOfTask++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean addDeadline(String description) {
        String[] descriptions;
        descriptions = splitDescription(description, " /by ");

        try {
            tasks[numOfTask] = new Deadline(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]);
            if (checkBlankEntry(descriptions)) {
                return false;
            }
            numOfTask++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkBlankEntry(String[] descriptions) {
        return descriptions[TASK_DESCRIPTION_INDEX].isBlank() || descriptions[BY_OR_AT_INDEX].isBlank();
    }
}
