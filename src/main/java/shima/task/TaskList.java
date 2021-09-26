package shima.task;

import shima.command.ListCommand;
import shima.design.UserInterface;
import shima.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The task list class that contains an array list and several methods such as print, delete and add
 */
public class TaskList {
    public static final String INVALID_TASK_INDEX_MSG = "Sorry, the input task number is invalid, please try again! :(";
    public static final String INDEX_OUT_OF_BOUND_MSG = "Sorry, the input task number is out of the bound, please try again! :(";
    public static final String UNEXPECTED_ERROR_MSG = "An unexpected error occurs when I try to update the file";
    public static final String TASK_FINISHED_MSG = "\tNice! You have finished all tasks! Time to chill~";
    public static final String UPDATE_STORAGE_ERROR_MSG = "An unexpected error occurs when I try to update the file";
    public static final String DELETE_ALL_MSG = "All tasks have been removed! Time to chill?";
    public static int longestTaskDescription = 0; //The length of the longest task description
    private ArrayList<Task> tasks;
    private final Storage storage;
    private final UserInterface ui;

    public TaskList(Storage storage, UserInterface ui) {
        tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Getter used to access the array list attribute
     *
     * @return Returns the array list attribute value
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    /**
     * Mimics the toString behavior of the array list
     */
    public String toString() {
        return tasks.toString();
    }

    /**
     * Mimics the behavior of the size() method of the array list
     *
     * @return Returns the size of the array list of the class attribute
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Mimics the behavior get() method of the array list to access element in the array list attribute
     *
     * @param index The index desired to access
     * @return Returns the task element in the array list attribute
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Mimics the behavior of add() method of the array list to add new to-do type element to the array list attribute
     *
     * @param task The new task added
     */
    public void add(ToDo task) {
        tasks.add(task);
    }

    /**
     * Mimics the behavior of add() method of the array list to add new deadline type element to the array list attribute
     *
     * @param task The new task added
     */
    public void add(Deadline task) {
        tasks.add(task);
    }

    /**
     * Mimics the behavior of add() method of the array list to add new event type element to the array list attribute
     *
     * @param task The new task added
     */
    public void add(Event task) {
        tasks.add(task);
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
            String doneIcon = (tasks.get(i).getDone()) ? "[X] " : "[ ] ";
            System.out.print("\t| [" + tasks.get(i).getClassType() + "]" + doneIcon + (i + 1) + ". ");
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
        ui.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Creates an event to the list
     *
     * @param taskName The name of the task
     * @param time     The given event period
     * @throws IOException Throws this exception if there is error occurs during saving data
     */
    public void createEvent(String taskName, String time) throws IOException {
        tasks.add(new Event(taskName, time));
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
        }
        storage.saveTaskToFile(this);
        Task currentTask = tasks.get(tasks.size() - 1);
        ui.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Creates a deadline to the list
     *
     * @param taskName The name of the task
     * @param time     The given deadline
     * @throws IOException Throws this exception if there is error occurs during saving data
     */
    public void createDeadline(String taskName, String time) throws IOException {
        tasks.add(new Deadline(taskName, time));
        Deadline deadline = (Deadline) tasks.get(tasks.size() - 1);
        int deadlineDescriptionLength = deadline.toString().length();
        if (longestTaskDescription < deadlineDescriptionLength) {
            longestTaskDescription = deadlineDescriptionLength;
        }
        storage.saveTaskToFile(this);
        Task currentTask = tasks.get(tasks.size() - 1);
        ui.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Deletes the task(s) according to the task index/indices provided
     *
     * @param words The array of words that compose the input command
     */
    public void deleteTasks(String[] words) {
        switch (words.length) {
        case 1:
            ui.showMessage("Sorry, the input task index to delete is missing!");
            break;
        case 2:
            if (words[1].equalsIgnoreCase(("all"))) {
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
            ui.showMessage(UPDATE_STORAGE_ERROR_MSG);
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
        ui.showMessage(DELETE_ALL_MSG);
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
                String doneIcon = (tasks.get(index - 1).getDone()) ? "[X] " : "[ ] ";
                ui.showMessage("I have removed this task: [" + tasks.get(index - 1).getClassType() + "]" + doneIcon
                        + tasks.get(index - 1));
                tasks.remove(index - 1);
                if (tasks.size() > 0) {
                    System.out.println("You have left " + tasks.size() + " tasks to do!");
                } else {
                    System.out.println(TASK_FINISHED_MSG);
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                ui.showMessage(INDEX_OUT_OF_BOUND_MSG);
            }
        } catch (NumberFormatException numberFormatException) {
            ui.showMessage(INVALID_TASK_INDEX_MSG);
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
                    String doneIcon = (tasks.get(i).getDone()) ? "[X] " : "[ ] ";
                    ui.showMessage("I have removed this task: [" + tasks.get(i).getClassType() + "]" + doneIcon + tasks.get(i));
                    tasks.remove((int) i);
                }
                //Prints the message according to tasks left
                if (tasks.size() > 0) {
                    System.out.println("You have left " + tasks.size() + " tasks to do!");
                } else {
                    System.out.println(TASK_FINISHED_MSG);
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                ui.showMessage(INDEX_OUT_OF_BOUND_MSG);
            }
        } catch (NumberFormatException numberFormatException) {
            ui.showMessage(INVALID_TASK_INDEX_MSG);
        }
    }

    /**
     * Marks the given tasks as done, and handles the possible errors if the input task number is not valid
     *
     * @param tasks The array list that contains all the tasks stored inside the to-do list
     * @param words The array of words that compose the input command
     */
    public void markAsDone(TaskList tasks, String[] words, Storage storage) {
        try {
            for (int i = 1; i < words.length; i++) {
                //check if the input character after the word "done" is integer value
                int taskIndex = Integer.parseInt(words[i]);
                showTaskDoneMessage(tasks, taskIndex);
            }
            storage.updateStorage(tasks);
        } catch (NumberFormatException numberFormatException) {
            ui.showMessage(INVALID_TASK_INDEX_MSG);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            ui.showMessage(INDEX_OUT_OF_BOUND_MSG);
        } catch (IOException ioException) {
            ui.showMessage(UNEXPECTED_ERROR_MSG);
            System.out.print("\t");
            ioException.printStackTrace();
        }
    }

    /**
     * Shows the message to indicate that the task is marked as done
     *
     * @param tasks      The array list which stores all the tasks
     * @param taskNumber The given task number to mark as done
     */
    private static void showTaskDoneMessage(TaskList tasks, int taskNumber) {
        if (!tasks.get(taskNumber - 1).getDone()) {
            tasks.get(taskNumber - 1).setDone();
            System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
            System.out.println("\t[✔] " + tasks.get(taskNumber - 1).getTask());
        } else {
            System.out.println("\tThe task number " + taskNumber + " - \"" + tasks.get(taskNumber - 1).getTask() + "\" has already been done!");
        }
    }
}
