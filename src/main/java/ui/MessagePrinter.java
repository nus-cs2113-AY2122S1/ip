package ui;

import tasks.Task;
import java.util.ArrayList;

public abstract class MessagePrinter {
    // constants storing printing messages
    private static final String IRON_MAN_MASK = "                           .*%@@@@@@@@@@@@@@@@@@@@@@&(.                         \n"
            + "                      /@@&*  #@@@@@@@@@@@@@@@@@@@@@@@  .%@@%.                   \n"
            + "                  &@@*        @@@@@@@@@@@@@@@@@@@@@@,       .@@@.               \n"
            + "              *@@#            ,@@@@@@@@@@@@@@@@@@@@#            *@@(            \n"
            + "             &%                &@@@@@@@@@@@@@@@@@@@                /@.          \n"
            + "           .%                   @@@@@@@@@@@@@@@@@@,                  ,%         \n"
            + "           %@                   ,@@@@@@@@@@@@@@@@%                   *@         \n"
            + "           @@                    #@@@@@@@@@@@@@@@                    /@/        \n"
            + "          *@@                                                        (@&        \n"
            + "          &@@.                                                       %@@.       \n"
            + "          @@@.                                                       &@@*       \n"
            + "         .@@@,                                                       &@@/       \n"
            + "         ,@@@*                                                       @@@%       \n"
            + "         *@@@*                                                       @@@@       \n"
            + "         (@@@.                                                       &@@@       \n"
            + "         @@@&  /@&%/,.                                      .,*#%&@  ,@@@/      \n"
            + "         @@@. .@@@@@@@@@@@@@@@@@@%###############@@@@@@@@@@@@@@@@@@(  @@@(      \n"
            + "         @@%     ,&@@@@@@@@&%/,.                   .*#&&@@@@@@@@(.    .@@#      \n"
            + "         @@.                                                           %@(      \n"
            + "         @@                                                            *@/      \n"
            + "         &@.                                                           #@,      \n"
            + "         &@@,                                                         @@@.      \n"
            + "         %@@@..                                                      #@@@       \n"
            + "         *@@@@ &/                                                .@,/@@@&       \n"
            + "          &@@@@.#@/                                             @@.#@@@@.       \n"
            + "          ,@@@@@*/@@.                                         %@& @@@@@&        \n"
            + "          .@@@@@@#.@@*                                       @@#,@@@@@@#        \n"
            + "           @@@@@@@@ &@,                                     &@.(@@@@@@@,        \n"
            + "           /@@@@@@@@,/#                                    .& @@@@@@@@@         \n"
            + "            @@@@@@@@@#..     &@@&%#######((((####%@@@.     ,,@@@@@@@@@,         \n"
            + "            *@@@@@@@@@. *@%/@*                      .@%*@%  %@@@@@@@@&          \n"
            + "             &@@@@@@@&                                      ,@@@@@@@@,          \n"
            + "             *@@@@@@@                 ,&&&&(                 #@@@@@@&           \n"
            + "              /@@@@@@#        .&@@@@@@@@@@@@@@@@@@@*        *@@@@@@&            \n"
            + "                 (@@@@@@@, .@@@@@@@@@@@@@@@@@@@@@@@@@@/  #@@@@@@&.              \n"
            + "                    .&@@@@@*@@@@@@@@@@@@@@@@@@@@@@@@@@%(@@@@@*                  \n"
            + "                        .#@@,@#                    *@##@&,                      \n";
    private static final String FRIDAY = "  __      _     _             \n"
            + " / _|    (_)   | |            \n"
            + "| |_ _ __ _  __| | __ _ _   _ \n"
            + "|  _| '__| |/ _` |/ _` | | | |\n"
            + "| | | |  | | (_| | (_| | |_| |\n"
            + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
            + "                         __/ |\n"
            + "                        |___/ \n";
    private static final String DASHES = "________________________________________________________________________________________________________________________";

    // methods to print constants
    private static void friday() {
        System.out.print(FRIDAY);
    }

    private static void dashes() {
        System.out.println(DASHES);
    }

    private static void ironManMask() {
        System.out.println(IRON_MAN_MASK);
    }

    // methods to print messages

