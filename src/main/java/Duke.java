import java.util.Scanner;

public class Duke {

    /**
     * Prints a message when the user runs the program.
     */
    public static void greetUser() {
        String HELLOMESSAGE = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣶⣾⣿⣿⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣅⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⣠⣶⡿⠿⠿⢿⣶⣴⣾⠿⠛⠋⠁⠀⠉⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢿⣶⣄⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⣼⣿⠁⠀⠀⣠⣾⠿⢋⣀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠛⠛⠿⣿⣿⣿⣿⣿⣿⡿⠿⣿⣿⠛⠀⠈⢿⣧⠀⠀\n"
                + "⠀⠀⠀⠀⠀⣿⣿⠀⢀⣾⣿⣿⡿⠛⠉⠉⠉⠉⢉⣭⣭⣉⠙⠳⣶⡆⠀⠐⣶⠶⠋⣉⣭⣭⣉⠉⠉⠉⠉⠙⢻⣿⣿⣷⡀⠀⣸⣿⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠘⣿⣦⣿⠏⠈⢿⡇⠀⠀⠀⠀⢸⣿⣿⣿⣿⣧⠀⣸⡗⠀⠐⣿⠀⢸⣿⣿⣿⣿⣧⠀⠀⠀⠀⢀⣿⠉⢻⣷⣴⣿⠏⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⣽⣿⠀⠀⠘⢷⡀⠀⠀⠀⠸⣿⣿⣿⣿⠏⣴⠟⠀⠀⠀⠘⣷⡸⣿⣿⣿⣿⠏⠀⠀⠀⠀⣼⠃⠀⠀⣿⡏⠁⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⢻⣿⠀⠀⠀⠈⠻⠦⣄⣀⣀⣀⣉⣩⡴⠟⠁⠀⠀⠀⠀⠀⠈⠛⠶⢭⣉⣀⣀⣀⣀⠤⠞⠁⠀⠀⠀⣿⡇⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠘⣿⡆⠀⠀⠀⠀⠀⠀⠀⠈⣽⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢧⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠁⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⢹⣯⠀⠀⠀⠀⠀⠀⠀⣰⠃⢠⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡄⠘⡇⠀⠀⠀⠀⠀⠀⢰⡿⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠄⠀⠀⠀⠂⠀⠀⡟⠀⠈⠛⠿⢷⣶⣶⣶⣶⣶⣶⣶⡾⠿⠋⠁⠀⠱⠀⠀⠀⠀⠀⢰⣿⠃⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣆⠀⢈⠀⠀⠀⠀⠀⠀⢷⠀⠀⠀⠀⡌⠉⠉⠉⠁⠀⠄⠀⣤⠀⠀⠀⠀⠀⠀⠀⠀⣿⡏⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠇⢀⠘⠁⠁⠀⡀⠀⡀⠘⢷⣤⣀⣀⠃⠀⠀⠀⣀⣈⣤⠾⠃⠀⠀⢀⢠⠀⡁⠀⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡔⣀⡀⡐⣦⠀⠀⠀⠂⠀⠀⠈⠉⠉⠉⠛⠛⠋⠏⠉⠀⠀⣰⡤⠐⠈⢠⣠⠃⠀⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠀⣿⡀⡁⠹⣆⠀⠀⠀⠀⠀⠀⠀⠐⠀⠁⠈⠀⠀⢄⣸⡾⠫⠐⠀⠀⣼⠋⠀⢱⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠘⢧⡁⠀⠙⠳⢶⣤⣄⣀⣀⣠⣀⣢⣦⣤⠶⠞⠛⠁⠀⠀⠀⢠⡼⠃⠢⢠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠈⠻⣦⡀⠀⠀⠀⠉⠉⢹⠉⠉⠙⡁⡀⠀⠀⠀⠀⣀⣠⡶⠋⠀⠀⢠⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⣄⡄⠀⡊⠙⠳⠶⠶⠶⢾⣤⣤⣤⣤⣧⣷⢶⠶⠞⢻⠉⠁⢠⠠⢈⣴⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢷⣧⣤⣀⣀⣀⣄⣄⣀⣀⣀⣀⣀⣂⣠⣀⣡⣠⣀⣠⣦⣴⡾⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠋⠙⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "Greetings, I'm DAHNAM. I'm definitely a human, not a machine!\nHow may I help you?\n";
        System.out.println(HELLOMESSAGE);
    }

