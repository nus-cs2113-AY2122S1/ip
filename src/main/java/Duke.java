import java.util.Scanner;

public class Duke {

    /**
     * Prints a message when the user runs the program.
     */
    public static void greetUser() {
        String helloMessage = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
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
        System.out.println(helloMessage);
    }

    /**
     * Prints out a message when the user inputs 'bye'
     */
    public static void bidGoodbye() {
        String goodbyeMessage = "Alas, our lovely time together comes to an end. Au revoir!\n";
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints a message denying any and all allegations of DAHNAM being a bot when prompted with 'are you a bot?'
     */
    public static void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";
        System.out.println(denyBotNature);
    }

    /**
     * Enumerates through an array of tasks and prints out all tasks input by user
     * @param taskList An array of tasks. Max size of 100
     */
    public static void listAllTasks(Task[] taskList) {
        int i = 0;
        while (taskList[i] != null) {
            System.out.println((i + 1) + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].taskDescription);
            i++;
        }
    }

    /**
     * Notifies user of what item is added to the task list upon receiving a valid task input.
     * @param t user input task
     */
    public static void echoUserInput(Task t) {
        System.out.println("Added: " + t.taskDescription);
    }

    /**
     * Adds the task 't' specified by user into the task list
     * @param taskList An array of tasks. Max size of 100
     * @param t user input task
     */
    public static void addToTaskList(Task[] taskList, Task t) {
        int i = 0;
        while (taskList[i] != null) {
            i++;
        }
        taskList[i] = t;
    }

    /**
     * Modifies a task and sets its boolean isDone to true. Prints out an acknowledgement after.
     * @param taskList An array of tasks. Max size of 100
     * @param i Index of the task shown when user inputs 'list'
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
     * @param args System arguments that are added to the program
     */
    public static void main(String[] args) {
        String lineBar = "____________________________________________________________\n";
        greetUser();
        Scanner readUserInput = new Scanner(System.in);
        Task[] taskList = new Task[100];
        String userInput;

        while (true) {
            userInput = readUserInput.nextLine();

            if (userInput.contains("done ")) {
                String[] splitInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);
                completeTask(taskList, taskNumber);
            } else {
                switch (userInput) {
                case "bye":
                    System.out.println(lineBar);
                    bidGoodbye();
                    System.out.println(lineBar);
                    return;

                case "are you a bot?":
                    System.out.println(lineBar);
                    denyBotNature();
                    System.out.println(lineBar);
                    continue;

                case "list":
                    System.out.println(lineBar);
                    listAllTasks(taskList);
                    System.out.println(lineBar);
                    continue;

                default:
                    System.out.println(lineBar);
                    Task t = new Task(userInput);
                    addToTaskList(taskList, t);
                    echoUserInput(t);
                    System.out.println(lineBar);
                }
            }
        }
    }
}