    // greet User upon entry
    public static void initiateProgram() {
        dashes();
        ironManMask();
        friday();
        System.out.println("Initiating FRIDAY");
    }

    public static void greetUser() {
        System.out.println("Hello Mr Stark, how may I be of assistance to you today?");
        dashes();
    }

    public static void loadingData() {
        System.out.println("Allow me to fetch the data from the archives ...");
    }

    public static void dataLoaded() {
        System.out.println("Data successfully loaded into memory sir.");
    }

    // if list of tasks  is empty
    public static void emptyListMessage() {
        dashes();
        System.out.println("You appear to have no tasks for today, how wonderful sir.");
        dashes();
    }

    // otherwise
    public static void printList(ArrayList<Task> tasks) {
        dashes();
        System.out.println("You have a total of " + tasks.size() +" tasks sir, listing them out now.");
        for (Task task : tasks) {
            System.out.println(task);
        }
        dashes();
    }

    // after marking task as done
    public static void taskMarkedAsDone(Task currTask) {
        dashes();
        System.out.println("Your task \"" + currTask.getTaskName() + "\" is marked as complete. Splendid sir.");
        System.out.println("[X]" + currTask.getTaskName());
        dashes();
    }

    // Message for adding tasks
    public static void addedTask(String taskName) {
        dashes();
        System.out.println("Very well, adding task \"" + taskName + "\".");
        dashes();
    }

    public static void filteredTasks(ArrayList<Task> filteredList) {
        if (filteredList.isEmpty()) {
            dashes();
            System.out.println("There are no tasks matching your query sir.");
            dashes();
        } else {
            dashes();
            System.out.println("Here are all your current tasks matching your query sir.");
            for (Task task : filteredList) {
                System.out.println(task);
            }
            dashes();
        }
    }

    public static void removedTask(String taskName, int tasksLength) {
        dashes();
        System.out.println("Alright sir, removing task \"" + taskName + "\".");
        System.out.println("You now have " + tasksLength + " tasks remaining.");
        dashes();
    }

    // message when no task name is specified
    public static void emptyTaskName() {
        dashes();
        System.out.println("I am going to need a name for this task sir.");
        dashes();
    }

    // Messages for null command (invalid command) enum
    public static void invalidCommand() {
        dashes();
        System.out.println("Apologies sir, I do not understand your command.");
        dashes();
    }

    // if taskIndex is out of bounds
    public static void outOfBoundsTaskIndex() {
        dashes();
        System.out.println("Apologies sir, the number you entered is not within the range of tasks in my memory.");
        dashes();
    }

    // if no task is at taskIndex specified by user
    public static void invalidTaskIndex() {
        dashes();
        System.out.println("Hmm.. How strange, there appears to be no task at this location... Perhaps you could try another number sir?");
        dashes();
    }

    public static void invalidIndex() {
        dashes();
        System.out.println("I was programmed to only work with numbers for this command by you sir.");
        dashes();
    }

    // message when no description after keyword is given
    public static void incompleteCommand() {
        dashes();
        System.out.println("I'm afraid you are going to have to be more specific sir.");
        dashes();
    }

    public static void missingIndex(String command) {
        dashes();
        System.out.println("May I know the index of the of the task you want me to " + command + " sir?");
        dashes();
    }

    // message when keyword is missing
    public static void missingKeyWord(String word) {
        dashes();
        System.out.println("Apologies sir, you need to include /" + word + " in your command");
        dashes();
    }

    public static void missingDate(String type) {
        dashes();
        System.out.println("May I know the date for this " + type + " sir?");
        dashes();
    }

    public static void missingQuery() {
        dashes();
        System.out.println("I require a specific keyword to query sir.");
        dashes();
    }

    public static void invalidDate() {
        dashes();
        System.out.println("Sir you modelled me to read dates in the format yyyy-mm-dd. "
                + "If you would kindly check the format or validity of your date.");
        dashes();
    }

    public static void ioexception(String error) {
        dashes();
        System.out.println("There was the error of type " + error + " when updating the storage.");
        dashes();
    }

    // message printed upon exiting
    public static void exitMessage() {
        dashes();
        System.out.println("Powering off now. Good Bye Mr Stark.");
        dashes();
    }
}
