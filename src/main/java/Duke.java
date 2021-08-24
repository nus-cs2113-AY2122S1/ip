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

    /**
     * Adds user command to the tasks.
     *
     * @param command User command.
     */
    private static void add(String command) {
        tasks[totalNumberOfTasks] = new Task(command);
        totalNumberOfTasks++;
        System.out.print(LINE);
        System.out.println(PADDING + "added: " + command);
        System.out.println(LINE);
    }

    /**
     * Lists all the tasks added.
     */
    private static void list() {
        System.out.print(LINE);
        for (int i = 1; i <= totalNumberOfTasks; i++) {
            System.out.println(PADDING + i + "." + tasks[i - 1]);
        }
        System.out.println(LINE);
    }

    /**
     * Marks a task as "done" and displays it.
     *
     * @param taskNumber Task number.
     */
    private static void done(int taskNumber) {
        if (taskNumber <= totalNumberOfTasks) {
            tasks[taskNumber - 1].markAsDone();
            System.out.print(LINE);
            System.out.println(PADDING + "Nice! I've marked this task as done:");
            System.out.println(PADDING + "  " + tasks[taskNumber - 1]);
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
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
        greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.split(" ")[0].equals("done")) {
                done(Integer.parseInt(command.split(" ")[1]));
            } else {
                add(command);
            }
        }
        exit();
    }
}
