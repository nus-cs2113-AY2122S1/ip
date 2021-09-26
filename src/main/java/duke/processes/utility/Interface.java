package duke.processes.utility;

import duke.processes.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Interface {

    public static final String lineBreak = "..........................." +
            ".......................................";
    public static final String logo = "                         |" + System.lineSeparator() +
            "_______________     ___ -+- _______________" + System.lineSeparator() +
            "\\______       /    /_  \\ |  \\       ______/" + System.lineSeparator() +
            " \\______      \\_____/   \\___/      ______/" + System.lineSeparator() +
            "  \\______          IKAROS         ______/" + System.lineSeparator() +
            "      \\_____________     ___________/" + System.lineSeparator() +
            "                  /_______\\" + System.lineSeparator() +
            "              Welcome to IKAROS!\n" +
            "           Your one and only butler\n";

    /**
     * reads the user input and returns it in a String form
     *
     * @return string form of the users response
     */
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Greets user and displays today's date and day of the week
     */
    public static void introductoryMessage() {

        System.out.println(lineBreak);

        System.out.println(logo);
        System.out.println(lineBreak);
        displayCommandList();
        System.out.println(lineBreak);
        System.out.println("Below is your current list of tasks."
                + System.lineSeparator()
                + "What further assistance do you require?");
        System.out.println(lineBreak);
        LocalDate d = LocalDate.now();

        System.out.println("Today's Date: " + d.getDayOfWeek() + ", "
                + d.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        System.out.println(lineBreak);
    }

    /**
     * prints the different commands the user can use in this program
     */
    public static void displayCommandList() {
        System.out.println("Below are the list of acceptable commands"
                + System.lineSeparator() + System.lineSeparator()
                + "add todo -> add a todo task to the list [add todo todo_name]"
                + System.lineSeparator()
                + "add event -> adds an event task to the list [add event event_name " +
                "/at-dd/MM/yyyy-HHmm"
                + System.lineSeparator()
                + "add deadline -> adds a deadline task to the list " +
                "[add deadline deadline_name /by-dd/MM/yyyy-HHmm"
                + System.lineSeparator()
                + "remove -> removes a task [remove task_number]"
                + System.lineSeparator()
                + "done -> marks a task as complete [done task_number]"
                + System.lineSeparator()
                + "undo -> marks a task as undone [undo task_number]"
                + System.lineSeparator()
                + "list -> Display the list of all tasks"
                + System.lineSeparator()
                + "list event -> Display the list of all Events sorted by the Date and Time"
                + System.lineSeparator()
                + "list deadline -> Display the list of all Deadlines sorted by the Date and Time"
                + System.lineSeparator()
                + "list todo -> Display the list of all ToDos"
                + System.lineSeparator()
                + "update -> updates either a description or date of a specified task " +
                "[update task_number description(or)date]"
                + System.lineSeparator()
                + "bye -> kill ikaros");
    }

    /**
     * Bid Farewell to the user
     */
    public static void goodbyeMessage() {

        System.out.println("GoodBye, Ikaros awaits for future commands" + System.lineSeparator() +
                 "              *FLIES AWAY*");
        System.out.println(Interface.lineBreak);
    }

    /**
     * prints the list of an Arraylist of elements of type Task in the specific format
     *
     * @param tasks Arraylist of elements of type Task
     */
    public static void printList(ArrayList<Task> tasks) {

        AtomicInteger i = new AtomicInteger();

        tasks.stream()
                .forEach(task -> {
                    i.getAndIncrement();
                    System.out.print(i + ".[" + task.getTaskID() + "]" +
                            "[" + task.getStatusIcon() +
                            "] " + task.description + " ");
                    if (task.getTaskType().equalsIgnoreCase("event")) {
                        System.out.println("(at: " + task.getDate() + ")");
                    } else if (task.getTaskType().equalsIgnoreCase("deadline")) {
                        System.out.println("(by: " + task.getDate() + ")");
                    } else if (task.getTaskType().equalsIgnoreCase("todo")) {
                        System.out.println(task.getDate());
                    }
                });
    }
}
