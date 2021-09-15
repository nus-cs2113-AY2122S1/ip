import java.util.Arrays;

public class choose {
    protected static Task[] list = new Task[100];
    private static final int tasksAdded = 0;
    private static final int LINE_WIDTH = 60;
    private static final Task[] List = new Task[100];
    private static int listSize = 0;


    public static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public static void addTask(String line) {
        List[listSize] = new Task(line);
        listSize++;
    }

    public static void list() {
        for (int i = 0; i < listSize; i++) {
            System.out.println(i + 1 + "." + List[i]);
        }
    }

    public static void listLast() {
        System.out.println(List[listSize]);
    }

    public static void setDone(int doneNumber) {
        List[doneNumber - 1].setDone("X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(List[doneNumber - 1]);
    }

    public static void setDeadline(String item, String timing) {
        List[listSize] = new Deadline(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void setEvent(String item, String timing) {
        List[listSize] = new Event(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void setTodo(String item) {
        List[listSize] = new Todo(item);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }
}




