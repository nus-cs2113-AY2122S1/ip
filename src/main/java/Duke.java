import java.util.Scanner;

/**
 * This class implements the Project Duke, a Personal
 * Assistant Chatbot that helps a user to keep track
 * of various things. Currently, Duke can greet user;
 * add/list user tasks, and mark them as "done".
 *
 * @author richwill28
 */
class Duke {
    private Scanner sc;

    public Duke() {
        sc = new Scanner(System.in);
    }

    /** Decorative logo */
    private static final String LOGO =
            "     ,---.    ,---.   ____    .-./`)  ______           \n" +
            "     |    \\  /    | .'  __ `. \\ .-.')|    _ `''.     \n" +
            "     |  ,  \\/  ,  |/   '  \\  \\/ `-' \\| _ | ) _  \\ \n" +
            "     |  |\\_   /|  ||___|  /  | `-'`\"`|( ''_'  ) |    \n" +
            "     |  _( )_/ |  |   _.-`   | .---. | . (_) `. |      \n" +
            "     | (_ o _) |  |.'   _    | |   | |(_    ._) '      \n" +
            "     |  (_,_)  |  ||  _( )_  | |   | |  (_.\\.' /      \n" +
            "     |  |      |  |\\ (_ o _) / |   | |       .'       \n" +
            "     '--'      '--' '.(_,_).'  '---' '-----'`          \n";

    /** Decorative line */
    private static final String LINE =
            "    ____________________________________________________________\n";

    /** Decorative padding */
    private static final String PADDING = "     ";

    /**
     * Displays logo and greets user.
     */
    private static void greet() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Hello! I'm your personal maid. Call me Maid-chan!");
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    public String getResponse() {
        return sc.nextLine();
    }

    /**
     * Says goodbye to user and exits the program.
     */
    private static void exit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }

    /** An array of tasks (no more than 100) */
    private static Task[] tasks = new Task[100];

    /** The total number of tasks */
    private static int totalNumberOfTasks = 0;

    private static void addTodo(String response) {
        tasks[totalNumberOfTasks] = new Todo(response);
        System.out.print(LINE);
        System.out.println(PADDING + "Got it. I've added this task:");
        System.out.println(PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addDeadline(String response) {
        String[] params = response.split(" /by ");
        String description = params[0];
        String by = params[1];
        tasks[totalNumberOfTasks] = new Deadline(description, by);
        System.out.print(LINE);
        System.out.println(PADDING + "Got it. I've added this task:");
        System.out.println(PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addEvent(String response) {
        String[] params = response.split(" /at ");
        String description = params[0];
        String by = params[1];
        tasks[totalNumberOfTasks] = new Event(description, by);
        System.out.print(LINE);
        System.out.println(PADDING + "Got it. I've added this task:");
        System.out.println(PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Lists all the tasks added.
     */
    private static void list() {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        for (int i = 1; i <= totalNumberOfTasks; i++) {
            System.out.println(PADDING + i + "." + tasks[i - 1]);
        }
        System.out.println(LINE);
    }

    /**
     * Marks a task as done and displays it.
     *
     * @param taskNumber Task number.
     */
    private static void markDone(int taskNumber) {
        if (taskNumber <= totalNumberOfTasks) {
            tasks[taskNumber - 1].markAsDone();
            System.out.print(LINE);
            System.out.println(PADDING + "Nice! I've marked this task as done:");
            System.out.println(PADDING + "  " + tasks[taskNumber - 1]);
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println(PADDING + "There are only " + totalNumberOfTasks + " tasks currently.");
            System.out.println(LINE);
        }
    }

    /**
     * The main method of Duke which implements its
     * chatting functionality.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        greet();
        String response = "";
        while (true) {
            response = duke.getResponse();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                list();
            } else if (response.split(" ")[0].equals("done")) {
                markDone(Integer.parseInt(response.split(" ")[1]));
            } else if (response.split(" ")[0].equals("todo")) {
                addTodo(response.replace("todo ", ""));
            } else if (response.split(" ")[0].equals("deadline")) {
                addDeadline(response.replace("deadline ", ""));
            } else if (response.split(" ")[0].equals("event")) {
                addEvent(response.replace("event ", ""));
            } else {
                System.out.println(LINE);
                System.out.println(PADDING + "Sorry.. I don't understand your command.");
                System.out.println(LINE);
            }
        }
        exit();
    }
}
