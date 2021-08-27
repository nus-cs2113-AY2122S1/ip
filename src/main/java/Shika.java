import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import shikabot.task.Task;
import shikabot.task.Todo;
import shikabot.task.Deadline;
import shikabot.task.Event;

import shikabot.exception.InvalidCommandException;
import shikabot.exception.TaskNegativeException;
import shikabot.exception.TaskNotFoundException;
import shikabot.exception.InvalidEventException;
import shikabot.exception.InvalidDeadlineException;

public class Shika {

    public static String line = "____________________________________________________________________________\n";
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Main function that calls other functions to run Shika.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        printLogo();
        setupShika();
    }

    /**
     * Function that loads tasks from ShikaTasks.txt. If the file or parent directories do not exist,
     * it creates them.
     * @throws FileNotFoundException when ShikaTasks.txt is not found.
     */
    public static void loadTasks() throws FileNotFoundException {
        File f = new File("data/ShikaTasks.txt");
        boolean isFirstLaunch = setupSave(f);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
        String greeting = (isFirstLaunch) ? "Hello, friend! " : "Welcome back, friend! ";
        System.out.println(greeting + "Shika at your service! ^-^\n");
    }

    /**
     * This function attempts to create the save file at the given path if it does not already exist.
     * @param f File to create.
     * @return true if file is created, false otherwise
     */
    private static boolean setupSave(File f) {
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
            }
            if (f.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during file creation :/");
        } catch (SecurityException e) {
            System.out.println("Shika isn't allowed to write in this location :<");
        }
        return false;
    }

    /**
     * This function adds tasks to taskList by parsing the String inputted.
     * @param s String to be parsed.
     */
    public static void loadTask(String s) {
        int firstDiv = s.indexOf("|") + 1;
        int secondDiv = s.indexOf("|", firstDiv) + 1;
        int thirdDiv = s.indexOf("|", secondDiv) + 1;
        String atBy = s.substring(firstDiv, secondDiv - 1).trim();
        String name = s.substring(secondDiv, thirdDiv - 1).trim();
        String done = s.substring(thirdDiv).trim();
        if (s.startsWith("T")) {
            tasks.add(new Todo(name));
        } else if (s.startsWith("D")) {
            tasks.add(new Deadline(name, atBy));
        } else {
            tasks.add(new Event(name, atBy));
        }
        if (done.equals("true")) {
            tasks.get(Task.count).markAsDone();
        }
        Task.count += 1;
    }

    /**
     * This function saves tasks to data/ShikaTasks.txt. It rewrites the txt from scratch.
     * @throws IOException when the saving operation is interrupted.
     */
    public static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter("data/ShikaTasks.txt");
        fw.close();
        for (int i = 0; i < Task.count; i++) {
            try {
                tasks.get(i).saveTask();
            } catch (IOException e) {
                System.out.println("Problem occurred when saving T.T");
            }
        }
    }

    /**
     * Function that prints Shika logo.
     */
    public static void printLogo() {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";
        System.out.println(logo);
    }

    /**
     * This function initialises the array containing all tasks, tries to load tasks, then runs Shika.
     */
    public static void setupShika() {

        Task.count = 0;
        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find the save file AHHHHHHH.\n");
        }
        runShika(tasks);
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     * @param tasks Arraylist containing all recorded tasks.
     */
    public static void runShika(ArrayList<Task> tasks) {
        Scanner in = new Scanner(System.in);
        String text;
        while(in.hasNextLine()) {
            text = in.nextLine();
            if (text.trim().equals("bye")) {
                System.out.print(line + "> Bye friend!\n> See you again! :)\n" + line);
                return;
            }
            try {
                getCommand(text);
                saveTasks();
            } catch (InvalidCommandException e) {
                System.out.print(line + "> Sorry friend, I don't know what that means. :/\n" + line);
            } catch (IOException e) {
                System.out.println("Problem occurred when saving T.T");
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
     * @param text String containing user input.
     * @throws InvalidCommandException thrown when command is invalid.
     */
    public static void getCommand(String text) throws InvalidCommandException {
        text = text.trim();
        if (text.equals("list")) {
            printTasks();
        } else if (text.startsWith("done")) {
            doTask(text);
        } else if (text.startsWith("delete")) {
            deleteTask(text);
        } else if (isAddCommand(text)) {
            addTask(text);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Function that deletes specified task from the Arraylist.
     * @param text String containing user input.
     */
    public static void deleteTask(String text) {
        String str = text.substring(text.indexOf("delete") + 6).trim();
        try {
            int index = Integer.parseInt(str) - 1;
            System.out.println(line + "> You've removed: " + "\n\t"
                    + (index + 1) + ". " + tasks.get(index).toString());
            tasks.remove(index);
            Task.count -= 1;
            printTaskCount();
            System.out.print(line);
        } catch (NumberFormatException e) {
            System.out.print(line + "> Please key in a number.\n" + line);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(line + "> That task does not exist.\n" + line);
        }
    }

    /**
     * This function attempts to add the task specified by the user to the list and catches exceptions if the input
     * is invalid, printing error messages.
     * @param text String containing user input.
     */
    public static void addTask(String text) {
        if (text.startsWith("todo")) {
            addTodo(text);
        } else if (text.startsWith("deadline")) {
            try {
                addDeadline(text);
            } catch (InvalidDeadlineException e) {
                System.out.print(line + "Please follow the format [NAME] /by [DEADLINE]. " +
                        "Thank you!\n" + line);
            }
        } else {
            try {
                addEvent(text);
            } catch (InvalidEventException e) {
                System.out.print(line + "Please follow the format [NAME] /at [DURATION]. " +
                        "Thank you!\n" + line);
            }
        }
    }

    /**
     * This function adds the todo specified by the user to the list.
     * @param text String containing user input.
     */
    public static void addTodo(String text) {
        String str = text.substring(text.indexOf("todo") + 4).trim();
        tasks.add(new Todo(str));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * This function adds the deadline specified by the user to the list.
     * @param text String containing user input.
     * @throws InvalidDeadlineException is thrown when command syntax is not followed.
     */
    public static void addDeadline(String text) throws InvalidDeadlineException  {
        if (!text.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String str = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        tasks.add(new Deadline(str, by));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * This function adds the event specified by the user to the list.
     * @param text String containing user input.
     * @throws InvalidEventException is thrown when command syntax is not followed.
     */
    public static void addEvent(String text) throws InvalidEventException {
        if (!text.contains("/at")) {
            throw new InvalidEventException();
        }
        String str = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        tasks.add(new Event(str, at));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * Function that attempts to mark a task done by calling markAsDone. It prints error messages if any exceptions
     * are caught from both parseInt and markAsDone.
     * If the String given is not a number or is out of bounds, it will catch the exception and print an error message.
     * @param text String that is supposed to be the number of the task.
     */
    public static void doTask(String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        try {
            int index = Integer.parseInt(str);
            markAsDone(index - 1);
        } catch (NumberFormatException e) {
            System.out.print(line + "> Please key in a number.\n" + line);
        } catch (TaskNegativeException e) {
            System.out.print(line + "> ...Stop trying to break me...\n" + line);
        } catch (TaskNotFoundException e) {
            System.out.print(line + "> Oops! That task does not exist yet!\n" + line);
        }
    }

    /**
     * Function throws exceptions if the index of the task is invalid and marks task as done if it is valid..
     * @param index Index of the task to be marked as done.
     * @throws TaskNegativeException If index is negative.
     * @throws TaskNotFoundException If index is of a task that has not been created yet.
     */
    public static void markAsDone(int index) throws TaskNegativeException, TaskNotFoundException {
        if (index < 0) {
            throw new TaskNegativeException();
        } else if (index >= Task.count) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
        System.out.println(line + "> You've done: " + "\n\t"
                + (index + 1) + ". " + tasks.get(index).toString());
        System.out.print(line);
    }

    /**
     * Function to print all tasks in tasks.
     */
    public static void printTasks() {
        System.out.println(line + "> Here is your list of tasks: ") ;
        for (int i = 0; i < Task.count; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        printTaskCount();
        System.out.print(line);
    }

    /**
     * Function that prints the current task count.
     */
    public static void printTaskCount() {
        String taskForm = (Task.count == 1) ? "task" : "tasks";
        System.out.println("> You have " + Task.count + " " + taskForm + " on your list. -w-") ;
    }
}


