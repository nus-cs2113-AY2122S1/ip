import java.util.Scanner;

public class Shika {

    public static String line = "____________________________________________________________________________\n";

    /**
     * Main function that calls other functions to run Shika.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        greetUser();
        runShika();
    }

    /**
     * Function that prints Shika logo and a greeting message.
     */
    public static void greetUser() {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";
        System.out.println(logo + "\nHello, friend! Shika at your service!\n");
    }

    /**
     * Function that calls scan() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    public static void runShika() {
        Task[] taskList = new Task[100];
        Task.count = 0;
        Scanner in = new Scanner(System.in);
        String text;
        while(in.hasNextLine()) {
            text = in.nextLine();
            if (text.trim().equals("bye")) {
                System.out.println(line + "> Bye friend!\n> See you again! :3\n" + line);
                return;
            }
            scan(taskList, text);
        }
    }

    /**
     * Function that input is an integer. It checks if value can be parsed by parseInt() without throwing an exception.
     * @param input String to be parsed.
     * @return Returns false if parseInt() throws an exception, returns true otherwise.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * Function that checks if the string is an add task command.
     * @param text String containing user input.
     * @return true if string starts with "todo", "deadline" or "event" and false otherwise.
     */
    public static boolean isAddCommand(String text) {
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    /**
     * Function that waits for user input, then executes user command.
     * "list" will list all current tasks.
     * "done x" will mark task x as done, where x is the number of the task.
     * "todo", "deadline" or "event" will add the task.
     * Any other string will print an error message.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void scan(Task[] taskList, String text) {
        text = text.trim();
        if (text.equals("list")) {
            printTasks(taskList);
        } else if (text.startsWith("done")) {
            doTask(taskList, text);
        } else if (isAddCommand(text)) {
            addTask(taskList, text);
        } else {
            System.out.print(line + "Please enter a valid command.\n" + line);
        }
    }

    /**
     * This function adds the task specified by the user to the list. It checks for the category of the task
     * as specified by the user and executes accordingly.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addTask(Task[] taskList, String text) {
        if (text.startsWith("todo")) {
            addTodo(taskList, text);
        } else if (text.startsWith("deadline")) {
            addDeadline(taskList, text);
        } else {
            addEvent(taskList, text);
        }
    }

    /**
     * This function adds the todo specified by the user to the list.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addTodo(Task[] taskList, String text) {
        String str = text.substring(text.indexOf("todo") + 4).trim();
        taskList[Task.count] = new Todo(str);
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t" + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * This function adds the deadline specified by the user to the list.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addDeadline(Task[] taskList, String text) {
        if (!text.contains("/by")) {
            System.out.print(line + "Please follow the format [NAME] /by [DEADLINE]. Thank you!\n" + line);
            return;
        }
        String str = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        taskList[Task.count] = new Deadline(str, by);
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t" + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * This function adds the event specified by the user to the list.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addEvent(Task[] taskList, String text) {
        if (!text.contains("/at")) {
            System.out.print(line + "Please follow the format [NAME] /at [DURATION]. Thank you!\n" + line);
            return;
        }
        String str = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        taskList[Task.count] = new Event(str, at);
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t" + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * Function that marks the task with the input number as done.
     * If the String given is not a number or is out of bounds, it will print an error message.
     * @param taskList Array containing all recorded tasks.
     * @param text String that is supposed to be the number of the task.
     */
    public static void doTask(Task[] taskList, String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        if (!isInteger(str)) {
            System.out.print(line + "> Please key in a number :/\n" + line);
            return;
        }
        int index = Integer.parseInt(str);
        if (index > Task.count) {
            System.out.print(line + "> Oopsie! That task does not exist... yet!\n" + line);
        } else if (index < 1) {
            System.out.print(line + "> ...Stop trying to break me :<\n" + line);
        } else {
            taskList[index - 1].markAsDone();
            System.out.print(line + "> Otsukare! You've done:"
                    + "\n\t" + index + ". " + taskList[index - 1].toString() + "\n" + line);
        }
    }

    /**
     * Function to print all tasks in taskList.
     * @param taskList Array containing all recorded tasks.
     */
    public static void printTasks(Task[] taskList) {
        System.out.println(line + "> Here is your list of tasks:") ;
        for (int i = 0; i < Task.count; i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList[i].toString());
        }
        System.out.print(line);
    }
}


