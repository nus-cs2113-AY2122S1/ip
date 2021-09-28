import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public static void addDeadline(String description, String date) {
        tasks.add(new Deadline(description, date));
    }

    public static void addEvent(String description, String date) {
        tasks.add(new Event(description, date));
    }

    public static void removeTask(int index) {
        tasks.remove(index);
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static void printTaskList() {
        System.out.println(Ui.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + "." + getTask(i));
        }
    }

    public static void printAddedTask() {
        System.out.println(Ui.INDENT + "Got it. I've added this task:" + Ui.LINE_SEPARATOR_AND_INDENT +
                getTask(tasks.size() - 1) + Ui.LINE_SEPARATOR_AND_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printDeletedTask(int index) {
        System.out.println(Ui.INDENT + "Got it. I've removed this task:" + Ui.LINE_SEPARATOR_AND_INDENT +
                getTask(index) + Ui.LINE_SEPARATOR_AND_INDENT + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }
}
