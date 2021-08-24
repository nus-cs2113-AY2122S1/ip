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
        System.out.println(logo + "\nHello, friend! Shika at your service! (`･ω･´)ゞ\n");
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    public static void runShika() {
        Task[] taskList = new Task[100];
        Task.count = 0;
        Scanner in = new Scanner(System.in);
        String text;
        while(in.hasNextLine()) {
            text = in.nextLine();
            if (text.trim().equals("bye")) {
                System.out.println(line + "> Bye friend!\n> See you again! (*・ω・)ﾉ\n" + line);
                return;
            }
            try {
                getCommand(taskList, text);
            } catch (InvalidCommandException e) {
                System.out.println(line + "> Sorry friend, I don't know what that means. (´･ω･`)?\n" + line);
            }
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
     * "todo", "deadline" or "event" will attempt to add the task.
     * Any other string will print an error message.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidCommandException thrown when command is invalid.
     */
    public static void getCommand(Task[] taskList, String text) throws InvalidCommandException {
        text = text.trim();
        if (text.equals("list")) {
            printTasks(taskList);
        } else if (text.startsWith("done")) {
            doTask(taskList, text);
        } else if (isAddCommand(text)) {
            addTask(taskList, text);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * This function attempts to add the task specified by the user to the list and catches exceptions if the input
     * is invalid, printing error messages.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addTask(Task[] taskList, String text) {
        if (text.startsWith("todo")) {
            addTodo(taskList, text);
        } else if (text.startsWith("deadline")) {
            try {
                addDeadline(taskList, text);
            } catch (InvalidDeadlineException e) {
                System.out.print(line + "Please follow the format [NAME] /by [DEADLINE]. " +
                        "Thank you! (。・ω・。)\n" + line);
            }
        } else {
            try {
                addEvent(taskList, text);
            } catch (InvalidEventException e) {
                System.out.print(line + "Please follow the format [NAME] /at [DURATION]. " +
                        "Thank you! (。・ω・。)\n" + line);
            }
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
        System.out.println(line + "> Added: (∩•̀ω•́)⊃-*⋆ " + "\n\t"
                + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * This function adds the deadline specified by the user to the list.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidDeadlineException is thrown when command syntax is not followed.
     */
    public static void addDeadline(Task[] taskList, String text) throws InvalidDeadlineException  {
        if (!text.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String str = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        taskList[Task.count] = new Deadline(str, by);
        Task.count += 1;
        System.out.println(line + "> Added: (∩•̀ω•́)⊃-*⋆" + "\n\t"
                + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * This function adds the event specified by the user to the list.
     * @param taskList Array containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidEventException is thrown when command syntax is not followed.
     */
    public static void addEvent(Task[] taskList, String text) throws InvalidEventException {
        if (!text.contains("/at")) {
            throw new InvalidEventException();
        }
        String str = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        taskList[Task.count] = new Event(str, at);
        Task.count += 1;
        System.out.println(line + "> Added: (∩•̀ω•́)⊃-*⋆" + "\n\t"
                + Task.count + ". " + taskList[Task.count - 1].toString());
    }

    /**
     * Function that attempts to mark a task done by calling markAsDone. It prints error messages if any exceptions
     * are caught from both parseInt and markAsDone.
     * If the String given is not a number or is out of bounds, it will catch the exception and print an error message.
     * @param taskList Array containing all recorded tasks.
     * @param text String that is supposed to be the number of the task.
     */
    public static void doTask(Task[] taskList, String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        try {
            int index = Integer.parseInt(str);
            markAsDone(taskList, index - 1);
        } catch (NumberFormatException e) {
            System.out.print(line + "> Please key in a number. (´･ω･`)?\n" + line);
        } catch (TaskNegativeException e) {
            System.out.print(line + "> ...Stop trying to break me... ( ; ω ; )\n" + line);
        } catch (TaskNotFoundException e) {
            System.out.print(line + "> Oopsie! That task does not exist... yet! (。・ω・。)\n" + line);
        }
    }

    /**
     * Function throws exceptions if the index of the task is invalid and marks task as done if it is valid.
     * @param taskList Array containing all recorded tasks.
     * @param index Index of the task to be marked as done.
     * @throws TaskNegativeException If index is negative.
     * @throws TaskNotFoundException If index is of a task that has not been created yet.
     */
    public static void markAsDone(Task[] taskList, int index) throws TaskNegativeException, TaskNotFoundException {
        if (index < 0) {
            throw new TaskNegativeException();
        } else if (index >= Task.count) {
            throw new TaskNotFoundException();
        }
        taskList[index].markAsDone();
    }

    /**
     * Function to print all tasks in taskList.
     * @param taskList Array containing all recorded tasks.
     */
    public static void printTasks(Task[] taskList) {
        System.out.println(line + "> Here is your list of tasks: (∩•̀ω•́)⊃-*⋆") ;
        for (int i = 0; i < Task.count; i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList[i].toString());
        }
        System.out.print(line);
    }
}


