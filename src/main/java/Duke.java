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
    private TaskList taskList;

    public Duke() {
        sc = new Scanner(System.in);
        taskList = new TaskList();
    }

    public String getResponse() {
        return sc.nextLine();
    }

    /** An array of tasks (no more than 100) */
    private static Task[] tasks = new Task[100];

    /** The total number of tasks */
    private static int totalNumberOfTasks = 0;

    /**
     * Lists all the tasks added.
     */
    public void list() {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Here are the tasks in your list:");
        for (int i = 1; i <= totalNumberOfTasks; i++) {
            System.out.println(Ui.PADDING + i + "." + tasks[i - 1]);
        }
        System.out.println(Ui.LINE);
    }

    /**
     * Marks a task as done and displays it.
     *
     * @param taskNumber Task number.
     */
    public void markDone(int taskNumber) {
        if (taskNumber <= totalNumberOfTasks) {
            tasks[taskNumber - 1].markAsDone();
            System.out.print(Ui.LINE);
            System.out.println(Ui.PADDING + "Nice! I've marked this task as done:");
            System.out.println(Ui.PADDING + "  " + tasks[taskNumber - 1]);
            System.out.println(Ui.LINE);
        } else {
            System.out.println(Ui.LINE);
            System.out.println(Ui.PADDING + "There are only " + totalNumberOfTasks + " tasks currently.");
            System.out.println(Ui.LINE);
        }
    }

    public void addTodo(String response) {
        tasks[totalNumberOfTasks] = new Todo(response);
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Got it. I've added this task:");
        System.out.println(Ui.PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(Ui.PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    public void addDeadline(String response) {
        String[] params = response.split(" /by ");
        String description = params[0];
        String by = params[1];
        tasks[totalNumberOfTasks] = new Deadline(description, by);
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Got it. I've added this task:");
        System.out.println(Ui.PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(Ui.PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    public void addEvent(String response) {
        String[] params = response.split(" /at ");
        String description = params[0];
        String by = params[1];
        tasks[totalNumberOfTasks] = new Event(description, by);
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Got it. I've added this task:");
        System.out.println(Ui.PADDING + "  " + tasks[totalNumberOfTasks]);
        totalNumberOfTasks++;
        System.out.println(Ui.PADDING + "Now you have " + totalNumberOfTasks + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    public void start() {
        String response = "";
        while (true) {
            response = getResponse();
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
                Ui.error();
            }
        }
    }

    public void run() {
        Ui.greet();
        this.start();
        Ui.bye();
    }

    /**
     * The main method of Duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
