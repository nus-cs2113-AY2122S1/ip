import java.util.Scanner;

public class Duke {

    public static final int FIRST_WORD = 0;
    public static final int DESCRIPTION = 0;
    public static final int TASK_NUMBER = 1;
    public static final int TIMING = 1;
    public static final int TODO_HEADER = 5;
    public static final int EVENT_HEADER = 6;
    public static final int DEADLINE_HEADER = 9;
    public static final int MAX_LIST_SIZE = 100;
    public static final String LINEBAR = "____________________________________________________________\n";
    public static final String EVENT_IDENTIFIER = "/at ";
    public static final String DEADLINE_IDENTIFIER = "/by ";

    public static int taskIndex = 0;


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

        System.out.println(LINEBAR);
        System.out.println(GOODBYEMESSAGE);
        System.out.println(LINEBAR);
    }

    /**
     * Prints a message denying any and all allegations of DAHNAM being a bot when prompted with 'bot?'
     */
    public static void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";

        System.out.println(LINEBAR);
        System.out.println(denyBotNature);
        System.out.println(LINEBAR);
    }

    /**
     * Enumerates through an array of tasks and prints out all tasks input by user
     *
     * @param taskList An array of tasks. Max size of 100
     */
    public static void listAllTasks(Task[] taskList, int index) {
        if (index == 0) {
            System.out.println("You do not have any items in your list currently.");
            return;
        }

        int taskNumber = 1;

        System.out.println(LINEBAR);
        for (int i = 0; i < index; i++) {
            System.out.println((taskNumber) + "." + taskList[i].toString());
            taskNumber++;
        }
        System.out.println(LINEBAR);
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
     * @param userInput String that the user input
     * @param taskList  An array of tasks. Max size of 100
     * @throws NumberFormatException Thrown when the input is not a valid positive integer e.g. an alphabet
     * @throws NullPointerException Thrown when the value inserted exceeds the number of tasks
     * @throws ArrayIndexOutOfBoundsException Thrown when input is a negative value, or does not include a value
     */
    public static void completeTask(String userInput, Task[] taskList)
            throws NumberFormatException, NullPointerException, ArrayIndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[TASK_NUMBER]);

            //Navigate to the given index and change the sign
            int taskIndex = taskNumber - 1;
            taskList[taskIndex].markAsDone();
            System.out.println(LINEBAR);
            System.out.println(
                    "Bueno! The following task is marked as done: \n[" + taskList[taskIndex].getStatusIcon() + "] "
                            + taskList[taskIndex].taskDescription);
        } catch (NumberFormatException e) {
            System.out.println("You must input a positive integer. Format: done [Task Number]");
        } catch (NullPointerException e) {
            System.out.println("The value you inserted is invalid. Please type 'list' to check the number of tasks");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The value you inserted must be a positive integer");
        }

        System.out.println(LINEBAR);
    }

    /**
     * Adds an event to the task list
     * @param userInput String that the user input
     * @param taskList  An array of tasks. Max size of 100
     * @throws StringIndexOutOfBoundsException Thrown when userInput does not have a description after 'event'
     * @throws ArrayIndexOutOfBoundsException Thrown when userInput does not follow [Description] /at [Time]
     */
    public static void addEvent(String userInput, Task[] taskList)
            throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            String taskDescription = userInput.split(EVENT_IDENTIFIER)[DESCRIPTION].substring(EVENT_HEADER);
            String timing = userInput.split(EVENT_IDENTIFIER)[TIMING];
            Event event = new Event(taskDescription, timing);
            addToTaskList(taskList, event, taskIndex);
            taskIndex++;
            echoUserInput(event, taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("String Index OOB. Did you forgot the /at? Format: event [Description] /at [Time]");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index OOB. Did you forget the /at? Format: event [Description] /at [Time]");
        }

        System.out.println(LINEBAR);
    }

    /**
     * Adds a todo to the task list
     * @param userInput String that the user input
     * @param taskList An array of tasks. Max size of 100
     * @throws StringIndexOutOfBoundsException Thrown when user does not include description
     */
    public static void addToDo(String userInput, Task[] taskList) throws StringIndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            String taskDescription = userInput.substring(TODO_HEADER);
            ToDo todo = new ToDo(taskDescription);
            addToTaskList(taskList, todo, taskIndex);
            taskIndex++;
            echoUserInput(todo, taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("String Index OOB. Looks like you forgot the description! Format: todo [description]");
        }

        System.out.println(LINEBAR);
    }

    /**
     * Adds a deadline to the task list
     * @param userInput String that user input
     * @param taskList An array of tasks. Max size of 100
     * @throws StringIndexOutOfBoundsException Thrown when user does not include any description after 'deadline'
     * @throws ArrayIndexOutOfBoundsException Thrown when user input does not follow [description] /by [time]
     */
    public static void addDeadline(String userInput, Task[] taskList)
            throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            String taskDescription = userInput.split(DEADLINE_IDENTIFIER)[DESCRIPTION].substring(DEADLINE_HEADER);
            String timing = userInput.split(DEADLINE_IDENTIFIER)[TIMING];
            Deadline deadline = new Deadline(taskDescription, timing);
            addToTaskList(taskList, deadline, taskIndex);
            taskIndex++;
            echoUserInput(deadline, taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("String Index OOB. Did you forget the /by? Format: deadline [Description] /by [Time]");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index OOB. Did you forget the /by? Format: deadline [Description /by [Time]");
        }

        System.out.println(LINEBAR);
    }

    public static void showListOfCommands() {
        System.out.println(LINEBAR);
        System.out.println("Unfortunately, my definitely human brain is unable to understand what you mean.\nThe list "
                + "of sentences I understand are: \n todo [Description] \n event [Description] /at [Time] \n "
                + "deadline [Description] /by [Time] \n list \n done [Task Number] \n bye \n bot?");
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
        String userInput;
        String identifier;

        while (true) {
            userInput = readUserInput.nextLine();
            identifier = userInput.split(" ")[FIRST_WORD];

            switch (identifier) {
            case "done":
                completeTask(userInput, taskList);
                continue;

            case "bye":
                bidGoodbye();
                return;

            case "bot?":
                denyBotNature();
                continue;

            case "list":
                listAllTasks(taskList, taskIndex);
                continue;

            case "todo":
                addToDo(userInput, taskList);
                continue;

            case "event":
                addEvent(userInput, taskList);
                continue;

            case "deadline":
                addDeadline(userInput, taskList);
                continue;

            default:
                showListOfCommands();
            }
        }
    }
}