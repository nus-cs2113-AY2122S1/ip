import java.util.Scanner;

public class Duke {
    public static final int MAX_TASKS = 100;
    public static final String TOP_LEFT_CORNER = "/";
    public static final String TOP_RIGHT_CORNER = "\\";
    public static final String BOTTOM_LEFT_CORNER = "\\";
    public static final String BOTTOM_RIGHT_CORNER = "/";
    public static Scanner in = new Scanner(System.in);
    public static int longestTaskDescription = 0;

    @SuppressWarnings("InfiniteLoopStatement") //disable the warning for infinite loop
    public static void main(String[] args) {
        //Prints all the welcome screens
        Design.printLogo();
        Design.printWelcomeMessage();
        Design.printVersionDescription();
        System.out.println("\nLet's start:");
        Task[] tasks = new Task[MAX_TASKS];
        while (true) {
            try{
                readCommand(tasks);
            }
            catch (DukeException ex){
                showMessage("Sorry, the command is invalid, I cant understand :(");
                System.out.println("\tTo seek for help, you can type the command \"help\" or \"view -h\"");
            }
        }
    }

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(Task[] tasks) throws DukeException {
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        if (isCommandEmpty(command)) {
            System.out.println("\t(Empty) <- will not save to the list");
        } else if (isCommandViewPersonality(command)) {
            Design.printPersonality();
        } else if (isCommandExit(command)) {
            showMessage("Bye! Hope to see you again :D");
            System.exit(0);
        } else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("view -h")){
            Design.printHelpMenu();
        } else {
            if (isCommandList(command)) {
                printToDoList(tasks, Task.totalTask, longestTaskDescription);
            } else if (isCommandDone(words[0])) {
                handleTaskDone(tasks, words);
            } else if (isCommandAddTask(words[0])) {
                addTask(tasks, command, words);
            } else {
                throw new DukeException();
            }
        }
    }

    /**
     * Checks if the command is meant to add task to the to-do list
     *
     * @param word The array of words that compose the input command
     * @return Returns true if the command contains keywords "deadline", "event" or "todo"
     */
    private static boolean isCommandAddTask(String word) {
        return word.equalsIgnoreCase("DEADLINE") || word.equalsIgnoreCase("EVENT") || word.equalsIgnoreCase("TODO");
    }

    /**
     * Performs the add task action
     *
     * @param tasks   The array that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     */
    private static void addTask(Task[] tasks, String command, String[] words) {
        if (isCorrectToDo(tasks, command, words) || isCorrectDeadline(tasks, command, words) || isCorrectEvent(tasks, command, words)) {
            showMessage(" Class type [" + tasks[Task.totalTask].getClassType() + "] \"" + tasks[Task.totalTask] + "\" has been added to the list!"
                    + " (" + (Task.totalTask + 1) + " tasks in total)");
            Task.totalTask++;
        }
    }

    /**
     * Checks the syntax for the command to create a new task, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(Task[] tasks, String command, String[] words) {
        if (!words[0].equalsIgnoreCase("TODO")) {
            return false;
        }
        if (words.length == 1) {
            showMessage("Sorry, the task is empty!");
            return false;
        }
        tasks[Task.totalTask] = new ToDo(command.replace(words[0], "").trim());
        if (longestTaskDescription < command.replace(words[0], "").trim().length()) {
            longestTaskDescription = command.replace(words[0], "").trim().length();
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(Task[] tasks, String command, String[] words) {
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
            showMessage("Sorry, the task is empty!");
            return false;
        }
        if (time.isEmpty()) {
            showMessage("Sorry, the date and period for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        if (!command.contains("-")) {
            showMessage("Sorry, fail to create an Event, the period specific character '-' is missing");
            return false;
        }
        tasks[Task.totalTask] = new Event(taskName, time);
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance, and add to the to-do list if the syntax is correct
     *
     * @param tasks   The array that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(Task[] tasks, String command, String[] words) {
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
            showMessage("Sorry, the task is empty!");
            return false;
        }
        if (time.isEmpty()) {
            showMessage("Sorry, the deadline for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        tasks[Task.totalTask] = new Deadline(taskName, time);
        if (longestTaskDescription < taskName.length() + time.length()) {
            longestTaskDescription = taskName.length() + "(by: )".length() + time.length();
        }
        return true;
    }

    /**
     * Checks if the input command starts with "done"
     *
     * @param firstWord The first word in the command
     * @return Returns true if the first word is equivalent to "done" no matter it is uppercase or lowercase or mixed, false otherwise
     */
    private static boolean isCommandDone(String firstWord) {
        return firstWord.equalsIgnoreCase("DONE");
    }

    /**
     * Checks if the input command is equivalent to 'list' or 'ls'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'list' or 'ls' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    private static boolean isCommandList(String command) {
        return command.equalsIgnoreCase("LIST") || command.equalsIgnoreCase("LS");
    }

    /**
     * Checks if the input command is equivalent to 'view -p'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'view -p' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    private static boolean isCommandViewPersonality(String command) {
        return command.equalsIgnoreCase("VIEW -P");
    }

    /**
     * Checks if the input command is empty
     *
     * @param command The input command
     * @return Returns true if the command is empty, false otherwise
     */
    private static boolean isCommandEmpty(String command) {
        return command.isEmpty();
    }

    /**
     * Checks if the input command is equivalent to 'bye' or 'exit'
     *
     * @param command The input command
     * @return Returns true if the command is equivalent to 'bye' or 'exit' no matter it is uppercase or lowercase or mixed, false otherwise
     */
    private static boolean isCommandExit(String command) {
        return command.equalsIgnoreCase("EXIT") || command.equalsIgnoreCase("BYE");
    }

    /**
     * Marks the given tasks as done, and handles the possible errors if the input task number is not valid
     *
     * @param tasks The array that contains all the tasks stored inside the to-do list
     * @param words The array of words that compose the input command
     */
    private static void handleTaskDone(Task[] tasks, String[] words) {
        try {
            if (words.length == 1) {
                showMessage("Sorry, the input task number is missing, please try again! :(");
            }
            for (int i = 1; i < words.length; i++) {
                //check if the input character after the word "done" is integer value
                int taskNumber = Integer.parseInt(words[i]);
                if (taskNumber <= Task.totalTask && taskNumber > 0) {
                    showTaskDoneMessage(tasks, taskNumber);
                } else {
                    showMessage("Sorry, the input task number is invalid, please try again! :(");
                }
            }
        } catch (NumberFormatException ex) {
            showMessage("Sorry, the input task number is invalid, please try again! :(");
        }
    }

    /**
     * Shows the formatted message string
     *
     * @param message The message to print
     */
    private static void showMessage(String message) {
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
        System.out.println("\t   " + message);
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
    }

    /**
     * Shows the message to indicate that the task is marked as done
     *
     * @param tasks      The array which stores all the tasks
     * @param taskNumber The given task number to mark as done
     */
    private static void showTaskDoneMessage(Task[] tasks, int taskNumber) {
        if (!tasks[taskNumber - 1].getDone()) {
            tasks[taskNumber - 1].setDone();
            System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
            System.out.println("\t[✔] " + tasks[taskNumber - 1].getTask());
        } else {
            System.out.println("\tThe task number " + taskNumber + " - \"" + tasks[taskNumber - 1].getTask() + "\" has already been done!");
        }
    }

    /**
     * Prints the to-do list with frames
     *
     * @param tasks     the array of class Task instance which stores all the tasks added by the user
     * @param totalTask the last index of the array that is not null
     */
    private static void printToDoList(Task[] tasks, int totalTask, int longestTaskDescription) {
        final int MIN_LENGTH = " My to-do list: ".length();
        //if longestTaskDescription is shorter than the length of the string "My to-do list: ", sets it to the length of the string
        if (longestTaskDescription < MIN_LENGTH) {
            longestTaskDescription = MIN_LENGTH;
        }
        //Prints the to-do list
        drawUpperFrame(longestTaskDescription);
        printTasks(tasks, totalTask, longestTaskDescription);
        drawLowerFrame(longestTaskDescription);
    }

    /**
     * Prints the bottom frame of the to-do list and the guide for reading the to-do list
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawLowerFrame(int longestTaskDescription) {
        System.out.print("\t" + BOTTOM_LEFT_CORNER);
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(BOTTOM_RIGHT_CORNER);
        //Shows the guide for understanding the to-do list
        System.out.println("\tFor your knowledge, ");
        System.out.println("\tthe first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)");
        System.out.println("\tthe second [ ] indicates whether the task is completed:\n" +
                "\t[X] when the task is marked completed\t[ ] when the task is not done.");
    }

    /**
     * Prints the tasks stored in the array, the frame starts with '|' and ends with '|', the ending frame is always located at the position of the longest task description
     * @param tasks the array that stores all the tasks
     * @param totalTask the number of the tasks stored
     * @param longestTaskDescription the length of the longest task description stored in the tasks array
     */
    private static void printTasks(Task[] tasks, int totalTask, int longestTaskDescription) {
        for (int i = 0; i < totalTask; i++) {
            //Fill the first [] with class type, and the second [] with a 'X' if the task is completed
            if (tasks[i].getDone()) {
                System.out.print("\t| [" + tasks[i].getClassType() + "][X] " + (i + 1) + ". ");
            } else {
                System.out.print("\t| [" + tasks[i].getClassType() + "][ ] " + (i + 1) + ". ");
            }
            //Calculates the required spacing for the current task as compared to the longest task description to print '|'
            int distanceToClosingFrame = longestTaskDescription + "| [ ][ ] 100. ".length() - ("| [ ][ ] " + (i + 1) + ". ").length() + 1;
            System.out.printf("%1$-" + distanceToClosingFrame + "s", tasks[i]);
            System.out.println("|");
        }
    }

    /**
     * Prints the upper frame of the to-do list and its default display string
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawUpperFrame(int longestTaskDescription) {
        System.out.print("\t" + TOP_LEFT_CORNER); //the top left corner
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(TOP_RIGHT_CORNER);
        //Print default string " My to-do list: "
        System.out.print("\t| My to-do list: ");
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length() - "| My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}

