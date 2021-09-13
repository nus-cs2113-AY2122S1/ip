package duke;

import duke.Exceptions.RemoveException;
import duke.Exceptions.TaskException;
import duke.Exceptions.TimeException;
import duke.Exceptions.DoneUndoException;
import duke.tasks.Deadlines;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static final int TIME_COMMAND = 4;
    public static final int DEADLINE_LENGTH = 13;
    public static final int EVENT_LENGTH = 10;
    public static final int TODO_LENGTH = 9;

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        IntroductoryMessage();
        LoadTasks();
        runIkaros();
        SaveTasks();
        goodbyeMessage();
    }

    /**
     * Runs Ikaros, manages the instructions of ikaros
     */
    private static void runIkaros() {
        boolean isRunning = true;

        Scanner in = new Scanner(System.in);
        String response;
        String[] command;

        while (isRunning) {
            response = in.nextLine();
            lineBreak();
            command = response.split(" ", 10);
            switch (command[0]) {
            case "echo":
                echo();
                break;
            case "list":
                printList();
                lineBreak();
                break;
            case "done":
            case "undo":
                doUndoManager(command, response);
                break;
            case "remove":
            case "add":
                instructionManager(command, response);
                break;
            case "bye":
                isRunning = false;
                break;
            case "help":
                help();
                break;
            default:
                System.out.println("I didn't catch that!");
                lineBreak();
                break;
            }
        }
    }

    /**
     * displays the commands usable for the user
     */
    private static void help() {
        System.out.println("Below are the list of acceptable commands"
                + System.lineSeparator() + System.lineSeparator()
                + "add todo -> add a todo task to the list [add todo todo_name]"
                + System.lineSeparator()
                + "add event -> adds an event task to the list [add event event_name " +
                "/at event_date"
                + System.lineSeparator()
                + "add deadline -> adds a deadline task to the list " +
                "[add deadline deadline_name /by deadline_date"
                + System.lineSeparator()
                + "remove -> removes a task [remove task_number]"
                + System.lineSeparator()
                + "done -> marks a task as complete [done task_number]"
                + System.lineSeparator()
                + "undo -> marks a task as undone [undo task_number]"
                + System.lineSeparator()
                + "list -> Display the list of tasks"
                + System.lineSeparator()
                + "echo -> echo mode activated, where system echos your input, " +
                "type [exit] to leave mode"
                + System.lineSeparator()
                + "bye -> kill ikaros");
        lineBreak();
    }

    /**
     * Loads the task list, to Ikaros
     */
    private static void LoadTasks() {
        String[] command;
        File ikarosTaskData = new File("ikarosTaskData.txt");
        Scanner scan;
        try {
            scan = new Scanner(ikarosTaskData);
            if (ikarosTaskData.exists()) {
                while (scan.hasNext()) {
                    command = scan.nextLine().split(">");
                    loadingManager(command);
                }
            } else {
                FileWriter f = new FileWriter(ikarosTaskData.getAbsoluteFile());
                f.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. pls try reload again, or start a new");
        } catch (IOException e) {
            System.out.println("File corrupted. pls try reload again, or start a new");
        } finally {
            printList();
            lineBreak();
        }
    }

    /**
     * Writes down the new tasks to the saved file
     */
    private static void SaveTasks() {
        try {
            FileWriter fw = new FileWriter("ikarosTaskData.txt");
            for (Task task : taskList) {
                fw.write(task.getTaskType() + ">"
                        + task.getStatusIcon() + ">"
                        + task.getDescription() + ">"
                        + task.getDate() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    /**
     * Checks and adds the tasks, from the saved .txt file
     *
     * @param command a String[] containing task information stored in the file
     */
    private static void loadingManager(String[] command) {
        switch (command[0]) {
        case "todo":
            ToDo taskTodo = new ToDo(command[2]);
            taskList.add(taskTodo);
            break;
        case "deadline":
            Deadlines taskDeadline = new Deadlines(command[2], command[3]);
            taskList.add(taskDeadline);
            break;
        case "event":
            Event taskEvent = new Event(command[2], command[3]);
            taskList.add(taskEvent);
            break;
        default:
            System.out.println("file error");
        }
        if (command[1].equalsIgnoreCase("X")) {
            taskList.get(taskList.size() - 1).markAsDone();
        }
    }

    /**
     * Checks to see which command the user has input
     *
     * @param command  the set of instructions the user input in a String array
     * @param response the string of the user input
     */
    private static void instructionManager(String[] command, String response) {
        try {
            if (command[0].equalsIgnoreCase("add")) {
                taskManager(command, response);
            } else {
                remove(command);
            }
        } catch (RemoveException e) {
            System.out.println(e.getMessage());
        } finally {
            lineBreak();
        }
    }

    /**
     * Deciphers the specific task that the user wants to add to the list
     *
     * @param command  the set of instructions the user input in a String array
     * @param response the string of the user input
     */
    private static void taskManager(String[] command, String response) {
        String TaskType = command[1];
        try {
            if ((command.length == 2) || (command[2].isEmpty())) {
                throw new TaskException();
            } else if (command[1].equalsIgnoreCase("event")) {
                Event(response);
            } else if (command[1].equalsIgnoreCase("todo")) {
                toDo(response);
            } else if (command[1].equalsIgnoreCase("deadline")) {
                deadLine(response);
            }
        } catch (TaskException e) {
            System.out.println("please specify " + TaskType + " to add!");
        } catch (TimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deciphers whether the user wants to do or undo a specific task
     *
     * @param command  the set of instructions the user input in a String array
     * @param response the string of the user input
     */
    private static void doUndoManager(String[] command, String response) {
        try {
            if (command[0].equalsIgnoreCase("done")) {
                done(response);
            } else if (command[0].equalsIgnoreCase("undo")) {
                undo(response);
            }
        } catch (DoneUndoException e) {
            System.out.println(e.getMessage());
        } finally {
            lineBreak();
        }
    }

    /**
     * removes a task from the list
     *
     * @param command a String[] containing task information stored in the file
     */
    private static void remove(String[] command) throws RemoveException {
        int i = Integer.parseInt(command[1]);
        if (i > taskList.size()) {
            throw new RemoveException("That task does not exist");
        }
        System.out.println("Understood, I have removed "
                + taskList.get(i - 1).description);
        taskList.remove(i - 1);
        System.out.println("Tasks remaining = " + taskList.size());
    }

    /**
     * Adds an event to the list of Tasks
     *
     * @param response the string of the user input
     */
    private static void Event(String response)
            throws TimeException {
        String timing = " (at: " + response.substring(response.indexOf("/") + TIME_COMMAND)
                + ")";

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /at your_timing]");
        }
        Event event = new Event(response.substring
                (EVENT_LENGTH, response.indexOf("/") - 1), timing);
        addToList(event);

    }

    /**
     * Adds an deadline to the list of Tasks
     *
     * @param response the string of the user input
     */
    private static void deadLine(String response)
            throws TimeException {
        String timing = " (by: " + response.substring(response.indexOf("/") + TIME_COMMAND)
                + ")";

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it due? " +
                    "[indicate by adding: /by your_timing]");
        }
        Deadlines work = new Deadlines(response.substring
                (DEADLINE_LENGTH, response.indexOf("/") - 1),
                timing);
        addToList(work);
    }

    /**
     * Adds a toDo to the list of Tasks
     *
     * @param response the string of the user input
     */
    private static void toDo(String response) {
        ToDo task = new ToDo(response.substring(TODO_LENGTH));
        addToList(task);
    }

    /**
     * marks task as done
     *
     * @param response the string of the user input
     */
    private static void done(String response) throws DoneUndoException {
        int i = Integer.parseInt(response.substring(5)) - 1;
        if (taskList.get(i).isDone) {
            throw new DoneUndoException("Task is already completed");
        }
        taskList.get(i).markAsDone();
        System.out.println("Nice! i have marked this task as done:\n ["
                + taskList.get(i).getStatusIcon() + "] "
                + taskList.get(i).getDescription());
    }

    /**
     * marks task as undone
     *
     * @param response the string of the user input
     */
    private static void undo(String response) throws DoneUndoException {
        int i = Integer.parseInt(response.substring(5)) - 1;
        if (!taskList.get(i).isDone) {
            throw new DoneUndoException("Task is currently incomplete");
        }
        taskList.get(i).undo();
        System.out.println("Ok i have changed the status for the specific task:\n ["
                + taskList.get(i).getStatusIcon() + "] "
                + taskList.get(i).getDescription());
    }

    /**
     * Adds a task to the list
     *
     * @param task task that is being added into the list
     */
    private static void addToList(Task task) {
        taskList.add(task);
        System.out.println("Task added: " + task);
        System.out.println("Total no. of Tasks = " + (taskList.size()));
    }

    /**
     * Below goes into a mode in ikaros, where user input is echoed
     */
    public static void echo() {
        System.out.println("Life is a mirror and will reflect back to "
                + "the thinker what\nhe thinks into it, echoing commencing");
        lineBreak();
        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        lineBreak();
        while (!response.equals("exit")) {
            System.out.println(response);
            lineBreak();
            response = in.nextLine();
            lineBreak();
        }
    }

    /**
     * prints the list of tasks
     */
    public static void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(i + ".[" + task.getTaskID() + "]" +
                    "[" + task.getStatusIcon() +
                    "] " + task.description + task.getDate());
            i++;
        }
    }

    /**
     * line separator
     */
    private static void lineBreak() {
        String lineBreak = "..........................." +
                ".......................................";
        System.out.println(lineBreak);
    }

    /**
     * Greet user
     */
    private static void IntroductoryMessage() {
        String logo = "  /\\ _ /\\\n"
                + " #  @ @  #    Welcome to IKAROS!\n"
                + " #   ^   #  Your one and only butler\n"
                + " #########";
        lineBreak();

        System.out.println(logo);
        lineBreak();
        System.out.println("Below is your current list of tasks."
                + System.lineSeparator()
                + "What further assistance do you require?");
        lineBreak();
    }

    /**
     * Bid Farewell to the user
     */
    private static void goodbyeMessage() {
        System.out.println("GoodBye, Ikaros awaits for future commands");
        lineBreak();
    }
}