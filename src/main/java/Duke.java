import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IncorrectCommandInput {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> savedTasks = new LinkedList<Task>();
        TaskList taskList = new TaskList(savedTasks);
        printWelcomeMessage();
        String userInput = "";
        String commandInput = "";
        String checkedCommandInput = "";
        try {
            userInput = sc.nextLine();
            commandInput = identifyUserInput(userInput)[0];
            checkedCommandInput = commandInputError(commandInput);
        } catch (IncorrectCommandInput e){
            System.out.println("Invalid Command!");
        }
        while(!userInput.contains("bye")){
            switch(checkedCommandInput) {
            case "todo":
                try {
                    addToDoTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "deadline":
                try {
                    addDeadlineTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please include your description!");
                }
                break;

            case "event":
                try {
                    addEventTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "list":
                listTask(taskList);
                break;

            case "done":
                markTaskAsDone(taskList, userInput);
                break;
            default:
                System.out.println("Invalid! Please try again.");
            }
            try {
                userInput = sc.nextLine();
                commandInput = identifyUserInput(userInput)[0];
                checkedCommandInput = commandInputError(commandInput);
            }catch (IncorrectCommandInput e){
                System.out.println("Invalid Command! Please try again");
            }
        }
        printGoodbyeMessage();
    }

    /**
     * Adds a new todo task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addToDoTask(TaskList taskList, String userInput) {
        ToDo toDoTask = new ToDo(userInput.substring(userInput.indexOf(' ',0))
                ,false);
        taskList.addTasks(toDoTask);
        toDoTask.initialiseToDo();
    }

    /**
     * Adds a new event task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addEventTask(TaskList taskList, String userInput) {
        Events eventTask = new Events(
                userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/')),
                false,identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        eventTask.initialiseEvent();
    }

    /**
     * Adds a new deadline task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTask(TaskList taskList, String userInput) {
        Deadline deadLineTask = new Deadline(
                userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/'))
                ,false,identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        deadLineTask.initialiseDeadline();
    }

    /**
     * Locates the task in the list and take note that it is completed.
     * @param taskList
     * @param userInput
     */
    private static void markTaskAsDone(TaskList taskList, String userInput) {
        int taskListIndex;
        Task currentTask;
        try{
            taskListIndex = Integer.parseInt(identifyUserInput(userInput)[1]);
            currentTask = taskList.findTask(taskListIndex);
            currentTask.markTaskAsDone();}
        catch(NumberFormatException e){
            System.out.println("Please choose a viable task");
        }
    }

    /**
     * Prints out the goodbye message.
     */
    private static void printGoodbyeMessage() {
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(goodbyeMessage);
    }

    /**
     * Prints out the welcome message.
     */
    private static void printWelcomeMessage() {
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What are you doing today?\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);
    }

    /**
     * Prints out all the tasks in the list.
     * @param taskList
     */
    private static void listTask(TaskList taskList) {
        System.out.println("______________________________\n");
        taskList.listTasks();
        System.out.println("______________________________\n");
    }

    /**
     * Prints the current number of task in the lists.
     * @param taskList
     */
    private static void printTaskList(TaskList taskList) {
        System.out.println("Now you have " + taskList.countTaskInList()
                + " tasks in the list");
        System.out.println("______________________________\n");
    }

    /**
     * Takes the user input and identify the index which,
     * the user wants to mark as completed.
     *
     * @param userInput
     * @return taskIndex.
     */
    public static String[] identifyUserInput(String userInput){
        String[] parts = userInput.split(" ");
        return parts;
    }

    /**
     * Takes the user input and identify the dateline which,
     * the user wants the task to be.
     *
     * @param userInput
     * @return String array of string split at "/".
     */
    public static String[] identifyDeadlineCommand(String userInput){
        String[] parts = userInput.split("/");
        return parts;
    }

    /**
     * Used to check if the input command is valid or not.
     * @param userInput
     * @return
     * @throws IncorrectCommandInput if input is not valid.
     */
    public static String commandInputError(String userInput) throws IncorrectCommandInput {
        switch(userInput) {
        case "todo":
        case "event":
        case "list":
        case "bye":
        case "done":
            return userInput;
        default:
            throw new IncorrectCommandInput();
        }
    }
}

