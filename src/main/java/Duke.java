import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];

    /** Prints a farewell message when the user exits the program. */
    public static void commandBye() {
        System.out.println("Bye! I hope to see you again soon :)");
    }

    /** Lists all tasks with their status. */
    public static void commandList() {
        System.out.println("Here are your current tasks and their status:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    /**
     * Adds a task of a certain type defined by the user to the list.
     *
     * @param line The command entered by the user.
     */
    public static void addTask(String line) throws EmptyCommandException, IllegalCommandException {

        if (!line.startsWith("todo") && !line.startsWith("deadline") && !line.startsWith("event")) {
            throw new IllegalCommandException();
        }

        if (line.split(" ").length < 2) {
            throw new EmptyCommandException();
        }

        if (line.startsWith("todo")) {
            tasks[Task.getTaskCount()] = new Todo(line.replace("todo ", ""));
        } else if (!line.contains("/")) {
            throw new IllegalCommandException();
        } else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            tasks[Task.getTaskCount()] = new Deadline(words[0].replace("deadline ", ""),
                                                                        words[1].replace("by ", ""));
        } else if (line.startsWith("event")) {
            String[] words = line.split("/");
            tasks[Task.getTaskCount()] = new Event(words[0].replace("event ", ""),
                                                                words[1].replace("at ", ""));
        }

        System.out.println("I've added that to your list:\n" + tasks[Task.getTaskCount() - 1]);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    /**
     * Sets a task in the list (specified by the user) as done.
     * The command is entered in the format "done X", where X is
     * the task's number.
     *
     * @param line The command entered by the user.
     */
    public static void doneTask(String line) {
        String[] words = line.split(" ");
        if (words.length < 2) {
            System.out.println("You didn't give me the task's number, try that command again.");
        } else {
            int taskIndex = Integer.parseInt(words[1]);
            tasks[taskIndex - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex - 1]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        // Read a command from the user.
        String line = in.nextLine();
        // Check which command the user entered and
        // call the corresponding method.
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                commandList();
            } else if (line.startsWith("done")) {
                doneTask(line);
            } else {
                try {
                    addTask(line);
                } catch (EmptyCommandException e) {
                    System.out.println("Sorry, you didn't give me a fitting description for your task.");
                } catch (IllegalCommandException e) {
                    System.out.println("That's not a known command format!");
                }
            }
            line = in.nextLine();
        }
        commandBye();
    }
}
