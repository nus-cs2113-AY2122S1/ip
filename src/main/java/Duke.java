import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    final static String HOR_LINE = "_".repeat(60);
    private static ArrayList<Task> tasksList = new ArrayList<>();
    private static int mode = 0;

    private final static int ECHO_MODE = 1;
    private final static int TASK_MODE = 2;

    private final static String filePath = "data/duke.txt";

    /**
     * Function introduces chatbot and asks user for preferred mode and enters that mode.
     * Mode 1 - Echo; 2 - Task; Otherwise - Returns Error Message and stops program.
     */
    public static void greet() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tHi there! I'm Lennox - your personal chatbot\n" +
                "\tWhat can I do for you today?");

        // Mode Selection
        System.out.println("\t* Enter 1 for Echo mode.\n " +
                "\t* Enter 2 for Task mode.");
        Scanner inp = new Scanner(System.in);
        mode = inp.nextInt();
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
        if (mode == ECHO_MODE) {
            System.out.println("\tECHO MODE - Commands entered will be echoed back.");
            startEcho();
        } else if (mode == TASK_MODE) {
            System.out.println("\tTASK MODE - Enter items to include in to-do list.");
            startTask();
        } else {
            System.out.println("\tERROR. PLEASE RUN AGAIN AND SELECT RIGHT MODE.\n");
            mode = 0;
        }
    }

    /**
     * Function prints Lennox chatbot logo.
     */
    private static void printLogo() {
        String logo = "#\t,--.                                      \t#\n" +
                "#\t|  |   ,---.,--,--,,--,--, ,---,--.  ,--. \t#\n" +
                "#\t|  |  | .-. |      |      | .-. \\  `'  /  \t#\n" +
                "#\t|  '--\\   --|  ||  |  ||  ' '-' /  /.  \\  \t#\n" +
                "#\t`-----'`----`--''--`--''--'`---'--'  '--' \t#";
        System.out.println("#".repeat(49));
        System.out.println("#\tHello from \t\t\t\t\t\t\t\t\t#");
        System.out.println(logo);
        System.out.println("# \t\t\t\t\t\t\t\t\t\t\t\t#");
        System.out.println("#\t\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ \t\t#");
        System.out.println("#\t\t[| o o |]" + "\t[| o o |]" + "\t[| o o |]\t\t#");
        System.out.println("#\t\t |  ^  | " + "\t |  ^  | " + "\t |  ^  | \t\t#");
        System.out.println("#\t\t | '-' | " + "\t | '-' | " + "\t | '-' | \t\t#");
        System.out.println("#\t\t +-----+ " + "\t +-----+ " + "\t +-----+ \t\t#");
        System.out.println("#".repeat(49));
    }

    /**
     * Prints out entered command by user in Echo mode.
     *
     * @param cmd Command entered by user.
     */
    public static void echoCommand(String cmd) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tYou have entered: " + cmd);
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Function switches between Echo and Task modes.
     *
     * @param newMode Code for new mode (1 - Echo; 2 - Task)
     */
    public static void switchMode(int newMode) {
        mode = newMode;
        System.out.println("\t" + HOR_LINE);
        if (newMode == ECHO_MODE) {
            System.out.println("\tECHO MODE ENTERED.");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            startEcho();
        } else {
            System.out.println("\tTASK MODE ENTERED.");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            startTask();
        }
    }

    /**
     * Executes Echo mode, where commands of user are echoed back.
     * When "change" is typed in by user, switches program to Task mode.
     */
    public static void startEcho() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        String cmdLowerC = command.toLowerCase();
        while (!cmdLowerC.equals("bye")) {
            while (command.equals("") | command.equals(" ")) {
                System.out.println("\t" + HOR_LINE);
                System.out.println("\tPlease enter a valid command!");
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
                command = in.nextLine();
            }

            // Switch to Task mode if user types in "change"
            if (cmdLowerC.equals("change")) {
                switchMode(TASK_MODE);
                return;
            }

            echoCommand(command);
            command = in.nextLine();
            cmdLowerC = command.toLowerCase();
        }
    }

    /**
     * Prints task list.
     */
    public static void printList() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tCURRENT ADDED LIST");
        int optionNo = 1;
        for (Task t: tasksList) {
            System.out.println("\t" + optionNo + ". " + t.getTypeIcon() +
                    t.getStatusIcon() + t.description);
            optionNo++;
        }
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Prints acknowledgement message that item has been added to list
     * and also mentions the total no. of tasks in list currently.
     *
     * @param currTask Task object that was just created after user input.
     */
    private static void printAddedResponse(Task currTask) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + currTask.getTypeIcon() + currTask.getStatusIcon() + currTask.description);
        System.out.println("\tNow there are " + tasksList.toArray().length + " tasks in the list.");
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Depending on the starting keyword present in user input, creates respective
     * Task subclass object and stores it inside the storedTasks array.
     *
     * @param userInput String command input by user.
     */
    public static void createTask(String userInput) throws DukeException {
        Task t;
        if (userInput.startsWith("event ")) {
            if (userInput.contains("/at ")) {
                t = new Event(userInput);
                tasksList.add(t);
            } else {
                throw new DukeException("\t*** Remember to include event timing after '/at ' in description. ***");
            }
        } else if (userInput.startsWith("deadline ")) {
            if (userInput.contains("/by ")) {
                t = new Deadline(userInput);
                tasksList.add(t);
            } else {
                throw new DukeException("\t*** Remember to indicate deadline after '/by ' in description. ***");
            }
        } else if (userInput.startsWith("todo ")) {
            if (!userInput.substring(4).isBlank()){
                t = new Todo(userInput);
                tasksList.add(t);
            } else {
                throw new DukeException("\t*** OOPS!!! The description of a todo cannot be empty. ***");
            }
        } else {
            throw new DukeException("\t☹ INVALID COMMAND\n" +
                    "\tPlease begin commands with\n" +
                    "\t'event', 'deadline', 'todo',\n" +
                    "\t'done', 'completed',\n" +
                    "\t'remove', or 'delete', and\n" +
                    "\tinput description after a whitespace.");
        }

        if (mode == TASK_MODE) {
            printAddedResponse(t);
            try {
                saveToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Executes Task mode, where user's commands are added into to-do list.
     * When "list" entered, prints out current to-do list.
     * When "completed <task id>" or "done <task id>" entered, updates that task status.
     * When "remove <task id>" or "delete <task id>" entered, removes that task from list.
     * When "change" is typed in by user, switches program to Echo mode.
     */
    public static void startTask() {
        Scanner in = new Scanner(System.in);
        String toAdd = in.nextLine();
        String textLowerC = toAdd.toLowerCase();

        while (!textLowerC.equals("bye") & !textLowerC.equals("list") & !textLowerC.equals("change")) {
            runTaskMode(toAdd, textLowerC);
            toAdd = in.nextLine();
            textLowerC = toAdd.toLowerCase();
        }

        if (textLowerC.equals("list")) {
            printList();
            startTask();
        }

        // Switch to Echo mode if user types in "change"
        if (textLowerC.equals("change")) {
            switchMode(ECHO_MODE);
        }
    }

    /**
     * Checks different scenarios for input commands of user in Task mode and
     * does the necessary action.
     *
     * @param toAdd String input by user.
     * @param textLowerC String input by user converted to lower cases for checking.
     */
    private static void runTaskMode(String toAdd, String textLowerC) {
        // Mark task as complete with an X.
        if (textLowerC.startsWith("completed ") | textLowerC.startsWith("done ")) {
            completeTask(toAdd);
        } else if (textLowerC.startsWith("delete ") | textLowerC.startsWith("remove ")) {
            // Remove task from list.
            deleteTask(toAdd);
        } else {
            // Create a new task if it does not exist in list
            try {
                createTask(toAdd);
            } catch (DukeException e) {
                e.printErrorMessage();
            }
        }
    }

    private static void completeTask(String completed) {
        try {
            int taskNo = Integer.parseInt(completed.replaceAll("[^0-9]", "")) - 1;
            tasksList.get(taskNo).setDone();
            System.out.println("\t" + HOR_LINE);
            System.out.printf("\tThat's great! %s has been checked as completed!\n", tasksList.get(taskNo).description);
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        } catch (NumberFormatException e) {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tPLEASE INPUT INVENTORY NO. OF THE TASK SEPARATED BY SPACE\n" +
                    "\tAFTER 'complete' OR 'done' TO MARK TASK AS DONE.");
            System.out.println("\t" + HOR_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tLIST IS CURRENTLY EMPTY OR INPUT INDEX IS OUT OF RANGE!");
            System.out.println("\t" + HOR_LINE);
        }
    }

    private static void deleteTask(String toRemove) {
        try {
            int taskNo = Integer.parseInt(toRemove.replaceAll("[^0-9]", "")) - 1;
            System.out.println("\t" + HOR_LINE);
            System.out.printf("\t%s removed from list!\n", tasksList.get(taskNo).description);
            tasksList.remove(taskNo);
            System.out.printf("\tNow you have %d tasks in the list.\n", tasksList.toArray().length);
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            printList();
        } catch (NumberFormatException e) {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tPLEASE INPUT INVENTORY NO. OF THE TASK SEPARATED BY SPACE\n" +
                    "\tAFTER 'delete' OR 'remove' TO REMOVE TASK FROM LIST.");
            System.out.println("\t" + HOR_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tLIST IS CURRENTLY EMPTY OR INPUT INDEX IS OUT OF RANGE!");
            System.out.println("\t" + HOR_LINE);
        }

        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Double confirms if user wants to exit.
     * If no, gets user back to Task/Echo mode user was in.
     * If yes, ends program with farewell message.
     */
    public static void exit() {
        System.out.print("\tDo you really want to exit chatbot (type y or n)? ");
        Scanner input = new Scanner(System.in);
        String exit_pref = input.nextLine();

        // User wishes to leave program.
        if (exit_pref.equals("y")) {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        } else if (exit_pref.equals("n")) {
            System.out.println("\n\tOk that's great! Continue keying in commands. :)");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            // Return back to previous mode since user is not exiting.
            if (mode == ECHO_MODE) {
                startEcho();
            } else if (mode == TASK_MODE) {
                startTask();
            }
            exit();
        } else {
            System.out.println("\tSorry, is it a y(es) or a n(o)?");
            exit();
        }
    }

    public static void initializeList() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int lineNo = 0;
        while (s.hasNext()) {
            try {
                String line = s.nextLine();
                String[] splitUp = line.strip().split(" < ");
                String cmd = createCommand(splitUp);
                createTask(cmd);
                if (splitUp[1].equals("Done")) {
                    tasksList.get(lineNo).setDone();
                }
                lineNo++;
            } catch (DukeException e) {
                e.printErrorMessage();
            }
        }
    }

    public static String createCommand(String[] lineFragments) {
        String toReturn = null;
        if (lineFragments.length == 3) {
            toReturn = "todo " + lineFragments[2];
        } else if (lineFragments[0].equals("D")) {
            toReturn = "deadline " + lineFragments[2] + " /by " + lineFragments[3];
        } else if (lineFragments[0].equals("E")) {
            toReturn = "event " + lineFragments[2] + " /at " + lineFragments[3];
        }
        return toReturn;
    }

    public static String createLineForFile(Task t) {
        String toReturn = null;
        String doneStatus;
        if (t.getStatusIcon().equals("[X] ")) {
            doneStatus= "Done";
        } else {
            doneStatus = "ND";
        }

        if (t instanceof Todo) {
            toReturn = "T < " + doneStatus + " < " + t.description;
        } else if (t instanceof Deadline) {
            String[] parts = t.description.split("[(]");
            toReturn = "D < " + doneStatus + " < " + parts[0] +
                    " < " + ((Deadline) t).due;
        } else if (t instanceof Event) {
            String[] parts = t.description.split("[(]");
            toReturn = "E < " + doneStatus + " < " + parts[0] +
                    " < " + ((Event) t).time;
        }
        return toReturn;
    }

    public static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task item: tasksList) {
            String textToWrite = createLineForFile(item) + System.lineSeparator();
            fw.write(textToWrite);
        }
        fw.close();
    }

    public static void main(String[] args) {
        // Read in list from previous session if it exists
        try {
            initializeList();
        } catch (FileNotFoundException e) {
            // Create new text file for saving if file not found
            File newFile = new File(filePath);
            try {
                newFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        // Actions
        printLogo();
        greet();
        if (mode != 0) {
            exit();
        }

        // The end.
        System.out.println("===== PROGRAM ENDED =====");
    }

}
