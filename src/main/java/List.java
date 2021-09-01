import java.lang.String;

public class List {

    public static String[] list = new String[100];
    public static String[] done = new String[100];
    public static int count = 0;
    public static int doneCount = 0;

    public static void printTask() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n     "
                + "Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println("     " + list[i]);
        }

        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printDoneTask() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n      "
                + "Nice! I've marked this task as done: ");
        for (int i = 0; i < doneCount; i++) {
            System.out.println("     " + done[i]);
        }
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void addTask(String task) {
        if (task.indexOf("/") != -1) {
            list[count] = (count + 1) + ".["
                + task.toUpperCase().charAt(0)
                + "][ ] "
                + task.substring(task.indexOf(" "), task.indexOf("/"))
                + "(" + task.substring(task.indexOf("/") + 1, task.indexOf("/") + 3)
                + ": " + task.substring(task.indexOf("/") + 4) + ")";
        } else {
            list[count] = (count + 1) + ".["
                + task.toUpperCase().charAt(0)
                + "][ ] "
                + task.substring(task.indexOf(" "));
        }
        count++;
        if (count == 1) {
            System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                    + "    Got it. I've added this task: \n      "
                    + list[count - 1].substring(2)
                    + "\n    Now you have " + count + " task in the list."
                    + "\n    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
            System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                    + "    Got it. I've added this task: \n      "
                    + list[count - 1].substring(2)
                    + "\n    Now you have " + count + " tasks in the list."
                    + "\n    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public static void doneTask(String task) {
        task = task.trim();
        String str = "";
        if (task != null && !"".equals(task)) {
            for (int i = 0; i < task.length(); i++) {
                if (task.charAt(i) >= 48 && task.charAt(i) <= 57) {
                    str = str + task.charAt(i);
                }
            }
        }
        for (int i = 0; i < count; i++) {
            if (str.equals(list[i].substring(0, str.length()))) {
                done[doneCount] = list[i].substring(str.length() + 1);
                list[i] = list[i].replaceFirst(" ", "X");
                done[doneCount] = done[doneCount].replaceFirst(" ", "X");
                doneCount++;
                printDoneTask();
                break;
            }
        }

    }
}
