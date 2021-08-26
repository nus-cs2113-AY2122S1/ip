import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];

    /**
     * Prints a farewell message when the user exits the program.
     */
    public static void commandBye() {
        System.out.println("Bye! I hope to see you again soon :)");
    }

    /**
     * Lists all tasks with their status
     */
    public static void commandList() {
        System.out.println("Here are your current tasks and their status:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescWithStatus());
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param desc The description of the new task to be added.
     */
    public static void addTask(String desc) {
        tasks[Task.getTaskCount()] = new Task(desc);
        System.out.println("I've added \"" + desc + "\" to your list.");
    }

    /**
     * Sets a task in the list (specified by the user) as done.
     * The command is entered in the format "done X", where X is
     * the task's number.
     *
     * @param words The command entered by the user separated into an array of strings.
     */
    public static void doneTask(String[] words) {
        if (words.length < 2) {
            System.out.println("You didn't give me the task's number, try that command again.");
        } else {
            int taskIndex = Integer.parseInt(words[1]);
            tasks[taskIndex - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex - 1].getDescWithStatus());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        // Read a command from the user.
        String line = in.nextLine();
        // Check which command the user entered and
        // call the corresponding method.
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                commandList();
            } else {
                String[] words = line.split(" ");
                if (words[0].equals("done")) {
                    doneTask(words);
                } else {
                    addTask(line);
                }
            }
            line = in.nextLine();
        }
        commandBye();
    }
}
