import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Duke {
    public static final String CURR_VERSION = "Version 4.0";
    public static final int MAX_TASKS = 100;
    public static Scanner in = new Scanner(System.in);
    public static int longestTaskDescription = 0;

    /**
     * Print the logo and greeting message
     */
    public static void printLogo() {
        //print the logo when the program starts
        System.out.println("\t              ##*                                 ");
        System.out.println("\t   .      . .&####%                               ");
        System.out.println("\t    #%%%%((&%######%**.                           ");
        System.out.println("\t    %#############%#%&&%%%%%##(*.                 ");
        System.out.println("\t . .%%#######%%%%%##############%%%%#(.           ");
        System.out.println("\t/##&@%####&%####&#####%&&&&&&&&&%%%####&/  .      ");
        System.out.println("\t&######%%######%&%%%#################%%%%&#       ");
        System.out.println("\t(@####&#####%&%########################%###@(     ");
        System.out.println("\t,&%#%%%%##%%############%%%%%%%%%%%##########%.   ");
        System.out.println("\t*&#&%%&#%&########&%#(%#/((((((/****#&%&%#####%,  ");
        System.out.println("\t  ,&#%%%%#####%%#(%(((&#(&(((((((((/%#(((%##%%#%. ");
        System.out.println("\t  (%##%%####%%(((/%&((%&#%##((((((((&#(((%#(((&&, ");
        System.out.println("\t  (%#%&####%#((##(#(/###.%#.*(##(((%, /##((%#(#( .");
        System.out.println("\t  *&(&%###%#(((((#/@%%%&,. ...     &%%&%  (&###*  ");
        System.out.println("\t . (%%%###%(((((#/#####&, .. .... /&(#(&,.%(((#(  ");
        System.out.println("\t    *&&##%%(((((%*(%###%. .. .. . *%(##&, (%((#(  ");
        System.out.println("\t      (&#%%((((#%/ .**,. ..... . ...,,,. . (#(%(  ");
        System.out.println("\t        .(%(((((&/ .   .  ...... .. .. . .(%/#%,  ");
        System.out.println("\t          .(%(((#&*,,.           . .,*/*%#((#&*   ");
        System.out.println("\t              ,/(#(    .,,******,.     ####%*     ");
    }

    /**
     * Print the welcome message and the ASCII art when the program starts
     */
    public static void printWelcomeMessage() {
        System.out.println("   __          ________ _      _____ ____  __  __ ______    ");
        System.out.println("   \\ \\        / /  ____| |    / ____/ __ \\|  \\/  |  ____|  ");
        System.out.println("    \\ \\  /\\  / /| |__  | |   | |   | |  | | \\  / | |__     ");
        System.out.println("     \\ \\/  \\/ / |  __| | |   | |   | |  | | |\\/| |  __|    ");
        System.out.println("      \\  /\\  /  | |____| |___| |___| |__| | |  | | |____   ");
        System.out.println("     __\\/_ \\/   |______|______\\_____\\____/|_|  |_|______|_ ");
        System.out.println("    / ____| |  | |_   _|  \\/  |   /\\   |  __ \\|_   _| \\ | |");
        System.out.println("   | (___ | |__| | | | | \\  / |  /  \\  | |__) | | | |  \\| |");
        System.out.println("    \\___ \\|  __  | | | | |\\/| | / /\\ \\ |  _  /  | | | . ` |");
        System.out.println("    ____) | |  | |_| |_| |  | |/ ____ \\| | \\ \\ _| |_| |\\  |");
        System.out.println("   |_____/|_|  |_|_____|_|  |_/_/    \\_\\_|  \\_\\_____|_| \\_|");
        //greeting
        System.out.println("\nHi there! My name is Shima Rin and I am a chat robot that can help you do some wonderful jobs!");
    }

    /**
     * Show the current version's functionality of the bot
     */
    private static void printVersionDescription() {
        //Uses list to store all the version information
        List<String> versionDescriptions = new ArrayList<String>();
        int maxDescriptionsLength = 0;
        versionDescriptions.add("* " + CURR_VERSION);
        versionDescriptions.add("* To know more about me, you can view my profile by typing the command \"view -p\"");
        versionDescriptions.add("* For now I am a note bot that can help you note down any tasks and create a to-do list for you :)");
        versionDescriptions.add("* In addition, you can mark any task in the to-do list as done!");
        versionDescriptions.add("* You can type \"todo\" or \"deadline\" or \"event\" to create a task and I will help you save it automatically!");
        versionDescriptions.add("* You can type \"list\" or \"ls\" to list all the tasks that are waiting to do");
        versionDescriptions.add("* You can type \"done i\" where i is the index of the task to mark the specific task as done");
        versionDescriptions.add("* You can type \"exit\" or \"bye\" to stop me and exit the program");
        //Finds the length of the longest description to align all '*' displayed
        for (String str : versionDescriptions) {
            if (str.length() > maxDescriptionsLength) {
                maxDescriptionsLength = str.length();
            }
        }
        //Draws the frame for the version description
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (String str : versionDescriptions) {
            //Uses format string to print the '*' at the correct position after each sentence is completed
            System.out.printf("%1$-" + (maxDescriptionsLength + 1) + "s", str);
            System.out.println("*");
        }
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        //Prints all the welcome screens
        printLogo();
        printWelcomeMessage();
        printVersionDescription();
        System.out.println("\nLet's start:");
        Task[] tasks = new Task[MAX_TASKS];
        while (true) {
            readCommand(tasks);
        }
    }

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(Task[] tasks) {
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        if (isCommandEmpty(command)) {
            System.out.println("\t(Empty) <- will not save to the list");
        } else if (isCommandViewPersonality(command)) {
            printPersonality();
        } else if (isCommandExit(command)) {
            showMessage("Bye! Hope to see you again :D");
            System.exit(0);
        } else {
            if (isCommandList(command)) {
                printToDoList(tasks, Task.totalTask, longestTaskDescription);
            } else if (isCommandDone(words[0])) {
                handleTaskDone(tasks, words);
            } else if (isCommandAddTask(words[0])) {
                addTask(tasks, command, words);
            } else {
                showMessage("Sorry, the command is invalid :(");
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
     * @param stopIndex the last index of the array that is not null
     */
    private static void printToDoList(Task[] tasks, int stopIndex, int maxLength) {
        //format to-do list
        System.out.print("\t/");
        if (maxLength < " My to-do list: ".length()) {
            maxLength = " My to-do list".length();
        }
        for (int i = 0; i < maxLength + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println("\\");
        System.out.print("\t| My to-do list: ");
        for (int i = 0; i < maxLength + "| [ ][ ] 100. ".length() - "| My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        //print task
        for (int i = 0; i < stopIndex; i++) {
            if (tasks[i].getDone()) {
                System.out.print("\t| [" + tasks[i].getClassType() + "][X] " + (i + 1) + ". " + tasks[i]);
            } else {
                System.out.print("\t| [" + tasks[i].getClassType() + "][ ] " + (i + 1) + ". " + tasks[i]);
            }
            for (int j = 0; j < maxLength + "| [ ][ ] 100. ".length() - ("| [ ][ ] " + (i + 1) + ". " + tasks[i].toString()).length() + 1; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.print("\t\\");
        for (int i = 0; i < maxLength + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println("/");
        System.out.println("\tFor your knowledge, ");
        System.out.println("\tthe first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)");
        System.out.println("\tthe second [ ] indicates whether the task is completed:\n\t[X] when the task is marked completed\t[ ] when the task is not done.");
    }

    /**
     * Prints the ASCII art image of the robot and the description of the personality of the robot
     */
    public static void printPersonality() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
        case 0:
            System.out.println(ProfilePicture.PICTURE_1);
            break;
        case 1:
            System.out.println(ProfilePicture.PICTURE_2);
            break;
        case 2:
            System.out.println(ProfilePicture.PICTURE_3);
            break;
        }
        System.out.println("**                                                                                                                   **");
        System.out.println("**       Greeting!                                                                                                   **");
        System.out.println("**      -My name is Shima Rin, I am a Japanese anime character that comes from the anime \"Yuru Camp\" :P              **");
        System.out.println("**      -I love physical camping, especially during the winter season!                                               **");
        System.out.println("**      -I also love food (especially hot pot!) and hot spring! :P                                                   **");
        System.out.println("**      -By the way, I am a dog lover!                                                                               **");
        System.out.println("**                                                                                                                   **");
        System.out.println("***********************************************************************************************************************");
    }
}

