public class Display {

    public static final String CHECKBOX_TASK_INCOMPLETE = " ";
    public static final String CHECKBOX_TASK_COMPLETE = "X";
    public static final String CHECKBOX_TODO_TASK_TYPE = "T";
    public static final String CHECKBOX_DEADLINE_TASK_TYPE = "D";
    public static final String CHECKBOX_EVENT_TASK_TYPE = "E";

    public static final String TASK_NAME_TODO = "TODO";
    public static final String TASK_NAME_DEADLINE = "DEADLINE";
    public static final String TASK_NAME_EVENT = "EVENT";

    public static void printListTaskLine() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void printAddTaskLine() {
        System.out.println("*********************************");
    }

    public static void printSeparatingLine() {
        System.out.println("---------------------------------");
    }

    public static String createCheckboxDisplay(String checkBoxType) {
        return "[" + checkBoxType + "]";
    }

    public static String getStatusCheckbox(Boolean isCompleted) {
        if (isCompleted) {
            return Display.createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE);
        }
        return Display.createCheckboxDisplay(Display.CHECKBOX_TASK_INCOMPLETE);
    }

    public static String getTwoCheckboxDisplay(String taskType, Boolean isCompleted) {
        String taskCheckboxType = Display.createCheckboxDisplay(taskType);
        String statusCheckboxType = getStatusCheckbox(isCompleted);
        return taskCheckboxType + statusCheckboxType;
    }

    public static void displayTaskCreation(Task task, String taskType, int taskCount) {
        printAddTaskLine();
        System.out.println("Noted! I've added a new " + taskType + " task");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        printAddTaskLine();
    }

    public static void displayTaskCompleted(String taskName) {
        System.out.println("Nice! Marking " + taskName + " as done!");
        System.out.println(Display.createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE) + " " + taskName);
    }
}
