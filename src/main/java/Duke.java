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
     * @param response User response.
     */
    public void markAsDone(String response) {
        int taskNumber = Integer.parseInt(response.replace("done ", ""));
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
        Task task = new Todo(response.replace("todo ", ""));
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
        String[] params = response.replace("deadline ", "").split(" /by ");
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
        String[] params = response.replace("event ", "").split(" /at ");
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
        while (true) {
            Command response = new Command(getResponse());
            if (response.isBye()) {
                break;
            } else if (response.isList()) {
                this.list();
            } else if (response.isDone()) {
                this.markAsDone(response.getCommand());
            } else if (response.isTodo()) {
                this.addTodo(response.getCommand());
            } else if (response.isDeadline()) {
                this.addDeadline(response.getCommand());
            } else if (response.isEvent()) {
                this.addEvent(response.getCommand());
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