    /**
     * Prints out a message when the user inputs 'bye'
     */
    public static void bidGoodbye() {
        String GOODBYEMESSAGE = "Alas, our lovely time together comes to an end. Au revoir!\n";
        System.out.println(GOODBYEMESSAGE);
    }

    /**
     * Prints a message denying any and all allegations of DAHNAM being a bot when prompted with 'bot?'
     */
    public static void denyBotNature() {
        String DENYBOTNATURE = "No, I am definitely not a bot. Why do you ask?\n";
        System.out.println(DENYBOTNATURE);
    }

    /**
     * Enumerates through an array of tasks and prints out all tasks input by user
     *
     * @param taskList An array of tasks. Max size of 100
     */
    public static void listAllTasks(Task[] taskList, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    /**
     * Notifies user of what item is added to the task list upon receiving a valid task input.
     *
     * @param t user input task
     */
    public static void echoUserInput(Task t, int taskNum) {
        System.out.println("Added: " + t.toString() + "\n You currently have " + taskNum + " tasks");
    }

    /**
     * Adds the task 't' specified by user into the task list
     *
     * @param taskList An array of tasks. Max size of 100
     * @param t        user input task
     */
    public static void addToTaskList(Task[] taskList, Task t, int index) {
        taskList[index] = t;
    }

    /**
     * Modifies a task and sets its boolean isDone to true. Prints out an acknowledgement after.
     *
     * @param taskList An array of tasks. Max size of 100
     * @param i        Index of the task shown when user inputs 'list'
     */
    public static void completeTask(Task[] taskList, int i) {
        //Navigate to the given index and change the sign
        String lineBar = "____________________________________________________________\n";
        if ((i < 1) || (taskList[i - 1] == null)) {
            System.out.println(lineBar);
            System.out.println("No task found with the given number. \nPlease type 'list' to view your current task "
                    + "list");
            System.out.println(lineBar);
            return;
        }
        i--;
        taskList[i].markAsDone();
        System.out.println(lineBar);
        System.out.println(
                "Bueno! The following task is marked as done: \n[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].taskDescription);
        System.out.println(lineBar);
    }

    /**
     * This function is called upon program execution.
     *
     * @param args System arguments that are added to the program
     */
    public static void main(String[] args) {
        String lineBar = "____________________________________________________________\n";
        greetUser();
        Scanner readUserInput = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskIndex = 0;
        String userInput;
        String identifier;
        String taskDescription;
        String timing;

        while (true) {
            userInput = readUserInput.nextLine();
            identifier = userInput.split(" ")[0];

            switch (identifier) {
            case "done":
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                completeTask(taskList, taskNumber);
                continue;

            case "bye":
                System.out.println(lineBar);
                bidGoodbye();
                System.out.println(lineBar);
                return;

            case "bot?":
                System.out.println(lineBar);
                denyBotNature();
                System.out.println(lineBar);
                continue;

            case "list":
                System.out.println(lineBar);
                listAllTasks(taskList, taskIndex);
                System.out.println(lineBar);
                continue;

            case "todo":
                System.out.println(lineBar);
                taskDescription = userInput.substring(5); //todo TASK. first 5 char gets stripped, start from index 5
                ToDo todo = new ToDo(taskDescription);
                addToTaskList(taskList, todo, taskIndex);
                taskIndex++;
                echoUserInput(todo, taskIndex);
                System.out.println(lineBar);
                continue;

            case "event":
                System.out.println(lineBar);
                taskDescription = userInput.split("/at ")[0].substring(6);
                timing = userInput.split("/at ")[1];
                Event event = new Event(taskDescription, timing);
                addToTaskList(taskList, event, taskIndex);
                taskIndex++;
                echoUserInput(event, taskIndex);
                System.out.println(lineBar);
                continue;

            case "deadline":
                System.out.println(lineBar);
                taskDescription = userInput.split("/by ")[0].substring(9);
                timing = userInput.split("/by ")[1];
                Deadline deadline = new Deadline(taskDescription, timing);
                addToTaskList(taskList, deadline, taskIndex);
                taskIndex++;
                echoUserInput(deadline, taskIndex);
                System.out.println(lineBar);
                continue;

            default:
                System.out.println(lineBar);
                Task task = new Task(userInput);
                addToTaskList(taskList, task, taskIndex);
                taskIndex++;
                echoUserInput(task, taskIndex);
                System.out.println(lineBar);
            }
        }
    }
}