package shima.task;

import shima.command.CommandLibrary;
import shima.command.ListCommand;
import shima.design.Default;
import shima.parser.Parser;
import shima.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    public static int longestTaskDescription = 0; //The length of the longest task description
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(ToDo task) {
        tasks.add(task);
    }

    public void add(Deadline task) {
        tasks.add(task);
    }

    public void add(Event task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void clear() {
        tasks.clear();
    }

    /**
     * Prints the to-do list with frames
     */
    public void printToDoList() {
        final int MIN_LENGTH = " My to-do list: ".length();
        //if longestTaskDescription is shorter than the length of the string "My to-do list: ", sets it to the length of the string
        if (longestTaskDescription < MIN_LENGTH) {
            longestTaskDescription = MIN_LENGTH;
        }
        //Prints the to-do list
        drawUpperFrame();
        printTasks();
        drawLowerFrame();
    }

    /**
     * Prints the upper frame of the to-do list and its default display string
     */
    private void drawUpperFrame() {
        System.out.print("\t" + ListCommand.TOP_LEFT_CORNER); //the top left corner
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(ListCommand.TOP_RIGHT_CORNER);
        System.out.print("\t| My to-do list: ");
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length() - "| My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    /**
     * Prints the tasks stored in the array, the frame starts with '|' and ends with '|', the ending frame is always located at the position of the longest task description
     */
    private void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            //Fill the first [] with class type, and the second [] with a 'X' if the task is completed
            if (tasks.get(i).getDone()) {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][X] " + (i + 1) + ". ");
            } else {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][ ] " + (i + 1) + ". ");
            }
            //Calculates the required spacing for the current task as compared to the longest task description to print '|'
            int distanceToClosingFrame = longestTaskDescription + "| [ ][ ] 100. ".length() - ("| [ ][ ] " + (i + 1) + ". ").length() + 1;
            System.out.printf("%1$-" + distanceToClosingFrame + "s", tasks.get(i));
            System.out.println("|");
        }
    }

    /**
     * Prints the bottom frame of the to-do list and the guide for reading the to-do list
     */
    private void drawLowerFrame() {
        System.out.print("\t" + ListCommand.BOTTOM_LEFT_CORNER);
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(ListCommand.BOTTOM_RIGHT_CORNER);
        //Shows the guide for understanding the to-do list
        System.out.println("\tFor your knowledge, ");
        System.out.println("\tthe first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)");
        System.out.println("\tthe second [ ] indicates whether the task is completed:\n" +
                "\t[X] when the task is marked completed\t[ ] when the task is not done.");
    }

    /**
     * Adds a to-do to the list if there is no error
     *
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public void createToDo(String command, String[] words) throws IOException {
        tasks.add(new ToDo(command.replace(words[0], "").trim()));
        if (longestTaskDescription < command.replace(words[0], "").trim().length()) {
            longestTaskDescription = command.replace(words[0], "").trim().length();
        }
        storage.saveTaskToFile(this);
        Task currentTask = tasks.get(tasks.size() - 1);
        Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Adds an event to the list if there is no error
     *
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public void createEvent(String command, String[] words, String taskName, String time) throws IOException {
        tasks.add(new Event(taskName, time));
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
        }
        storage.saveTaskToFile(this);
        Task currentTask = tasks.get(tasks.size() - 1);
        Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Adds a deadline to the list if there is no error
     *
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public void createDeadline(String command, String[] words, String taskName, String time) throws IOException {
        tasks.add(new Deadline(taskName, time));
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(by: )".length() + time.length();
        }
        storage.saveTaskToFile(this);
        Task currentTask = tasks.get(tasks.size() - 1);
        Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Create task according to its type to the array list
     * @param command The input command entered by the user
     * @param words The array of words that compose the command
     * @param storage The storage class which stores all the data
     * @throws IOException
     */
    public void addTask(String command, String[] words, Storage storage) throws IOException {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        switch (words[0].toLowerCase()) {
        case "todo":
            createToDo(command, words);
            break;
        case "deadline":
            createDeadline(command, words, taskName, time);
            break;
        case "event":
            createEvent(command, words, taskName, time);
            break;
        default:
            Default.showMessage("An unexpected error has occured when I try to add task :(");
        }
    }

    /**
     * Deletes the task(s) according to the task index/indices provided
     *
     * @param words The array of words that compose the input command
     */
    public void deleteTasks(String[] words) {
        switch (words.length) {
        case 1:
            Default.showMessage("Sorry, the input task index to delete is missing!");
            break;
        case 2:
            if (CommandLibrary.isCommandDeleteAll(words[1])) {
                deleteAllTasks(tasks);
            } else {
                deleteSingleTask(tasks, words[1]);
            }
            break;
        default:
            deleteMultipleTasks(tasks, words);
        }
        try {
            storage.updateStorage(this);
        } catch (IOException ioException) {
            Default.showMessage("An unexpected error occurs when I try to update the file");
            System.out.print("\t");
            ioException.printStackTrace();
        }
    }

    /**
     * Deletes all the tasks stored
     *
     * @param tasks The array list that stored all the tasks
     */
    private void deleteAllTasks(ArrayList<Task> tasks) {
        tasks.clear();
        Default.showMessage("All tasks have been removed! Time to chill?");
    }

    /**
     * Deletes the specific task when there is only one task specified
     *
     * @param tasks The array list that stored all the tasks
     * @param word  The array of words that compose the input command
     */
    private void deleteSingleTask(ArrayList<Task> tasks, String word) {
        int index;
        try {
            //Compiler throws NumberFormatException if word is not a digit character
            index = Integer.parseInt(word);
            try {
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
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                Default.showMessage("Sorry, the input task index to delete is invalid!");
            }
        } catch (NumberFormatException numberFormatException) {
            Default.showMessage("Sorry, the input task index to delete is invalid!");
        }
    }

    /**
     * Deletes multiple tasks when there are multiple task indices provided
     *
     * @param tasks The array list that stored all the tasks
     * @param words The array of words that compose the input command
     */
    private void deleteMultipleTasks(ArrayList<Task> tasks, String[] words) {
        try {
            ArrayList<Integer> taskIndices = new ArrayList<>();
            //Checks if the input task indices are valid
            for (int i = 1; i < words.length; i++) {
                taskIndices.add(Integer.parseInt(words[i]) - 1); //throws NumberFormatException if input is not digit character
            }
            try {
                //Deletes the task with the largest task index first
                taskIndices.sort(Collections.reverseOrder());
                for (Integer i : taskIndices) {
                    String doneSymbol = (tasks.get(i).getDone()) ? "X" : " ";
                    Default.showMessage("I have removed this task: [" + tasks.get(i).getClassType() + "][" + doneSymbol + "] " + tasks.get(i));
                    tasks.remove((int) i);
                }
                //Prints the message according to tasks left
                if (tasks.size() > 0) {
                    System.out.println("You have left " + tasks.size() + " tasks to do!");
                } else {
                    System.out.println("\tNice! You have finished all tasks! Time to chill~");
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                Default.showMessage("Sorry, there are task indices which are not in 1 to " + tasks.size() + ", I do not know how to handle :(");
            }
        } catch (NumberFormatException numberFormatException) {
            Default.showMessage("Sorry, there are some task indices which are invalid, I do not know how to handle :(");
        }
    }
}
