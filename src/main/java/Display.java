public class Display {
    public static final String TASK_INCOMPLETE = " ";
    public static final String TASK_COMPLETE = "X";
    public static final String TODO_TASK_TYPE = "T";

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
        String checkboxDisplay = "[" + checkBoxType + "]";
        return checkboxDisplay;
    }
}
