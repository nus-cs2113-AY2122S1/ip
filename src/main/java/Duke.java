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
    /** A scanner to read from standard input. */
    private Scanner sc;

    /** Stores the list of tasks */
    private TaskList taskList;

    /**
     * The constructor method. Initializes scanner and
     * tasks list.
     */
    public Duke() {
        sc = new Scanner(System.in);
        taskList = new TaskList();
    }

    /**
     * Returns user response.
     *
     * @return User response.
     */
    public String getResponse() {
        return sc.nextLine();
    }

    /**
     * Lists all the tasks.
     */
    public void list() {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(Ui.LINE);
    }

    /**
     * Marks a task as done and displays it.
     *
     * @param taskNumber Task number.
     */
    public void markAsDone(int taskNumber) {
        if (taskNumber <= taskList.getSize()) {
            taskList.markAsDone(taskNumber - 1);
            System.out.print(Ui.LINE);
            System.out.println(Ui.PADDING + "Nice! I've marked this task as done:");
            System.out.println(Ui.PADDING + "  " + taskList.getTask(taskNumber - 1));
            System.out.println(Ui.LINE);
        } else {
            System.out.println(Ui.LINE);
            System.out.println(Ui.PADDING + "There are only " + taskList.getSize() + " tasks currently.");
            System.out.println(Ui.LINE);
        }
    }

    /**
     * Reports to user that the task is added
     * successfully.
     *
     * @param task User task.
     */
    public void reportTaskAdded(Task task) {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Got it. I've added this task:");
        System.out.println(Ui.PADDING + "  " + task);
        System.out.println(Ui.PADDING + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Adds Todo to the list of tasks.
     *
     * @param response User response, consists of only
     *                 description.
     */
    public void addTodo(String response) {
        Task task = new Todo(response);
        taskList.addTask(task);
        reportTaskAdded(task);
    }

    /**
     * Add Deadline to the list of tasks.
     *
     * @param response User response, consists of
     *                 description and deadline.
     */
    public void addDeadline(String response) {
        String[] params = response.split(" /by ");
        String description = params[0];
        String by = params[1];
        Task task = new Deadline(description, by);
        taskList.addTask(task);
        reportTaskAdded(task);
    }

    /**
     * Add Event to the list of tasks.
     *
     * @param response User response, consists of
     *                 description and time period.
     */
    public void addEvent(String response) {
        String[] params = response.split(" /at ");
        String description = params[0];
        String at = params[1];
        Task task = new Event(description, at);
        taskList.addTask(task);
        reportTaskAdded(task);
    }

    /**
     * Starts the chatting functionality of Duke.
     */
    public void start() {
        String response = "";
        while (true) {
            response = getResponse();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                this.list();
            } else if (response.split(" ")[0].equals("done")) {
                this.markAsDone(Integer.parseInt(response.split(" ")[1]));
            } else if (response.split(" ")[0].equals("todo")) {
                this.addTodo(response.replace("todo ", ""));
            } else if (response.split(" ")[0].equals("deadline")) {
                this.addDeadline(response.replace("deadline ", ""));
            } else if (response.split(" ")[0].equals("event")) {
                this.addEvent(response.replace("event ", ""));
            } else {
                Ui.error();
            }
        }
    }

    /**
     * Runs the whole Duke program.
     */
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
