/**
 * This class deals with operations on
 */
public class Ui {

    public static final String LINE = "    ____________________________________________________________";
    public static final String INDENT = "     ";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_AND_INDENT = LINE_SEPARATOR + INDENT;
    public static final String HELP_INSTRUCTIONS = "Learn how to use Duke:" +
            LINE_SEPARATOR_AND_INDENT + " To add a todo: todo task_name" +
            LINE_SEPARATOR_AND_INDENT + " To add an event: event /on event_date" +
            LINE_SEPARATOR_AND_INDENT + " To add a deadline: deadline /by deadline_date" +
            LINE_SEPARATOR_AND_INDENT + " To mark a task as done: done task_index" +
            LINE_SEPARATOR_AND_INDENT + " To delete a task: delete task_index" +
            LINE_SEPARATOR_AND_INDENT + " To view the list: list" +
            LINE_SEPARATOR_AND_INDENT + " To get help: help" +
            LINE_SEPARATOR_AND_INDENT + " To end the program: bye";

    public static void printWithLines(String line) {
        System.out.println(LINE + LINE_SEPARATOR + INDENT + line + LINE_SEPARATOR + LINE);
    }

    public static void printGreetMessage() {
        printWithLines("Hello! I'm Duke." + LINE_SEPARATOR_AND_INDENT + "What can I do for you?" +
                LINE_SEPARATOR + LINE_SEPARATOR_AND_INDENT + HELP_INSTRUCTIONS);
    }

    public static void printFarewellMessage() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    public static void showHelp() {
        System.out.println(INDENT + HELP_INSTRUCTIONS);
    }

    /**
     * Prints all the tasks
     */
    public static void printTaskList() {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + TaskList.getTask(i));
        }
    }

    /**
     * Prints the newly added task
     */
    public static void printAddedTask() {
        System.out.println(INDENT + "Got it. I've added this task:" + LINE_SEPARATOR_AND_INDENT +
                TaskList.getTask(TaskList.tasks.size() - 1) + LINE_SEPARATOR_AND_INDENT + "Now you have " + TaskList.tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the newly deleted task
     * @param index Index of the task to be deleted from tasks list
     */
    public static void printDeletedTask(int index) {
        System.out.println(INDENT + "Got it. I've removed this task:" + LINE_SEPARATOR_AND_INDENT +
                TaskList.getTask(index) + LINE_SEPARATOR_AND_INDENT + "Now you have " + (TaskList.tasks.size() - 1) + " tasks in the list.");
    }

    /**
     * Prints the newly marked as done task
     * @param task
     */
    public static void printDoneTask(Task task) {
        System.out.print(INDENT + "Nice! I've marked this task as done: " +
                LINE_SEPARATOR_AND_INDENT + " ");
        System.out.println(task);
    }
}
