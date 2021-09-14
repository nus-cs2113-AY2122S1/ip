package duke;

import exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Task type Array list to store the tasks the user will create
     */
    private static ArrayList<Task> scheduledTasks = new ArrayList<>();

    private static final String FILE_PATH = "duke.txt";
    public static final String TASK_COMPLETED = "1";
    public static final String TASK_INCOMPLETE = "0";

    /**
     * This is the main function responsible for the execution of this program
     */
    public static void main(String[] args) {
        loadData();
        greet();
        runDuke();
        greetBye();
    }

    /**
     * Greets the user by printing some introductory messages
     */
    private static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints a line on the screen
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    public static void loadData() {
        try {
            loadPreviousData();
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            try {
                file.createNewFile();
            } catch (IOException ee) {
                System.out.println("Cannot create a new file");
            }
        }
    }

    public static void loadPreviousData() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadSavedTasksToList(sc.nextLine());
        }
    }

    public static void saveTaskInDisk() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        String lineToWrite = "";
        for (Task task : scheduledTasks) {
            lineToWrite = "";
            if (task.taskType == TaskType.TODO) {
                lineToWrite = "T => ";
            } else if (task.taskType == TaskType.DEADLINE) {
                lineToWrite = "D => ";
            } else {
                lineToWrite = "E => ";
            }

            String taskStatus = task.getStatus();

            if (taskStatus.equals("X")) {
                lineToWrite = lineToWrite + TASK_COMPLETED;
            } else {
                lineToWrite = lineToWrite + TASK_INCOMPLETE;
            }

            String taskDescription = task.description;

            lineToWrite = lineToWrite + " => " + taskDescription;
            if (task instanceof Deadline) {
                lineToWrite = lineToWrite + " => " + ((Deadline) task).by;
            } else if (task instanceof Event) {
                lineToWrite = lineToWrite + " => " + ((Event) task).at;
            }

            lineToWrite += "\n";
            fw.write(lineToWrite);

        }
        fw.close();
    }

    public static void saveTaskToList() {
        try {
            saveTaskInDisk();
        } catch (IOException e) {
            System.out.println("Unable to save data to the disk");
        }
    }


    public static void loadSavedTasksToList(String input) {
        String[] splitInput = input.split("=>");
        String taskType = splitInput[0].trim();
        String taskStatus = splitInput[1].trim();
        String taskDescription = splitInput[2].trim();

        switch (taskType) {
        case "T":
            scheduledTasks.add(new Todo(taskDescription));
            break;
        case "D":
            String timeDueBy = splitInput[3];
            scheduledTasks.add(new Deadline(taskDescription, timeDueBy));
            break;
        case "E":
            String timeDueAt = splitInput[3];
            scheduledTasks.add(new Event(taskDescription, timeDueAt));
            break;
        default:
        }

        if (taskStatus.equals(TASK_COMPLETED)) {
            scheduledTasks.get(scheduledTasks.size() - 1).markAsDone();
        }

    }

    /**
     * Takes in input from the user and executes the given instructions.
     * If user input is "list", it calls the list() function to list all the tasks.
     * If user input is done x, where x is a valid task number, it calls the MarkTaskAsDone() function to mark the task as done.
     * If user has scheduled a task by writing a statement beginning with "event", "deadline" or "todo",
     * then it adds the task in the tasks list by calling the addTaskToList() function.
     *
     * @param userInput            userInput stores the input String entered by the user.
     * @param taskCompletionStatus taskCompletionStatus Stores the status of the task, true if completed, false otherwise.
     */
    private static void runDuke() {
        int i;
        String userInput;
        String taskCompletionStatus = "";
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!(userInput.equalsIgnoreCase("bye"))) {
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    list();
                } else if (userInput.startsWith("done")) {
                    markTaskAsDone(userInput);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    addTaskToList(userInput);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException ee) {
                System.out.println("Could not save data to file");
            }
            printLine();
            userInput = in.nextLine();
        }

    }

    /**
     * Greets the user goodbye and the code finishes its execution.
     */
    private static void greetBye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Adds the task to the task list if it is a valid task creation statement given by the user.
     * Displays appropriate message if task is valid and is created successfully, vice versa.
     * Displays the total number of tasks in the list as well.
     *
     * @param isTaskValid IsTaskValid stores true if the task statement entered by the user is a valid task creation statement and false, otherwise.
     * @param index       Index stores the index of the "/" in the entered String
     */
    private static void addTaskToList(String userInput) throws DukeException {

        int index;
        boolean isTaskValid = true;
        String firstWord;
        String[] split = userInput.split(" ", 2);
        String taskDescription;
        String timeDueAt;
        String timeDueBy;
        firstWord = split[0].toLowerCase();

        switch (firstWord) {
        case "todo":
            if (split.length < 2 || split[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                scheduledTasks.add(new Todo(split[1]));
            }
            saveTaskToList();
            break;

        case "deadline":
            index = userInput.indexOf("/");
            if (split.length < 2 || split[1].isEmpty() == true || index == -1) {
                throw new DukeException("☹ OOPS!!! The description or the deadline of the task cannot be empty.");
            }
            int indexOfSpace = split[1].indexOf(" ");
            taskDescription = split[1].split("/by", 2)[0];
            timeDueBy = split[1].split("/by", 2)[1];
            if (taskDescription.isEmpty() || timeDueBy.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of the task seems incomplete.");
            }
            scheduledTasks.add(new Deadline(userInput.substring(indexOfSpace, index), userInput.substring(index + 3)));
            saveTaskToList();
            break;

        case "event":
            index = userInput.indexOf("/");
            if (split.length < 2 || split[1].isEmpty() || index == -1) {
                throw new DukeException("☹ OOPS!!! The description and time schedule of the event cannot be empty.");
            }
            indexOfSpace = split[1].indexOf(" ");
            taskDescription = split[1].split("/at", 2)[0];
            timeDueAt = split[1].split("/at", 2)[1];
            if (taskDescription.isEmpty() || timeDueAt.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description or time schedule of the event seems incomplete.");
            }
            scheduledTasks.add(new Event(userInput.substring(indexOfSpace, index), userInput.substring(index + 3)));
            saveTaskToList();
            break;

        default:
            isTaskValid = false;
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (isTaskValid) {
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + scheduledTasks.get(scheduledTasks.size() - 1));
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
        }
    }


    /**
     * Updates the status of the task by marking it as done in the task list.
     *
     * @param taskNumberCompleted TaskNumberCompleted stores the task number which has been completed by the user.
     */
    private static void markTaskAsDone(String userInput) throws DukeException {
        printLine();
        int taskNumberCompleted = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((taskNumberCompleted <= scheduledTasks.size()) && (taskNumberCompleted > 0)) {
            scheduledTasks.get(taskNumberCompleted - 1).markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(scheduledTasks.get(taskNumberCompleted - 1));
            saveTaskToList();
        } else {
            throw new DukeException("Sorry, no task is assigned at this number, you might want to re-check?");
        }
    }


    /**
     * Deletes the task from the task list.
     *
     * @param deleteTask DeleteTask stores the task number which is supposed to be deleted.
     */
    private static void deleteTask(String userInput) throws DukeException, IOException {
        printLine();
        int deleteTask = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((deleteTask <= scheduledTasks.size()) && (deleteTask > 0)) {
            Task taskToBeDeleted = scheduledTasks.get(deleteTask - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToBeDeleted);
            scheduledTasks.remove(deleteTask - 1);
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
            saveTaskInDisk();
        } else {
            throw new DukeException("Sorry, no task is assigned at this number, you might want to re-check?");
        }
    }


    /**
     * Lists all the tasks in the task list along with their task completion status.
     * The tasks are enlisted which reveal if they are a "todo", "event" or a "deadline".
     *
     * @param taskCompletionStatus TaskCompletionStatus stores true if the task is completed, false otherwise.
     */
    private static void list() throws DukeException {
        int i;
        String taskCompletionStatus;
        printLine();
        if (scheduledTasks.size() == 0) {
            throw new DukeException("Sorry, no tasks have been added to the list as yet!\n" +
                    "You can add tasks to this list simply by typing and pressing \"Enter\"!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            i = 0;
            for (Task task : scheduledTasks) {
                taskCompletionStatus = task.getStatus();
                System.out.print((i + 1) + ".");
                System.out.println(task);
                i++;
            }
        }
    }
}

