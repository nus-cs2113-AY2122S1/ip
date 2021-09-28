package duke;

import java.util.Scanner;

/** To deal with interactions with the user such as the user sending commands
 * and C3PO sending replies.
 */
public class Ui {

    protected Scanner in;

    /**
     * Instantiates the Ui object and allows the user to send their commands.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String sendInput() {
        return in.nextLine();
    }

    public static final String EMPTY = "There is no data in your list master!";
    public static final String UNSPECIFIED_DONE = "Oh no master, I am not quite sure which task you would like me to mark as done!";
    public static final String UNSPECIFIED_DELETE = "Oh no master, I am not quite sure which task you would like me to delete!";
    public static final String UNSPECIFIED_FIND = "Oh no master, I am not quite sure which task you would like me to find!";
    public static final String UNSPECIFIED_DATE = "Oh no master, please specify the date for which you would like me to print the respective tasks!\n" +
                                                  "e.g \"date 2021-12-23\"";
    public static final String INVALID = "Please type in a valid number master! Type \"list\" to check the index number of your list data";
    public static final String DEADLINE_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                                 "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
                                                 "and lastly followed by the due date to specify the deadline Master!");
    public static final String EVENT_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters. \n" +
                                              "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
                                              "lastly followed by the event duration to specify the timing of the event Master!");
    public static final String TODO_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                             " Please enter \"todo\", followed by the task you wish to add to your \n" +
                                             "duke.Todo list Master!");
    public static final String UNSPECIFIED_TASK = ("Sorry Master! Despite the fact that I am fluent in over six million forms\n" +
                                                   " of communication, I am unable to comprehend your request. Please specify\n" +
                                                   " the type of task that you wish to add Master!");
    public static final String NUMBER_FORMAT_EXCEPTION = "Master, please type in a number to indicate the task you want me to perform the necessary actions for!";
    public static final String INPUT_OUTPUT_EXCEPTION = "There is an error in your input master! Please check it out!";
    public static final String DATE_TIME_PARSE_EXCEPTION = "Please enter the correct date and time format master! It is \"YYYY-MM-DD HH:MM\". e.g 2021-12-23 19:00";
    public static final String FILE_NOT_FOUND_EXCEPTION = "File not found. Automatic text file creation initiated master!";
    public static final String STARTING_MESSAGE = "Accessing archives, loading in data, C3PO systems online!";
    public static final String ENDING_MESSAGE = "Goodbye master! May the force be with you!\n";
    public static final String USER_PROMPT_MESSAGE = "Type something: ";
    public static final String LINE = "____________________________________________________________\n";
    public static final String LOGO = "       /~\\\n"
                                    + "      |oo )\n"
                                    + "      _\\=/_\n"
                                    + "     /     \\\n"
                                    + "    //|/.\\|\\\\\n"
                                    + "   ||  \\_/  ||\n"
                                    + "   || |\\ /| ||\n"
                                    + "    # \\_ _/  #\n"
                                    + "      | | |\n"
                                    + "      | | |\n"
                                    + "      []|[]\n"
                                    + "      | | |\n"
                                    + "     /_]_[_\\\n";
    public static final String GREETINGS = "____________________________________________________________\n" +
                                  "Hello! I am C3P0! Human-cyborg relations! \n" + " \n" + LOGO + "\n" +
                                  "What can I do for you my master?\n";
    public static final String TASK_ADDED = "Added to Galactic database:";
    public static final String MARKED_DONE = "The following task has been marked as done Master!";
    public static final String LOADING = "Accessing archives...";
    public static final String DELETED_TASK = "Taking one last look Master, at this Task. Removing the following from my memory";

    /** To say goodbye to the user before the program terminates */
    public static void sayBye() {
        System.out.println(ENDING_MESSAGE + LINE);
    }

    /** To indicate to the user that a task has been added to their list */
    public static void sayTaskAdded() {
        System.out.println(TASK_ADDED);
    }

    /** To indicate to the user that a task has been marked as done */
    public static void sayMarkedDone() {
        System.out.println(MARKED_DONE);
    }

    /** To indicate to a user that a task will be deleted */
    public static void sayGoodbyeTask() {
        System.out.println(DELETED_TASK);
    }

    /** To indicate that their tasks will be shown soon */
    public static void sayLoadingList() {
        System.out.println(LOADING);
    }

    /** To welcome the user when they run the program */
    public static void greetUser() {
        System.out.println(GREETINGS);
    }

    /** To prompt the user to key in a command */
    public static void promptUser() {
        System.out.println(LINE);
        System.out.print(USER_PROMPT_MESSAGE);
    }

    /** To print a line to separate commands from the user and those from C3PO */
    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printNumberFormatExceptionMessage() {
        System.out.println(NUMBER_FORMAT_EXCEPTION);
    }

    public static void printIOExceptionMessage() {
        System.out.println(INPUT_OUTPUT_EXCEPTION);
    }

    public static void printDateTimeExceptionMessage() {
        System.out.println(DATE_TIME_PARSE_EXCEPTION);
    }

    public static void printFileNotFoundMessage() {
        System.out.println(FILE_NOT_FOUND_EXCEPTION);
    }
}
