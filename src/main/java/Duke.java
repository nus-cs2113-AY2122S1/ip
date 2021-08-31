import java.util.Scanner;

public class Duke {

    public static final int FIRST_WORD = 0;
    public static final int DESCRIPTION = 0;
    public static final int TASK_NUMBER = 1;
    public static final int TIMING = 1;
    public static final int SMALLEST_VALID_LIST_SIZE = 1;
    public static final int TODO_HEADER = 5;
    public static final int EVENT_HEADER = 6;
    public static final int DEADLINE_HEADER = 9;
    public static final int MAX_LIST_SIZE = 100;
    public static final String LINEBAR = "____________________________________________________________\n";
    public static final String EVENT_IDENTIFIER = "/at ";
    public static final String DEADLINE_IDENTIFIER = "/by ";


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
        if (index == 0) {
            System.out.println("You do not have any items listed currently.");
            return;
        }

        int taskNumber = 1;
        for (int i = 0; i < index; i++) {
            System.out.println((taskNumber) + "." + taskList[i].toString());
            taskNumber++;
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
        boolean isInvalidListSize = (i < SMALLEST_VALID_LIST_SIZE);
        //Navigate to the given index and change the sign
        if (isInvalidListSize) {
            System.out.println(LINEBAR);
            System.out.println("No task found with the given number. \nPlease type 'list' to view your current task "
                    + "list");
            System.out.println(LINEBAR);
            return;
        }
        i--;
        taskList[i].markAsDone();
        System.out.println(LINEBAR);
        System.out.println(
                "Bueno! The following task is marked as done: \n[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].taskDescription);
        System.out.println(LINEBAR);
    }

    /**
     * This function is called upon program execution.
     *
     * @param args System arguments that are added to the program
     */
    public static void main(String[] args) {
        greetUser();
        Scanner readUserInput = new Scanner(System.in);
        Task[] taskList = new Task[MAX_LIST_SIZE];
        int taskIndex = 0;
        String userInput;
        String identifier;
        String taskDescription;
        String timing;

        while (true) {
            userInput = readUserInput.nextLine();
            identifier = userInput.split(" ")[FIRST_WORD];

            switch (identifier) {
            case "done":
                int taskNumber = Integer.parseInt(userInput.split(" ")[TASK_NUMBER]);
                completeTask(taskList, taskNumber);

                continue;

            case "bye":
                System.out.println(LINEBAR);
                bidGoodbye();
                System.out.println(LINEBAR);
                return;

            case "bot?":
                System.out.println(LINEBAR);
                denyBotNature();
                System.out.println(LINEBAR);
                continue;

            case "list":
                System.out.println(LINEBAR);
                listAllTasks(taskList, taskIndex);
                System.out.println(LINEBAR);
                continue;

            case "todo":
                System.out.println(LINEBAR);
                taskDescription = userInput.substring(TODO_HEADER);
                ToDo todo = new ToDo(taskDescription);
                addToTaskList(taskList, todo, taskIndex);
                taskIndex++;
                echoUserInput(todo, taskIndex);
                System.out.println(LINEBAR);
                continue;

            case "event":
                System.out.println(LINEBAR);
                taskDescription = userInput.split(EVENT_IDENTIFIER)[DESCRIPTION].substring(EVENT_HEADER);
                timing = userInput.split(EVENT_IDENTIFIER)[TIMING];
                Event event = new Event(taskDescription, timing);
                addToTaskList(taskList, event, taskIndex);
                taskIndex++;
                echoUserInput(event, taskIndex);
                System.out.println(LINEBAR);
                continue;

            case "deadline":
                System.out.println(LINEBAR);
                taskDescription = userInput.split(DEADLINE_IDENTIFIER)[DESCRIPTION].substring(DEADLINE_HEADER);
                timing = userInput.split(DEADLINE_IDENTIFIER)[TIMING];
                Deadline deadline = new Deadline(taskDescription, timing);
                addToTaskList(taskList, deadline, taskIndex);
                taskIndex++;
                echoUserInput(deadline, taskIndex);
                System.out.println(LINEBAR);
                continue;

            default:
                System.out.println(LINEBAR);
                Task task = new Task(userInput);
                addToTaskList(taskList, task, taskIndex);
                taskIndex++;
                echoUserInput(task, taskIndex);
                System.out.println(LINEBAR);
            }
        }
    }
}