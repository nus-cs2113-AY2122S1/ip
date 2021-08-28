public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int numOfTask = 0;

    private static String[] splitDescription(String description, String splitBy) {
        return description.split(splitBy, 2);
    }

    public static Task getLatestTask() {
        return tasks[numOfTask - 1];
    }

    public static Task getTask(int num) {
        return tasks[num - 1];
    }

    public static int getNumOfTask() {
        return numOfTask;
    }

    public static void setDone(int num) {
        tasks[num - 1].markDone();
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
        final int DESCRIPTION_INDEX = 0;
        final int BY_OR_AT_INDEX = 1;
        switch (type) {
        case ADD_TODO:
            if (!description.isBlank()) {
                tasks[numOfTask] = new ToDo(description);
                numOfTask++;
                return true;
            } else {
                return false;
            }

        case ADD_DEADLINE:
            String[] descriptions1;
            descriptions1 = splitDescription(description, " /by ");

            try {
                tasks[numOfTask] = new Deadline(descriptions1[DESCRIPTION_INDEX], descriptions1[BY_OR_AT_INDEX]);
                if (descriptions1[DESCRIPTION_INDEX].isBlank() || descriptions1[BY_OR_AT_INDEX].isBlank()) {
                    return false;
                }
                numOfTask++;
                return true;
            } catch (Exception e) {
                return false;
            }

        case ADD_EVENT:
            String[] descriptions2;
            descriptions2 = splitDescription(description, " /at ");
            try {
                tasks[numOfTask] = new Event(descriptions2[DESCRIPTION_INDEX], descriptions2[BY_OR_AT_INDEX]);
                if (descriptions2[DESCRIPTION_INDEX].isBlank() || descriptions2[BY_OR_AT_INDEX].isBlank()) {
                    return false;
                }
                numOfTask++;
                return true;
            } catch (Exception e) {
                return false;
            }

        default:
            return false;
        }
    }
}
