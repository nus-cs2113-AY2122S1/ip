package duke;

import duke.design.Default;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.command.Command;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Duke {
    public static Scanner in = new Scanner(System.in);
    public static int longestTaskDescription = 0; //The length of the longest task description
    public static String STORAGE_MESSAGE = "Welcome to my storage :P, this is how I memorize all your tasks!\n" +
            "Alert! Please do not delete anything inside this file, else I will get memory loss :(\n";
    public static File file = new File("src/main/java/shimaStorage.txt");

    @SuppressWarnings("InfiniteLoopStatement") //Disables the warning for infinite loop
    public static void main(String[] args) {
        //Prints all the welcome screens
        Default.printLogo();
        Default.printWelcomeMessage();
        Default.printVersionDescription();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            readFromStorage(tasks);
        } catch (IOException ex) {
            Default.showMessage("Unfortunately some things have messed up, I have received this information:");
            ex.printStackTrace();
        } catch (DukeException.StorageException ex) {
            Default.showMessage("There is an error occurs when I try to read data from the shimaStorage.txt file, please help me fix it :(");
        }
        System.out.println("\nLet's start:");
        while (true) {
            try {
                readCommand(tasks);
            } catch (DukeException.CommandException ex) {
                Default.showMessage("Sorry, the command is invalid, I cant understand :(");
                System.out.println("\tTo seek for help, you can type the command \"help\" or \"view -h\"");
            } catch (IOException ex) {
                Default.showMessage("Unfortunately some things have messed up, I have received this information:");
                ex.printStackTrace();
            }
        }
    }

    public static void readFromStorage(ArrayList<Task> tasks) throws DukeException.StorageException, IOException {
        try {
            Scanner sc = new Scanner(file);
            int skipTwoLines = 0;
            while (sc.hasNext()) {
                if (skipTwoLines < 2) {
                    sc.nextLine();
                    skipTwoLines++;
                } else {
                    String[] tasksData = sc.nextLine().split("Ø");
                    Task currentTask;
                    switch (tasksData[0]) {
                    case "T":
                        tasks.add(new ToDo(tasksData[2]));
                        currentTask = tasks.get(tasks.size() - 1);
                        longestTaskDescription = Math.max(currentTask.getTask().length(), longestTaskDescription);
                        break;
                    case "D":
                        tasks.add(new Deadline(tasksData[2], tasksData[3]));
                        currentTask = tasks.get(tasks.size() - 1);
                        longestTaskDescription = Math.max(currentTask.getTask().length() + "(at: )".length() + currentTask.getTime().length(), longestTaskDescription);
                        break;
                    case "E":
                        tasks.add(new Event(tasksData[2], tasksData[3]));
                        currentTask = tasks.get(tasks.size() - 1);
                        longestTaskDescription = Math.max(currentTask.getTask().length() + "(at: )".length() + currentTask.getTime().length(), longestTaskDescription);
                        break;
                    default:
                        throw new DukeException.StorageException();
                    }
                    if (tasksData[1].equals("Y")) {
                        tasks.get(tasks.size() - 1).setDone();
                    }
                }
            }
            if (tasks.size() > 0) {
                System.out.println("\n\tHello user! I have helped you written down the to-do list from my previous record!");
                Default.printToDoList(tasks, longestTaskDescription);
            }
        } catch (FileNotFoundException ex) {
            //create a file called shimaStorage.txt
            FileWriter createFile = new FileWriter("src/main/java/shimaStorage.txt");
            createFile.write(STORAGE_MESSAGE);
            createFile.close();
        }
    }

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(ArrayList<Task> tasks) throws DukeException.CommandException, IOException {
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        if (Command.isCommandEmpty(command)) {
            System.out.println("\t(Empty) <- will not save to the list");
        } else if (Command.isCommandViewPersonality(command)) {
            Default.printPersonality();
        } else if (Command.isCommandExit(command)) {
            Default.showMessage("Bye! Hope to see you again :D");
            System.exit(0);
        } else if (Command.isCommandHelp(command)) {
            Default.printHelpMenu();
        } else if (Command.isCommandDelete(command)) {
            deleteTasks(tasks, words);
        } else {
            if (Command.isCommandList(command)) {
                Default.printToDoList(tasks, longestTaskDescription);
            } else if (Command.isCommandDone(words[0])) {
                handleTaskDone(tasks, words);
            } else if (Command.isCommandAddTask(words[0])) {
                addTask(tasks, command, words);
            } else {
                throw new DukeException.CommandException();
            }
        }
    }

    /**
     * Deletes the task(s) according to the task index/indices provided
     *
     * @param tasks The array list that stored all the tasks
     * @param words The array of words that compose the input command
     */
    private static void deleteTasks(ArrayList<Task> tasks, String[] words) {
        switch (words.length) {
        case 1:
            Default.showMessage("Sorry, the input task index to delete is missing!");
            break;
        case 2:
            if (Command.isCommandDeleteAll(words[1])) {
                deleteAllTasks(tasks);
            } else {
                deleteSingleTask(tasks, words[1]);
            }
            break;
        default:
            deleteMultipleTasks(tasks, words);
        }
    }

    /**
     * Deletes all the tasks stored
     *
     * @param tasks The array list that stored all the tasks
     */
    private static void deleteAllTasks(ArrayList<Task> tasks) {
        tasks.clear();
        Default.showMessage("All tasks have been removed! Time to chill?");
    }

    /**
     * Deletes the specific task when there is only one task specified
     *
     * @param tasks The array list that stored all the tasks
     * @param word  The array of words that compose the input command
     */
    private static void deleteSingleTask(ArrayList<Task> tasks, String word) {
        try {
            int index = Integer.parseInt(word);
            if (tasks.get(index - 1).getDone()) {
                Default.showMessage("I have removed this task: [" + tasks.get(index - 1).getClassType() + "][X] " + tasks.get(index - 1));
            } else {
                Default.showMessage("I have removed this task: [" + tasks.get(index - 1).getClassType() + "][ ] " + tasks.get(index - 1));
            }
            tasks.remove(index - 1);
            if (tasks.size() > 0) {
                System.out.println("You have left " + tasks.size() + " tasks to do!");
            } else {
                System.out.println("\tNice! You have finished all tasks! Time to chill~");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            Default.showMessage("Sorry, the input task index to delete is invalid!");
        }
    }

    /**
     * Deletes multiple tasks when there are multiple task indices provided
     *
     * @param tasks The array list that stored all the tasks
     * @param words The array of words that compose the input command
     */
    private static void deleteMultipleTasks(ArrayList<Task> tasks, String[] words) {
        try {
            ArrayList<Integer> taskIndices = new ArrayList<>();
            //Checks if the input task indices are valid
            for (int i = 1; i < words.length; i++) {
                taskIndices.add(Integer.parseInt(words[i]) - 1);
            }
            //Deletes the task with the largest task index first
            taskIndices.sort(Collections.reverseOrder());
            for (Integer i : taskIndices) {
                if (tasks.get(i).getDone()) {
                    Default.showMessage("I have removed this task: [" + tasks.get(i).getClassType() + "][X] " + tasks.get(i));
                } else {
                    Default.showMessage("I have removed this task: [" + tasks.get(i).getClassType() + "][ ] " + tasks.get(i));
                }
                tasks.remove((int) i);
            }
            //Prints the message according to tasks left
            if (tasks.size() > 0) {
                System.out.println("You have left " + tasks.size() + " tasks to do!");
            } else {
                System.out.println("\tNice! You have finished all tasks! Time to chill~");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            Default.showMessage("Sorry, there are some task indices which are invalid, I do not know how to handle :(");
        }
    }

    /**
     * Performs the add task action
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     */
    private static void addTask(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        if (isCorrectToDo(tasks, command, words) || isCorrectDeadline(tasks, command, words) || isCorrectEvent(tasks, command, words)) {
            Default.showMessage(" Class type [" + tasks.get(tasks.size() - 1).getClassType() + "] \"" + tasks.get(tasks.size() - 1) +
                    "\" has been added to the list!" + " (" + tasks.size() + " tasks in total)");
        }
    }

    /**
     * Checks the syntax for the command to create a new task, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        if (!words[0].equalsIgnoreCase("TODO")) {
            return false;
        }
        if (words.length == 1) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        tasks.add(new ToDo(command.replace(words[0], "").trim()));
        if (longestTaskDescription < command.replace(words[0], "").trim().length()) {
            longestTaskDescription = command.replace(words[0], "").trim().length();
        }
        //append mode
        FileWriter fw = new FileWriter(file, true);
        Task currentTask = tasks.get(tasks.size() - 1);
        String taskToSave = currentTask.getClassType() + "Ø" + "N" + "Ø" + currentTask.getTask() + System.lineSeparator();
        System.out.println("\tAdd task " + taskToSave);
        fw.write(taskToSave);
        fw.close();
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        if (!words[0].equalsIgnoreCase("EVENT")) {
            return false;
        }
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        String taskName = command.split("/", 2)[0].trim();
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        if (time.isEmpty()) {
            Default.showMessage("Sorry, the date and period for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        if (!command.contains("-")) {
            Default.showMessage("Sorry, fail to create an Event, the period specific character '-' is missing");
            return false;
        }
        tasks.add(new Event(taskName, time));
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
        }
        FileWriter fw = new FileWriter(file, true);
        Task currentTask = tasks.get(tasks.size() - 1);
        String taskToSave = currentTask.getClassType() + "Ø" + "N" + "Ø" + currentTask.getTask() + "Ø" + currentTask.getTime() + System.lineSeparator();
        fw.write(taskToSave);
        fw.close();
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        if (!words[0].equalsIgnoreCase("DEADLINE")) {
            return false;
        }
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        String taskName = command.split("/", 2)[0].trim();
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        if (time.isEmpty()) {
            Default.showMessage("Sorry, the deadline for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        tasks.add(new Deadline(taskName, time));
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(by: )".length() + time.length();
        }
        FileWriter fw = new FileWriter(file, true);
        Task currentTask = tasks.get(tasks.size() - 1);
        String taskToSave = currentTask.getClassType() + "Ø" + "N" + "Ø" + currentTask.getTask() + "Ø" + currentTask.getTime() + System.lineSeparator();
        fw.write(taskToSave);
        fw.close();
        return true;
    }

    /**
     * Marks the given tasks as done, and handles the possible errors if the input task number is not valid
     *
     * @param tasks The array list that contains all the tasks stored inside the to-do list
     * @param words The array of words that compose the input command
     */
    private static void handleTaskDone(ArrayList<Task> tasks, String[] words) throws IOException {
        try {
            if (words.length == 1) {
                Default.showMessage("Sorry, the input task number is missing, please try again! :(");
            }
            for (int i = 1; i < words.length; i++) {
                //check if the input character after the word "done" is integer value
                int taskIndex = Integer.parseInt(words[i]);
                showTaskDoneMessage(tasks, taskIndex);
            }
            FileWriter fw = new FileWriter(file);
            fw.write(STORAGE_MESSAGE);
            for (Task t : tasks) {
                String taskToSave = "";
                String symbolForDone = (t.getDone()) ? "Y" : "N";
                boolean doNotSave = false;
                if (t instanceof Deadline) {
                    taskToSave = t.getClassType() + "Ø" + symbolForDone + "Ø" + t.getTask() + "Ø" + t.getTime() + System.lineSeparator();
                } else if (t instanceof Event) {
                    taskToSave = t.getClassType() + "Ø" + symbolForDone + "Ø" + t.getTask() + "Ø" + t.getTime() + System.lineSeparator();
                } else if (t instanceof ToDo) {
                    taskToSave = t.getClassType() + "Ø" + symbolForDone + "Ø" + t.getTask() + System.lineSeparator();
                } else {
                    Default.showMessage("An unexpected error occurs when I try to know the type of the task " + t);
                    doNotSave = true;
                }
                if (!doNotSave) {
                    fw.write(taskToSave);
                }
            }
            fw.close();
        } catch (NumberFormatException |
                IndexOutOfBoundsException ex) {
            Default.showMessage("Sorry, the input task number is invalid, please try again! :(");
        } catch (
                IOException ex) {
            Default.showMessage("An unexpected error occurs when I try to upload the file");
            System.out.print("\t");
            ex.printStackTrace();
        }

    }

    /**
     * Shows the message to indicate that the task is marked as done
     *
     * @param tasks      The array list which stores all the tasks
     * @param taskNumber The given task number to mark as done
     */
    private static void showTaskDoneMessage(ArrayList<Task> tasks, int taskNumber) {
        if (!tasks.get(taskNumber - 1).getDone()) {
            tasks.get(taskNumber - 1).setDone();
            System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
            System.out.println("\t[✔] " + tasks.get(taskNumber - 1).getTask());
        } else {
            System.out.println("\tThe task number " + taskNumber + " - \"" + tasks.get(taskNumber - 1).getTask() + "\" has already been done!");
        }
    }
}

